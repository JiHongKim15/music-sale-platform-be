# 좋아요 도메인 - CQRS 패턴 적용 완료 보고서

## 📅 작업 일자
- **2024-11-09 (토)**

---

## 📋 작업 개요

**좋아요 도메인을 Product 도메인 수준으로 업그레이드**  
→ **CQRS (Command-Query Responsibility Segregation) 패턴 적용**

---

## ✅ 어제 한 일 (2024-11-09 전반)

### 1. 기본 도메인 구축
- [x] Domain Layer (Java) 구현
  - `Like.java` - 도메인 모델 (불변 객체)
  - `LikeableType.java` - 좋아요 타입 Enum (PRODUCT, STORE, SELLER)

- [x] Application Layer 구현
  - `LikeUseCase.java` - Inbound Port
  - `LikePort.java` - Outbound Port
  - `LikeService.java` - 비즈니스 로직
  - `LikeMapper.java` - DTO 변환
  - 예외 클래스 3개 (LikeAlreadyExistsException, LikeNotFoundException, TargetNotFoundException)

- [x] Infrastructure Layer 구현
  - `LikeEntity.java` - JPA Entity
  - `LikeRepository.java` - Spring Data JPA Repository
  - `LikePersistenceAdapter.java` - Port 구현체

- [x] API Layer 구현
  - `LikeController.java` - REST API (12개 엔드포인트)
  - `LikeResponse.java`, `LikeStatusResponse.java` - Response DTO
  - `LikeWebMapper.java` - Web 레이어 매퍼

### 2. Clean Code 적용
- [x] **메서드 3줄 리팩토링**
  - Public 메서드 4개 모두 3줄 이하로 축소
  - Private 헬퍼 메서드 13개 추가
  - 단일 책임 원칙 준수

### 3. 테스트 및 인프라
- [x] 단위 테스트 작성
  - `LikeServiceTest.java` (12개 테스트 케이스)
- [x] MySQL DDL 작성
  - `V1__create_likes_table.sql`
  - 유니크 제약조건, 인덱스 포함
- [x] Docker 환경 구축
  - `docker-compose.yml`로 MySQL 컨테이너 실행

---

## 🔥 오늘 한 일 (2024-11-09 후반) - CQRS 패턴 적용

### 1. Command/Query UseCase 분리 ✅

#### Before (통합)
```
📂 application/like/port/inport/
  └── LikeUseCase.java  (읽기/쓰기 혼재)
```

#### After (분리)
```
📂 application/like/port/inport/
  ├── LikeCommandUseCase.java  ← 쓰기 전용 (addLike, deleteLike)
  └── LikeQueryUseCase.java    ← 읽기 전용 (getLikeStatus, getMyLikes)
```

**효과**: 읽기/쓰기 책임 명확히 분리

---

### 2. Command/Query Port 분리 ✅

#### Before (통합)
```
📂 application/like/port/outport/
  └── LikePort.java  (읽기/쓰기 혼재)
```

#### After (분리)
```
📂 application/like/port/outport/
  ├── LikeCommandPort.java  ← 쓰기 전용 (save, delete, exists)
  └── LikeQueryPort.java    ← 읽기 전용 (exists, find, count)
```

**효과**: Infrastructure 레이어의 책임 명확화

---

### 3. Command/Query Service 분리 ✅

#### Before (통합)
```java
@Service
@Transactional
public class LikeService implements LikeUseCase {
    private final LikePort likePort;  // 읽기/쓰기 혼재
    
    public LikeOutput addLike(...) { ... }         // 쓰기
    public void deleteLike(...) { ... }            // 쓰기
    public LikeStatusOutput getLikeStatus(...) { ... }  // 읽기
    public Page<Object> getMyLikes(...) { ... }    // 읽기
}
```

#### After (분리)

##### LikeCommandService.java (쓰기 전용)
```java
@Service
@Transactional
public class LikeCommandService implements LikeCommandUseCase {
    private final LikeCommandPort likeCommandPort;
    
    public LikeOutput addLike(...) {
        validateNotDuplicate(...);
        Like savedLike = saveNewLike(...);
        return likeMapper.toOutput(savedLike);
    }
    
    public void deleteLike(...) {
        validateExists(...);
        likeCommandPort.delete(...);
    }
}
```

##### LikeQueryService.java (읽기 전용)
```java
@Service
@Transactional(readOnly = true)  ← 읽기 최적화
public class LikeQueryService implements LikeQueryUseCase {
    private final LikeQueryPort likeQueryPort;
    
    public LikeStatusOutput getLikeStatus(...) {
        boolean isLiked = checkIsLiked(...);
        return new LikeStatusOutput(isLiked);
    }
    
    public Page<Object> getMyLikes(...) {
        Page<Like> likes = findLikes(...);
        return convertToOutputPage(likes);
    }
}
```

**효과**: 
- 읽기 전용 트랜잭션 최적화 (`@Transactional(readOnly = true)`)
- 코드 가독성 향상

---

### 4. Command/Query Persistence Adapter 분리 ✅

#### Before (통합)
```
📂 infrastructure/persistence/like/
  └── LikePersistenceAdapter.java  (읽기/쓰기 혼재)
```

#### After (분리)
```
📂 infrastructure/persistence/like/
  ├── LikeCommandPersistenceAdapter.java  ← 쓰기 (save, delete)
  └── LikeQueryPersistenceAdapter.java    ← 읽기 (find, count)
```

##### LikeCommandPersistenceAdapter.java
```java
@Repository
@Transactional
public class LikeCommandPersistenceAdapter implements LikeCommandPort {
    private final LikeRepository likeRepository;
    
    public Like save(Like like) { ... }
    public void delete(...) { ... }
    public boolean exists(...) { ... }  // Command 검증용
}
```

##### LikeQueryPersistenceAdapter.java
```java
@Repository
@Transactional(readOnly = true)
public class LikeQueryPersistenceAdapter implements LikeQueryPort {
    private final LikeRepository likeRepository;
    
    public boolean exists(...) { ... }  // Query 조회용
    public Page<Like> findByUserIdAndType(...) { ... }
    public long countByLikeableIdAndType(...) { ... }
}
```

**효과**: 
- 읽기/쓰기 트랜잭션 최적화
- DB 레플리케이션 시 Master/Slave 분리 가능

---

### 5. Command/Query Controller 분리 ✅

#### Before (통합)
```
📂 api/web/like/
  └── LikeController.java  (12개 엔드포인트 혼재)
```

#### After (분리)
```
📂 api/web/like/
  ├── LikeCommandController.java  ← 6개 엔드포인트 (POST, DELETE)
  └── LikeQueryController.java    ← 6개 엔드포인트 (GET)
```

##### LikeCommandController.java (쓰기 전용)
```java
@RestController
@RequestMapping("/api/v1")
public class LikeCommandController {
    private final LikeCommandUseCase likeCommandUseCase;
    
    @PostMapping("/products/{id}/likes")      // 상품 찜하기
    @DeleteMapping("/products/{id}/likes")    // 상품 찜 취소
    @PostMapping("/stores/{id}/likes")        // 스토어 구독
    @DeleteMapping("/stores/{id}/likes")      // 스토어 구독 취소
    @PostMapping("/sellers/{id}/likes")       // 판매자 팔로우
    @DeleteMapping("/sellers/{id}/likes")     // 판매자 팔로우 취소
}
```

##### LikeQueryController.java (읽기 전용)
```java
@RestController
@RequestMapping("/api/v1")
public class LikeQueryController {
    private final LikeQueryUseCase likeQueryUseCase;
    
    @GetMapping("/products/{id}/likes/status")   // 상품 찜 상태 조회
    @GetMapping("/users/me/likes/products")      // 내가 찜한 상품 목록
    @GetMapping("/stores/{id}/likes/status")     // 스토어 구독 상태
    @GetMapping("/users/me/likes/stores")        // 내 구독 스토어 목록
    @GetMapping("/sellers/{id}/likes/status")    // 판매자 팔로우 상태
    @GetMapping("/users/me/likes/sellers")       // 내 팔로우 판매자 목록
}
```

**효과**: 
- API 의도가 명확 (Command vs Query)
- 코드 네비게이션 쉬움

---

### 6. Input DTO 추가 ✅

Product 도메인처럼 Input DTO 추가

```
📂 application/like/dto/
  ├── AddLikeInput.java     ← 좋아요 추가용
  ├── DeleteLikeInput.java  ← 좋아요 삭제용
  ├── LikeOutput.java       ← 기존 (응답용)
  └── LikeStatusOutput.java ← 기존 (상태 응답용)
```

#### AddLikeInput.java
```java
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}
```

#### DeleteLikeInput.java
```java
public class DeleteLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}
```

**효과**: 파라미터 그룹화, 확장성 향상

---

### 7. 통합 테스트 추가 ✅

단위 테스트에 이어 통합 테스트 작성

```
📂 infrastructure/src/test/java/.../like/
  └── LikePersistenceAdapterIntegrationTest.java  ← 새로 추가
```

#### 테스트 케이스 (9개)
```java
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class LikePersistenceAdapterIntegrationTest {
    
    @Test
    void save_Success() { ... }
    
    @Test
    void exists_CommandPort_Success() { ... }
    
    @Test
    void exists_QueryPort_Success() { ... }
    
    @Test
    void delete_Success() { ... }
    
    @Test
    void findByUserIdAndType_Success() { ... }
    
    @Test
    void countByLikeableIdAndType_Success() { ... }
    
    @Test
    void findByType_Separation() { ... }
    
    @Test
    void save_Duplicate_ShouldThrowException() { ... }
}
```

**효과**: 
- 실제 DB(H2) 연동 검증
- CQRS 분리 후 동작 확인

---

### 8. Repository 메서드 추가 ✅

QueryPort를 위한 메서드 추가

```java
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    // 기존 메서드
    boolean existsByUserIdAndLikeableIdAndLikeableType(...);
    void deleteByUserIdAndLikeableIdAndLikeableType(...);
    Page<LikeEntity> findByUserIdAndLikeableTypeOrderByCreatedAtDesc(...);
    
    // 새로 추가 (좋아요 개수 조회)
    long countByLikeableIdAndLikeableType(Long likeableId, LikeableType type);
}
```

**효과**: "상품에 대한 좋아요 개수" 조회 가능

---

## 📊 변경 사항 요약

| 항목 | Before | After | 변경 |
|------|--------|-------|------|
| **UseCase** | 1개 (LikeUseCase) | 2개 (Command/Query) | +1 |
| **Port** | 2개 (Inport 1, Outport 1) | 4개 (Command 2, Query 2) | +2 |
| **Service** | 1개 (LikeService) | 2개 (Command/Query) | +1 |
| **Adapter** | 1개 (LikePersistenceAdapter) | 2개 (Command/Query) | +1 |
| **Controller** | 1개 (LikeController) | 2개 (Command/Query) | +1 |
| **Input DTO** | 0개 | 2개 (Add/Delete) | +2 |
| **통합 테스트** | 0개 | 1개 (9개 테스트) | +1 |
| **Repository 메서드** | 4개 | 5개 | +1 |

**총 추가 파일: 10개**  
**총 추가 테스트: 9개**

---

## 🎯 학습한 개념

### 1. CQRS 패턴이란?
```
Command-Query Responsibility Segregation
= 명령(쓰기)과 조회(읽기)의 책임 분리

Command (명령):
  - 데이터 변경 (생성, 수정, 삭제)
  - 상태 변경 (찜하기, 취소)
  - 트랜잭션 필요

Query (조회):
  - 데이터 읽기
  - 상태 조회 (찜 여부, 목록)
  - 읽기 전용 트랜잭션 최적화
```

### 2. 왜 CQRS를 적용했나?
```
1. Product 도메인과 구조 통일
   → 팀 코드 스타일 일관성

2. 읽기/쓰기 최적화
   → @Transactional(readOnly = true)
   → DB 레플리케이션 준비 (Master/Slave)

3. 코드 가독성 향상
   → 파일 하나가 작아져서 이해하기 쉬움
   → Command vs Query 의도 명확

4. 확장성 증가
   → 나중에 Redis 캐싱 추가 시 QueryService만 수정
   → 이벤트 기반 아키텍처로 전환 가능
```

### 3. 메서드 3줄 리팩토링이란?
```
하나의 메서드가 하나의 일만 하도록 분리

Before:
  addLike() {
      // 중복 검증
      if (exists(...)) throw Exception;
      
      // 생성 및 저장
      Like like = Like.create(...);
      Like saved = save(like);
      
      // 변환
      return toOutput(saved);
  }
  → 10줄, 3가지 책임 혼재

After:
  addLike() {
      validateNotDuplicate(...);
      Like savedLike = saveNewLike(...);
      return likeMapper.toOutput(savedLike);
  }
  → 3줄, 각 메서드가 단일 책임
```

### 4. Port & Adapter 패턴이란?
```
Hexagonal Architecture (헥사고날 아키텍처)

Port (인터페이스):
  - Inbound Port: UseCase (외부 → 내부)
  - Outbound Port: CommandPort, QueryPort (내부 → 외부)

Adapter (구현체):
  - Controller: Inbound Adapter (HTTP → UseCase)
  - PersistenceAdapter: Outbound Adapter (UseCase → DB)

의존성 방향:
  Controller → UseCase ← Service → Port ← Adapter
                ↑                     ↓
             Domain                 DB
```

---

## 📂 최종 파일 구조

```
music-sale-platform-be/
├── music-domain/
│   └── src/main/java/.../like/
│       ├── Like.java                    ← Domain Model
│       └── LikeableType.java            ← Enum
│
├── music-application/
│   └── src/main/java/.../like/
│       ├── port/
│       │   ├── inport/
│       │   │   ├── LikeCommandUseCase.java    ← NEW
│       │   │   └── LikeQueryUseCase.java      ← NEW
│       │   └── outport/
│       │       ├── LikeCommandPort.java       ← NEW
│       │       └── LikeQueryPort.java         ← NEW
│       ├── service/
│       │   ├── LikeCommandService.java        ← NEW
│       │   └── LikeQueryService.java          ← NEW
│       ├── dto/
│       │   ├── AddLikeInput.java              ← NEW
│       │   ├── DeleteLikeInput.java           ← NEW
│       │   ├── LikeOutput.java
│       │   └── LikeStatusOutput.java
│       ├── mapper/
│       │   └── LikeMapper.java
│       └── exception/
│           ├── LikeAlreadyExistsException.java
│           ├── LikeNotFoundException.java
│           └── TargetNotFoundException.java
│
├── music-infrastructure/
│   └── src/main/java/.../like/
│       ├── entity/
│       │   └── LikeEntity.java
│       ├── repository/
│       │   └── LikeRepository.java        (1개 메서드 추가)
│       ├── LikeCommandPersistenceAdapter.java ← NEW
│       └── LikeQueryPersistenceAdapter.java   ← NEW
│   └── src/test/java/.../like/
│       └── LikePersistenceAdapterIntegrationTest.java ← NEW
│
└── music-api/
    └── src/main/java/.../like/
        ├── LikeCommandController.java     ← NEW
        ├── LikeQueryController.java       ← NEW
        ├── response/
        │   ├── LikeResponse.java
        │   └── LikeStatusResponse.java
        └── mapper/
            └── LikeWebMapper.java
```

---

## 🔍 면접에서 설명하기

### Q: "CQRS 패턴을 적용한 이유는?"

**답변**:
```
"좋아요 도메인을 Product 도메인 수준으로 업그레이드하기 위해 CQRS 패턴을 적용했습니다.

1. 책임 분리: 
   Command(쓰기)는 데이터 변경과 검증에 집중하고,
   Query(읽기)는 조회와 성능 최적화에 집중합니다.

2. 성능 최적화:
   Query Service에 @Transactional(readOnly = true)를 적용해
   읽기 전용 트랜잭션으로 최적화했습니다.

3. 확장성:
   나중에 Redis 캐싱을 추가하거나 DB를 Master/Slave로 분리할 때
   QueryPort만 수정하면 됩니다.

4. 코드 가독성:
   파일이 작아지고, API 의도가 명확해졌습니다.
   LikeCommandController는 POST/DELETE만,
   LikeQueryController는 GET만 담당합니다."
```

### Q: "메서드 3줄 리팩토링이란?"

**답변**:
```
"Clean Code 원칙 중 '함수는 한 가지 일만 해야 한다'를 적용했습니다.

Before:
  addLike() 메서드 하나가 10줄로 검증, 생성, 저장, 변환을 모두 처리

After:
  - validateNotDuplicate(): 검증만
  - saveNewLike(): 저장만
  - likeMapper.toOutput(): 변환만
  
각 메서드가 단일 책임을 가지므로 테스트하기 쉽고,
메서드명이 주석 역할을 해서 가독성이 높아졌습니다."
```

### Q: "통합 테스트와 단위 테스트의 차이는?"

**답변**:
```
"단위 테스트는 Mockito로 의존성을 Mocking해서
비즈니스 로직만 테스트합니다 (LikeServiceTest).

통합 테스트는 실제 DB(H2)에 연결해서
JPA, Repository, Adapter까지 전체 흐름을 테스트합니다
(LikePersistenceAdapterIntegrationTest).

단위 테스트는 빠르고, 통합 테스트는 느리지만 실제 동작을 보장합니다."
```

---

## ✨ 다음 단계 (선택)

- [ ] Request DTO 추가 (Controller 레이어에서 사용)
- [ ] Redis 캐싱 적용 (조회 성능 최적화)
- [ ] Event 기반 아키텍처 고려 (Command → Event → Query)
- [ ] API 문서화 (Swagger/OpenAPI)
- [ ] 좋아요 개수 캐싱 (상품별 좋아요 개수)

---

## 📚 참고 문서

- `LOMBOK_AND_BUILDER_EXPLAINED.md` - Lombok과 Builder 패턴 설명
- `LIKE_VS_PRODUCT_COMPARISON.md` - Product와 상세 비교
- `METHOD_3LINE_RULE.md` - 메서드 3줄 규칙 상세
- `TODAY_WORK_SUMMARY.md` - 전체 흐름 설명

---

## 💬 복붙용 (슬랙/지라)

```
[어제 한 일]
• 좋아요 도메인 기본 구축 (Domain/Application/Infrastructure/API)
• Clean Code 적용 - 메서드 3줄 리팩토링
• 단위 테스트 12개 작성

[오늘 한 일]
• CQRS 패턴 적용 (Command/Query 분리)
  - UseCase, Port, Service, Adapter, Controller 모두 분리
• Input DTO 추가 (AddLikeInput, DeleteLikeInput)
• 통합 테스트 9개 작성 (실제 DB 연동)
• Repository 메서드 추가 (countByLikeableIdAndType)

[변경 사항]
• 파일 10개 추가
• 코드 가독성 향상, 읽기/쓰기 최적화
• Product 도메인과 구조 통일
```

---

끝!


