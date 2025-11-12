package com.music.sale.persistence.like;

import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import com.music.sale.persistence.like.repository.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * LikePersistenceAdapter 통합 테스트
 * 실제 DB(H2)에 연결하여 CRUD 동작 검증
 */
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DisplayName("좋아요 Persistence Adapter 통합 테스트")
class LikePersistenceAdapterIntegrationTest {

    @Autowired
    private LikeCommandPersistenceAdapter commandAdapter;

    @Autowired
    private LikeQueryPersistenceAdapter queryAdapter;

    @Autowired
    private LikeRepository likeRepository;

    @BeforeEach
    void setUp() {
        likeRepository.deleteAll();
    }

    @Test
    @DisplayName("좋아요 저장 - 성공")
    void save_Success() {
        // given
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);

        // when
        Like savedLike = commandAdapter.save(like);

        // then
        assertThat(savedLike.getId()).isNotNull();
        assertThat(savedLike.getUserId()).isEqualTo(1L);
        assertThat(savedLike.getLikeableId()).isEqualTo(100L);
        assertThat(savedLike.getLikeableType()).isEqualTo(LikeableType.PRODUCT);
    }

    @Test
    @DisplayName("좋아요 존재 여부 확인 - Command Port")
    void exists_CommandPort_Success() {
        // given
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);
        commandAdapter.save(like);

        // when
        boolean exists = commandAdapter.exists(1L, 100L, LikeableType.PRODUCT);
        boolean notExists = commandAdapter.exists(1L, 999L, LikeableType.PRODUCT);

        // then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    @DisplayName("좋아요 존재 여부 확인 - Query Port")
    void exists_QueryPort_Success() {
        // given
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);
        commandAdapter.save(like);

        // when
        boolean exists = queryAdapter.exists(1L, 100L, LikeableType.PRODUCT);
        boolean notExists = queryAdapter.exists(1L, 999L, LikeableType.PRODUCT);

        // then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    @DisplayName("좋아요 삭제 - 성공")
    void delete_Success() {
        // given
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);
        commandAdapter.save(like);

        // when
        commandAdapter.delete(1L, 100L, LikeableType.PRODUCT);

        // then
        boolean exists = commandAdapter.exists(1L, 100L, LikeableType.PRODUCT);
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("사용자의 좋아요 목록 조회 - 페이징")
    void findByUserIdAndType_Success() {
        // given
        commandAdapter.save(Like.create(1L, 100L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(1L, 101L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(1L, 102L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(2L, 103L, LikeableType.PRODUCT)); // 다른 사용자

        // when
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Like> result = queryAdapter.findByUserIdAndType(1L, LikeableType.PRODUCT, pageRequest);

        // then
        assertThat(result.getContent()).hasSize(3);
        assertThat(result.getTotalElements()).isEqualTo(3);
        assertThat(result.getContent())
                .extracting(Like::getUserId)
                .containsOnly(1L);
    }

    @Test
    @DisplayName("특정 대상의 좋아요 개수 조회")
    void countByLikeableIdAndType_Success() {
        // given
        commandAdapter.save(Like.create(1L, 100L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(2L, 100L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(3L, 100L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(4L, 101L, LikeableType.PRODUCT)); // 다른 상품

        // when
        long count = queryAdapter.countByLikeableIdAndType(100L, LikeableType.PRODUCT);

        // then
        assertThat(count).isEqualTo(3);
    }

    @Test
    @DisplayName("타입별 좋아요 분리 조회 - PRODUCT, STORE, SELLER")
    void findByType_Separation() {
        // given
        commandAdapter.save(Like.create(1L, 100L, LikeableType.PRODUCT));
        commandAdapter.save(Like.create(1L, 200L, LikeableType.STORE));
        commandAdapter.save(Like.create(1L, 300L, LikeableType.SELLER));

        // when
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Like> products = queryAdapter.findByUserIdAndType(1L, LikeableType.PRODUCT, pageRequest);
        Page<Like> stores = queryAdapter.findByUserIdAndType(1L, LikeableType.STORE, pageRequest);
        Page<Like> sellers = queryAdapter.findByUserIdAndType(1L, LikeableType.SELLER, pageRequest);

        // then
        assertThat(products.getContent()).hasSize(1);
        assertThat(stores.getContent()).hasSize(1);
        assertThat(sellers.getContent()).hasSize(1);
    }

    @Test
    @DisplayName("중복 좋아요 저장 시도 - 유니크 제약조건 (실패 예상)")
    void save_Duplicate_ShouldThrowException() {
        // given
        Like like1 = Like.create(1L, 100L, LikeableType.PRODUCT);
        commandAdapter.save(like1);

        // when & then
        Like like2 = Like.create(1L, 100L, LikeableType.PRODUCT);
        
        try {
            commandAdapter.save(like2);
            // H2 DB는 flush가 명시적으로 필요할 수 있음
            likeRepository.flush();
            assertThat(false).as("중복 저장이 성공하면 안 됨").isTrue();
        } catch (Exception e) {
            // 유니크 제약조건 위반 예외 발생 (정상)
            assertThat(e).isNotNull();
        }
    }
}


