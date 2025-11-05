package com.music.sale.application.like.service;

import com.music.sale.application.like.dto.LikeOutput;
import com.music.sale.application.like.dto.LikeStatusOutput;
import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.mapper.LikeMapper;
import com.music.sale.application.like.port.outport.LikePort;
import com.music.sale.common.DefaultPageable;
import com.music.sale.common.Pageable;
import com.music.sale.domain.like.Like;
import com.music.sale.domain.like.LikeableType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * LikeService 단위 테스트
 * Mockito를 사용하여 LikePort를 모킹합니다.
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("LikeService 단위 테스트")
class LikeServiceTest {

    @Mock
    private LikePort likePort;

    @Mock
    private LikeMapper likeMapper;

    @InjectMocks
    private LikeService likeService;

    private Like testLike;
    private LikeOutput testLikeOutput;

    @BeforeEach
    void setUp() {
        testLike = Like.of(1L, 100L, 1L, LikeableType.PRODUCT, LocalDateTime.now());
        testLikeOutput = new LikeOutput(1L, 100L, 1L, LikeableType.PRODUCT, LocalDateTime.now());
    }

    @Test
    @DisplayName("상품 찜하기 - 성공")
    void addLike_Success() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(false);
        given(likePort.save(any(Like.class))).willReturn(testLike);
        given(likeMapper.toOutput(testLike)).willReturn(testLikeOutput);

        // when
        LikeOutput result = likeService.addLike(userId, productId, type);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getLikeableId()).isEqualTo(productId);
        assertThat(result.getLikeableType()).isEqualTo(type);

        then(likePort).should(times(1)).exists(userId, productId, type);
        then(likePort).should(times(1)).save(any(Like.class));
        then(likeMapper).should(times(1)).toOutput(testLike);
    }

    @Test
    @DisplayName("상품 찜하기 - 중복 시 예외 발생")
    void addLike_AlreadyExists_ThrowsException() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(true);

        // when & then
        assertThatThrownBy(() -> likeService.addLike(userId, productId, type))
                .isInstanceOf(LikeAlreadyExistsException.class)
                .hasMessageContaining("이미");

        then(likePort).should(times(1)).exists(userId, productId, type);
        then(likePort).should(never()).save(any(Like.class));
    }

    @Test
    @DisplayName("찜 취소 - 성공")
    void deleteLike_Success() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(true);
        doNothing().when(likePort).delete(userId, productId, type);

        // when
        likeService.deleteLike(userId, productId, type);

        // then
        then(likePort).should(times(1)).exists(userId, productId, type);
        then(likePort).should(times(1)).delete(userId, productId, type);
    }

    @Test
    @DisplayName("찜 취소 - 존재하지 않으면 예외 발생")
    void deleteLike_NotExists_ThrowsException() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(false);

        // when & then
        assertThatThrownBy(() -> likeService.deleteLike(userId, productId, type))
                .isInstanceOf(LikeNotFoundException.class)
                .hasMessageContaining("찾을 수 없습니다");

        then(likePort).should(times(1)).exists(userId, productId, type);
        then(likePort).should(never()).delete(userId, productId, type);
    }

    @Test
    @DisplayName("좋아요 상태 조회 - 찜한 경우")
    void getLikeStatus_IsLiked() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(true);

        // when
        LikeStatusOutput result = likeService.getLikeStatus(userId, productId, type);

        // then
        assertThat(result).isNotNull();
        assertThat(result.isLiked()).isTrue();

        then(likePort).should(times(1)).exists(userId, productId, type);
    }

    @Test
    @DisplayName("좋아요 상태 조회 - 찜하지 않은 경우")
    void getLikeStatus_IsNotLiked() {
        // given
        Long userId = 100L;
        Long productId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        given(likePort.exists(userId, productId, type)).willReturn(false);

        // when
        LikeStatusOutput result = likeService.getLikeStatus(userId, productId, type);

        // then
        assertThat(result).isNotNull();
        assertThat(result.isLiked()).isFalse();

        then(likePort).should(times(1)).exists(userId, productId, type);
    }

    @Test
    @DisplayName("내 찜 목록 조회 - 성공")
    void getMyLikes_Success() {
        // given
        Long userId = 100L;
        LikeableType type = LikeableType.PRODUCT;
        Pageable pageable = new DefaultPageable(0, 10, null, null);

        Like like1 = Like.of(1L, userId, 1L, type, LocalDateTime.now());
        Like like2 = Like.of(2L, userId, 2L, type, LocalDateTime.now());
        List<Like> likeList = List.of(like1, like2);
        
        Page<Like> likePage = new PageImpl<>(likeList);
        
        LikeOutput output1 = new LikeOutput(1L, userId, 1L, type, LocalDateTime.now());
        LikeOutput output2 = new LikeOutput(2L, userId, 2L, type, LocalDateTime.now());

        given(likePort.findByUserIdAndType(eq(userId), eq(type), any(Pageable.class)))
                .willReturn(likePage);
        given(likeMapper.toOutput(like1)).willReturn(output1);
        given(likeMapper.toOutput(like2)).willReturn(output2);

        // when
        Page<Object> result = likeService.getMyLikes(userId, type, pageable);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getContent()).hasSize(2);
        assertThat(result.getTotalElements()).isEqualTo(2);

        then(likePort).should(times(1)).findByUserIdAndType(eq(userId), eq(type), any(Pageable.class));
        then(likeMapper).should(times(2)).toOutput(any(Like.class));
    }

    @Test
    @DisplayName("스토어 구독 - 성공")
    void addLike_Store_Success() {
        // given
        Long userId = 100L;
        Long storeId = 5L;
        LikeableType type = LikeableType.STORE;

        Like storeLike = Like.of(1L, userId, storeId, type, LocalDateTime.now());
        LikeOutput storeOutput = new LikeOutput(1L, userId, storeId, type, LocalDateTime.now());

        given(likePort.exists(userId, storeId, type)).willReturn(false);
        given(likePort.save(any(Like.class))).willReturn(storeLike);
        given(likeMapper.toOutput(storeLike)).willReturn(storeOutput);

        // when
        LikeOutput result = likeService.addLike(userId, storeId, type);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getLikeableType()).isEqualTo(LikeableType.STORE);
        assertThat(result.getLikeableId()).isEqualTo(storeId);
    }

    @Test
    @DisplayName("판매자 팔로우 - 성공")
    void addLike_Seller_Success() {
        // given
        Long userId = 100L;
        Long sellerId = 7L;
        LikeableType type = LikeableType.SELLER;

        Like sellerLike = Like.of(1L, userId, sellerId, type, LocalDateTime.now());
        LikeOutput sellerOutput = new LikeOutput(1L, userId, sellerId, type, LocalDateTime.now());

        given(likePort.exists(userId, sellerId, type)).willReturn(false);
        given(likePort.save(any(Like.class))).willReturn(sellerLike);
        given(likeMapper.toOutput(sellerLike)).willReturn(sellerOutput);

        // when
        LikeOutput result = likeService.addLike(userId, sellerId, type);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getLikeableType()).isEqualTo(LikeableType.SELLER);
        assertThat(result.getLikeableId()).isEqualTo(sellerId);
    }

    @Test
    @DisplayName("Domain 객체 생성 검증")
    void createDomainObject() {
        // given
        Long userId = 100L;
        Long likeableId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        // when
        Like like = Like.create(userId, likeableId, type);

        // then
        assertThat(like).isNotNull();
        assertThat(like.getUserId()).isEqualTo(userId);
        assertThat(like.getLikeableId()).isEqualTo(likeableId);
        assertThat(like.getLikeableType()).isEqualTo(type);
        assertThat(like.getCreatedAt()).isNotNull();
    }

    @Test
    @DisplayName("Domain 객체 - 잘못된 userId로 생성 시 예외")
    void createDomainObject_InvalidUserId_ThrowsException() {
        // given
        Long invalidUserId = -1L;
        Long likeableId = 1L;
        LikeableType type = LikeableType.PRODUCT;

        // when & then
        assertThatThrownBy(() -> Like.create(invalidUserId, likeableId, type))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("사용자 ID");
    }

    @Test
    @DisplayName("Domain 객체 - null LikeableType으로 생성 시 예외")
    void createDomainObject_NullType_ThrowsException() {
        // given
        Long userId = 100L;
        Long likeableId = 1L;
        LikeableType type = null;

        // when & then
        assertThatThrownBy(() -> Like.create(userId, likeableId, type))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("좋아요 대상 타입");
    }
}
