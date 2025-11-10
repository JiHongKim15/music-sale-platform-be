# 좋아요 도메인 vs Product 도메인 비교

## ❓ 좋아요 기능 완전히 구현됐나?

**답변: 기본 기능은 완료, 하지만 Product 수준은 아님**

---

## 📊 현재 구현 상태

### ✅ 좋아요 도메인 - 구현 완료된 것

| 항목 | 상태 | 비고 |
|------|------|------|
| Domain 레이어 | ✅ | Like.java, LikeableType.java |
| Application 레이어 | ✅ | LikeService.java, LikeUseCase.java, LikePort.java |
| Infrastructure 레이어 | ✅ | LikeEntity.java, LikeRepository.java, LikePersistenceAdapter.java |
| API 레이어 | ✅ | LikeController.java (12개 엔드포인트) |
| 단위 테스트 | ✅ | LikeServiceTest.java (12개 테스트) |
| MySQL DDL | ✅ | V1__create_likes_table.sql |
| Docker 환경 | ✅ | docker-compose.yml |

---

## 🔍 Product 도메인과 비교

### 1. CQRS 패턴 분리

#### Product (있음 ✅)
```
📂 music-application/src/.../product/
  ├── port/
  │   ├── inport/
  │   │   ├── ProductCommandUseCase.kt  ← Command (쓰기)
  │   │   └── ProductQueryUseCase.kt    ← Query (읽기)
  │   └── outport/
  │       ├── ProductCommandPort.kt
  │       └── ProductQueryPort.kt
  ├── service/
  │   ├── ProductCommandService.kt
  │   └── ProductQueryService.kt
  └── dto/
      ├── CreateProductInput.kt
      ├── UpdateProductInput.kt
      └── ProductOutput.kt

📂 music-api/src/.../product/
  ├── ProductCommandController.kt  ← Command API
  └── ProductQueryController.kt    ← Query API
```

#### 좋아요 (없음 ❌)
```
📂 music-application/src/.../like/
  ├── port/
  │   ├── inport/
  │   │   └── LikeUseCase.java         ← Command/Query 혼재
  │   └── outport/
  │       └── LikePort.java            ← Command/Query 혼재
  ├── service/
  │   └── LikeService.java             ← Command/Query 혼재
  └── dto/
      ├── LikeOutput.java              ← Output만 있음
      └── LikeStatusOutput.java

📂 music-api/src/.../like/
  └── LikeController.java              ← Command/Query 혼재
```

**차이점**: Product는 **Command(쓰기)**와 **Query(읽기)**가 분리됨

---

### 2. Input DTO 존재 여부

#### Product (있음 ✅)
```kotlin
// 상품 생성용 Input
data class CreateProductInput(
    val name: String,
    val catalogId: Long,
    val price: Int,
    val sellerId: Long,
    val storeId: Long,
    val condition: ProductCondition,
    val conditionGrade: ProductConditionGrade,
    val stockQuantity: Int,
    val status: ProductStatus,
    val attributes: Map<String, Any>
)

// 상품 수정용 Input
data class UpdateProductInput(
    val id: Long,
    val name: String? = null,
    val price: Int? = null,
    val stockQuantity: Int? = null,
    ...
)

// Service에서 사용
fun createProduct(input: CreateProductInput): ProductOutput {
    // ...
}
```

#### 좋아요 (없음 ❌)
```java
// Service 메서드가 직접 파라미터 받음
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    // ...
}

// Input DTO 없음!
```

**차이점**: Product는 **Input DTO**로 파라미터를 그룹화함

---

### 3. Controller의 Request DTO

#### Product (있음 ✅)
```kotlin
// Request DTO
data class CreateProductRequest(
    val name: String,
    val catalogId: Long,
    val price: Int,
    ...
)

// Controller
@PostMapping("/products")
fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<...> {
    val input = mapper.toInput(request)  // Request → Input 변환
    val output = productCommandUseCase.createProduct(input)
    return ResponseEntity.ok(...)
}
```

#### 좋아요 (없음 ❌)
```java
// Request DTO 없음, PathVariable/Header로 직접 받음
@PostMapping("/products/{productId}/likes")
public ResponseEntity<...> likeProduct(
    @PathVariable Long productId,
    @RequestHeader("X-User-Id") Long userId
) {
    LikeOutput output = likeUseCase.addLike(userId, productId, LikeableType.PRODUCT);
    return ResponseEntity.ok(...);
}
```

**차이점**: Product는 **Request DTO**를 별도로 정의함

---

### 4. QueryDSL / jOOQ 사용

#### Product (있음 ✅)
```kotlin
// QueryDSL로 복잡한 검색 쿼리
@Repository
class ProductQueryPersistenceAdapter(
    private val queryFactory: JPAQueryFactory,
    private val jooqContext: DSLContext  // jOOQ
) : ProductQueryPort {
    
    override fun search(input: SearchProductInput): Page<Product> {
        // QueryDSL로 동적 쿼리 생성
        val query = queryFactory.selectFrom(qProduct)
            .where(
                qProduct.name.contains(input.keyword)
                    .and(qProduct.price.between(input.minPrice, input.maxPrice))
                    .and(qProduct.status.eq(ProductStatus.ACTIVE))
            )
            .orderBy(qProduct.createdAt.desc())
        
        return query.fetch()
    }
}
```

#### 좋아요 (없음 ❌)
```java
// Spring Data JPA의 메서드 이름 쿼리만 사용
@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    boolean existsByUserIdAndLikeableIdAndLikeableType(...);
    LikeEntity findByUserIdAndLikeableIdAndLikeableType(...);
    Page<LikeEntity> findByUserIdAndLikeableTypeOrderByCreatedAtDesc(...);
}
```

**차이점**: Product는 **복잡한 검색**을 위해 QueryDSL/jOOQ 사용

---

### 5. 통합 테스트

#### Product (있을 것으로 예상 ✅)
```kotlin
@SpringBootTest
@Transactional
class ProductCommandServiceIntegrationTest {
    @Autowired
    private lateinit var productCommandService: ProductCommandService
    
    @Test
    fun `상품 생성 통합 테스트`() {
        // 실제 DB에 연결해서 테스트
    }
}
```

#### 좋아요 (없음 ❌)
```
단위 테스트만 있음 (LikeServiceTest.java)
통합 테스트 없음
```

---

## 📋 좋아요 도메인에 추가하면 좋은 것들

### 1. CQRS 패턴 적용 (Priority: 중)

Product처럼 Command/Query 분리

```
Before:
  - LikeUseCase.java
  - LikeService.java
  - LikeController.java

After:
  - LikeCommandUseCase.java (addLike, deleteLike)
  - LikeQueryUseCase.java (getLikeStatus, getMyLikes)
  - LikeCommandService.java
  - LikeQueryService.java
  - LikeCommandController.java
  - LikeQueryController.java
```

**효과**: 읽기/쓰기 최적화, 코드 가독성 향상

---

### 2. Input DTO 추가 (Priority: 하)

```java
// AddLikeInput.java
public class AddLikeInput {
    private Long userId;
    private Long likeableId;
    private LikeableType likeableType;
    
    // Getter, Constructor
}

// Service
public LikeOutput addLike(AddLikeInput input) {
    validateNotDuplicate(input.getUserId(), input.getLikeableId(), input.getLikeableType());
    Like savedLike = saveNewLike(input);
    return likeMapper.toOutput(savedLike);
}
```

**효과**: 파라미터 관리 편리, 확장성 증가

---

### 3. Request DTO 추가 (Priority: 하)

```java
// AddLikeRequest.java
public class AddLikeRequest {
    private Long likeableId;
    private LikeableType likeableType;
}

// Controller
@PostMapping("/likes")
public ResponseEntity<...> addLike(
    @RequestBody AddLikeRequest request,
    @RequestHeader("X-User-Id") Long userId
) {
    AddLikeInput input = mapper.toInput(request, userId);
    LikeOutput output = likeCommandUseCase.addLike(input);
    return ResponseEntity.ok(...);
}
```

**효과**: API 스펙 명확화, Swagger 문서화 쉬움

---

### 4. 통합 테스트 추가 (Priority: 중)

```java
@SpringBootTest
@Transactional
class LikePersistenceAdapterTest {
    @Autowired
    private LikePersistenceAdapter adapter;
    
    @Test
    void 좋아요_저장_조회_테스트() {
        // 실제 MySQL에 연결해서 테스트
        Like like = Like.create(1L, 100L, LikeableType.PRODUCT);
        Like saved = adapter.save(like);
        
        assertThat(saved.getId()).isNotNull();
        assertThat(adapter.exists(1L, 100L, LikeableType.PRODUCT)).isTrue();
    }
}
```

**효과**: DB 연동 오류 사전 발견

---

### 5. 복잡한 쿼리 (선택)

좋아요는 복잡한 검색이 없으므로 **QueryDSL 불필요**

```
Product: 
  "가격 10만원~20만원, 브랜드 Apple, 상태 NEW, 
   카테고리 전자기기, 재고 있는 것만, 최신순 정렬"
  → QueryDSL 필요 ✅

좋아요:
  "내가 찜한 상품 목록"
  → Spring Data JPA로 충분 ✅
```

---

## 🎯 우선순위별 정리

| 작업 | 우선순위 | 소요 시간 | 효과 |
|------|---------|----------|------|
| **API 실제 테스트** | 🔥 필수 | 1시간 | 버그 발견 |
| **문서화 (API 명세)** | 🔥 필수 | 1시간 | 팀 협업 |
| **통합 테스트 추가** | 🟡 중 | 2시간 | 안정성 향상 |
| **CQRS 패턴 적용** | 🟡 중 | 4시간 | Product와 통일 |
| **Input DTO 추가** | 🔵 하 | 1시간 | 확장성 |
| **Request DTO 추가** | 🔵 하 | 30분 | API 스펙 명확화 |

---

## 💬 결론

### 좋아요 기능 구현 완료됐나?
```
✅ 기본 기능 (CRUD, 중복 검증, 페이징) - 완료
✅ 단위 테스트 - 완료
✅ MySQL 연동 - 완료

⚠️ Product 수준 비교
  - CQRS 패턴 분리 - 미완료
  - Input/Request DTO - 미완료
  - 통합 테스트 - 미완료
```

### Product가 계속 할 일이 있어 보이는 이유
```
1. CQRS로 Command/Query 분리됨 (파일 개수 2배)
2. Input/Request DTO가 별도로 있음 (파일 개수 증가)
3. QueryDSL/jOOQ로 복잡한 검색 기능 제공
4. 통합 테스트 있음

→ 파일이 많아서 "할 일이 많아 보임"
→ 실제로는 더 체계적으로 구성됨
```

### 좋아요도 Product 수준으로?
```
필수는 아님!

좋아요는 단순한 도메인:
  - 생성(찜하기) / 삭제(취소) / 조회(상태/목록)
  - 복잡한 검색 없음
  - QueryDSL 불필요

Product는 복잡한 도메인:
  - 생성/수정/삭제 + 복잡한 검색
  - 가격, 재고, 상태, 카테고리 등 다양한 필터
  - QueryDSL 필수

→ 좋아요는 현재 구조로도 충분함!
```

### 하지만 Product처럼 만들고 싶다면?
```
1. CQRS 패턴 적용 (4시간)
2. Input DTO 추가 (1시간)
3. 통합 테스트 추가 (2시간)

총 소요 시간: 약 7시간

효과:
  - Product와 구조 통일
  - 팀 코드 스타일 일관성
  - 면접 시 CQRS 경험 어필 가능
```

---

## 📚 관련 문서

- `LIKES_REFACTORING_GUIDE.md` - CQRS 패턴 적용 가이드
- `QUICK_START_REFACTORING.md` - 5단계 빠른 리팩토링
- `DAILY_REPORT_LIKES.md` - 오늘 할 일 정리

---

끝!


