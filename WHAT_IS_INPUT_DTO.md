# Input DTO란 무엇인가?

## 📋 간단 요약

**Input DTO = Service로 전달할 파라미터를 그룹화한 객체**

---

## 🤔 왜 추가했는가?

### Product 도메인도 사용하는 패턴

Product 도메인을 보면:
```kotlin
// Product 도메인의 Input DTO
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
```

**좋아요도 Product와 동일한 패턴을 적용하기 위해 추가했습니다!**

---

## 🔍 Before / After 비교

### Before (Input DTO 없음)

```java
// Service 메서드
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    validateNotDuplicate(userId, likeableId, type);
    Like savedLike = saveNewLike(userId, likeableId, type);
    return likeMapper.toOutput(savedLike);
}

// 호출 (Controller에서)
LikeOutput output = likeCommandUseCase.addLike(
    userId,           // ← 파라미터 3개
    likeableId, 
    likeableType
);
```

**문제점**:
- 파라미터가 여러 개 (3개)
- 파라미터 추가/삭제 시 모든 호출부 수정 필요
- 파라미터 순서 헷갈림

---

### After (Input DTO 사용)

```java
// Input DTO
@Getter
@AllArgsConstructor
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

// Service 메서드
public LikeOutput addLike(AddLikeInput input) {
    validateNotDuplicate(input);
    Like savedLike = saveNewLike(input);
    return likeMapper.toOutput(savedLike);
}

// 호출 (Controller에서)
AddLikeInput input = AddLikeInput.builder()
    .userId(userId)
    .likeableId(likeableId)
    .likeableType(likeableType)
    .build();

LikeOutput output = likeCommandUseCase.addLike(input);
```

**장점**:
- 파라미터를 객체로 그룹화
- 필드 추가/삭제 시 호출부 수정 불필요
- Builder 패턴으로 가독성 향상

---

## 📦 추가한 Input DTO

### 1. AddLikeInput.java

```java
@Getter
@AllArgsConstructor
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}
```

**용도**: 좋아요 추가할 때 사용
- userId: 누가
- likeableId: 무엇을
- likeableType: 어떤 타입으로 (PRODUCT, STORE, SELLER)

---

### 2. DeleteLikeInput.java

```java
@Getter
@AllArgsConstructor
@Builder
public class DeleteLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}
```

**용도**: 좋아요 삭제할 때 사용
- AddLikeInput과 동일한 필드
- 나중에 삭제 권한 검증 등 추가 필드가 생길 수 있음

---

## 🎯 실제 사용 예시

### 현재 (Input DTO 미사용)

```java
// Controller
@PostMapping("/products/{productId}/likes")
public ResponseEntity<...> likeProduct(
    @PathVariable Long productId,
    @RequestHeader("X-User-Id") Long userId
) {
    // 직접 파라미터 전달
    LikeOutput output = likeCommandUseCase.addLike(
        userId, 
        productId, 
        LikeableType.PRODUCT
    );
    return createResponse(output);
}
```

---

### 미래 (Input DTO 사용 시)

```java
// Controller
@PostMapping("/products/{productId}/likes")
public ResponseEntity<...> likeProduct(
    @PathVariable Long productId,
    @RequestHeader("X-User-Id") Long userId
) {
    // Input DTO로 변환
    AddLikeInput input = AddLikeInput.builder()
        .userId(userId)
        .likeableId(productId)
        .likeableType(LikeableType.PRODUCT)
        .build();
    
    LikeOutput output = likeCommandUseCase.addLike(input);
    return createResponse(output);
}
```

---

## 💡 Input DTO의 장점

### 1. 확장성
```java
// Before: 파라미터 추가 시 모든 호출부 수정
public LikeOutput addLike(
    Long userId, 
    Long likeableId, 
    LikeableType type,
    String reason  // ← 새 파라미터 추가!
) { ... }

// After: Input DTO에만 필드 추가
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final String reason;  // ← 필드만 추가
}
// 호출부는 수정 불필요 (Builder 사용)
```

### 2. 가독성
```java
// Before: 파라미터 순서 헷갈림
addLike(100L, 1L, LikeableType.PRODUCT);
//      ↑ userId? likeableId?

// After: 명시적
AddLikeInput.builder()
    .userId(100L)           // ← 명확함
    .likeableId(1L)         // ← 명확함
    .likeableType(...)
    .build();
```

### 3. 검증 로직 추가 가능
```java
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    
    // 검증 로직 추가 가능
    public void validate() {
        if (userId == null) {
            throw new IllegalArgumentException("userId is required");
        }
        if (likeableId == null) {
            throw new IllegalArgumentException("likeableId is required");
        }
    }
}
```

---

## 🤷 왜 아직 사용하지 않았나?

**답변**: 현재는 파라미터가 3개뿐이라 Input DTO의 이점이 크지 않음

```java
// 현재: 파라미터 3개 (관리 가능)
addLike(userId, likeableId, likeableType)

// 만약 파라미터가 많아지면:
addLike(userId, likeableId, likeableType, reason, priority, tags, metadata, ...)
// ↑ 이럴 때 Input DTO가 필수!
```

**하지만 Product 도메인과 구조 통일을 위해 미리 추가했습니다!**

---

## 📊 Product vs 좋아요 비교

### Product (Input DTO 사용)
```kotlin
// CreateProductInput (10개 필드)
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

// Service
fun createProduct(input: CreateProductInput): ProductOutput {
    // 10개 파라미터를 하나의 객체로 받음
}
```

### 좋아요 (Input DTO 추가)
```java
// AddLikeInput (3개 필드)
@Builder
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

// Service
public LikeOutput addLike(AddLikeInput input) {
    // 3개 파라미터를 하나의 객체로 받음
}
```

**Product와 동일한 패턴! ✅**

---

## 🎓 면접 대비 답변

### Q: "Input DTO를 왜 추가했나요?"

**답변**:
```
"Input DTO는 Service로 전달할 파라미터를 그룹화한 객체입니다.

Product 도메인과 구조를 통일하기 위해 추가했습니다.

3가지 장점이 있습니다:

1. 확장성: 
   파라미터 추가 시 Input DTO에만 필드를 추가하면 되고
   호출부는 수정할 필요가 없습니다.

2. 가독성:
   Builder 패턴으로 각 필드의 의미가 명확해집니다.

3. 유지보수성:
   파라미터가 많아져도 관리가 쉽습니다.

현재는 파라미터가 3개뿐이지만,
나중에 기능이 추가될 것을 고려해 미리 구조를 잡았습니다."
```

---

## 💬 복붙용 (슬랙/메신저)

```
[Input DTO란?]
• Service로 전달할 파라미터를 그룹화한 객체
• Product 도메인과 구조 통일 목적

[추가한 것]
• AddLikeInput (좋아요 추가용)
• DeleteLikeInput (좋아요 삭제용)

[장점]
• 확장성: 파라미터 추가 시 Input DTO만 수정
• 가독성: Builder 패턴으로 명시적
• 유지보수성: 파라미터 많아져도 관리 쉬움

[현재 상태]
• 파라미터 3개뿐이라 아직 미사용
• 하지만 Product와 구조 통일 완료
```

---

## 🎯 핵심 정리

### Input DTO란?
```
Service로 전달할 파라미터를 하나의 객체로 묶은 것
```

### 왜 추가했나?
```
1. Product 도메인과 구조 통일
2. 확장성 확보 (나중에 필드 추가 쉬움)
3. 가독성 향상 (Builder 패턴)
```

### 언제 사용하나?
```
현재: 파라미터 3개 → 직접 전달 (현재 방식)
미래: 파라미터 5개 이상 → Input DTO 사용 (확장 시)
```

---

끝!


