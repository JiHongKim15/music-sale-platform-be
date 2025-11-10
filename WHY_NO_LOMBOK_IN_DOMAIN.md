# 왜 Domain Layer에는 Lombok을 사용하지 않았는가?

## 📋 요약

**Domain Layer (Like.java)에는 Lombok을 의도적으로 사용하지 않았습니다.**

**이유**: 비즈니스 규칙 검증이 필수이기 때문

---

## 🚫 Lombok을 쓰면 안 되는 이유

### 문제 1: 생성자 검증을 건너뛴다

#### ❌ Lombok @AllArgsConstructor 사용 시
```java
@AllArgsConstructor  // ← 문제 발생!
public class Like {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

// 사용
Like invalidLike = new Like(-999L, -1L, null);
// ↑ 음수 userId, 음수 likeableId, null type
// ↑ 그대로 생성됨! (검증 안 됨)
```

**문제점**:
- userId가 -999 (음수)여도 생성됨
- likeableId가 -1 (음수)여도 생성됨  
- likeableType이 null이어도 생성됨
- **Domain 객체가 "항상 유효한 상태"라는 원칙 위반!**

---

#### ✅ 현재 코드 (생성자 검증 포함)
```java
public class Like {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    
    // Private 생성자 (검증 포함)
    private Like(Long id, Long userId, Long likeableId, 
                 LikeableType likeableType, LocalDateTime createdAt) {
        // 1. userId 검증
        validateUserId(userId);
        
        // 2. likeableId 검증
        validateLikeableId(likeableId);
        
        // 3. likeableType 검증
        validateLikeableType(likeableType);
        
        // 검증 통과한 경우에만 할당
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }
    
    // 팩토리 메서드로 생성 강제
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
}
```

**장점**:
- userId가 null이거나 음수면 예외 발생
- likeableId가 null이거나 음수면 예외 발생
- likeableType이 null이면 예외 발생
- **Domain 객체는 항상 유효한 상태 보장!** ✅

---

### 문제 2: @Builder도 검증을 건너뛴다

#### ❌ Lombok @Builder 사용 시
```java
@Builder
public class Like {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
}

// 사용
Like invalidLike = Like.builder()
    .userId(-999L)      // ← 음수여도 OK
    .likeableId(-1L)    // ← 음수여도 OK
    .build();           // ← likeableType이 null인데 생성됨!
```

**문제점**:
- 선택적 필드 설정이 가능해서 필수 필드를 빠뜨릴 수 있음
- 검증 로직을 넣을 방법이 없음

---

#### ✅ 현재 코드 (팩토리 메서드)
```java
public class Like {
    // Private 생성자 (외부에서 직접 생성 불가)
    private Like(...) {
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        // ...
    }
    
    // 팩토리 메서드로만 생성 가능
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
}

// 사용
Like like = Like.create(100L, 1L, LikeableType.PRODUCT);
// ↑ 잘못된 값이면 예외 발생
```

**장점**:
- 생성 방법을 팩토리 메서드로 제한
- 필수 파라미터 누락 불가
- 모든 생성 시점에 검증 보장

---

## 🎯 Domain의 핵심 원칙

### "Domain 객체는 항상 유효한 상태여야 한다"

```java
// ❌ BAD: 유효하지 않은 상태의 Domain 객체
Like invalidLike = new Like(-1L, null, LikeableType.PRODUCT);
// ↑ userId가 음수, likeableId가 null
// ↑ 이런 객체가 존재하면 안 됨!

// ✅ GOOD: 생성 시점에 검증
Like validLike = Like.create(100L, 1L, LikeableType.PRODUCT);
// ↑ 검증 통과한 경우에만 생성
// ↑ Domain 객체는 항상 유효함을 보장
```

---

## 📊 Lombok 사용 정책

| 레이어 | Lombok 사용 | 이유 |
|--------|------------|------|
| **Domain** | ❌ | 비즈니스 규칙 검증 필요 |
| **Application (DTO)** | ✅ | 단순 데이터 전달, 검증 불필요 |
| **Infrastructure (Entity)** | ✅ | DB 매핑만 담당 |
| **API (Response)** | ✅ | 단순 DTO |

---

## 🔍 실제 코드 비교

### Domain (Like.java) - Lombok 없음
```java
public class Like {
    private final Long userId;
    
    private Like(Long id, Long userId, Long likeableId, 
                 LikeableType likeableType, LocalDateTime createdAt) {
        validateUserId(userId);  // ← 검증!
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }
    
    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
    }
    
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
}
```

### DTO (LikeOutput.java) - Lombok 사용
```java
@Getter
@AllArgsConstructor
@Builder
public class LikeOutput {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
}
// ← DTO는 검증 없이 데이터만 전달하므로 Lombok OK!
```

---

## 🎓 면접 대비 답변

### Q: "Domain Layer에 Lombok을 사용하지 않은 이유는?"

**답변 (1분 버전)**:
```
"Domain 레이어는 핵심 비즈니스 로직이므로
Lombok의 @AllArgsConstructor나 @Builder를 쓰면
검증 로직을 건너뛰게 됩니다.

예를 들어 Like 도메인에서 userId는 반드시 양수여야 하는데,
Lombok을 쓰면 음수인 userId로도 객체가 생성됩니다.

Domain 객체는 '항상 유효한 상태'여야 한다는 DDD 원칙을
지키기 위해 생성자에서 직접 검증하고,
팩토리 메서드(Like.create())로 생성을 강제했습니다.

반면 DTO나 Entity는 검증이 필요 없으므로
Lombok을 적용해 코드를 41% 줄였습니다."
```

**답변 (30초 버전)**:
```
"Domain은 비즈니스 규칙 검증이 필수인데,
Lombok을 쓰면 검증을 건너뛰게 됩니다.

그래서 생성자에서 직접 검증하고,
팩토리 메서드로 생성을 강제했습니다.

DTO는 검증이 필요 없어서 Lombok을 적용했습니다."
```

---

## 💡 핵심 정리

### ❌ Domain에 Lombok을 쓰면 안 되는 이유
```
1. @AllArgsConstructor → 검증 건너뜀
2. @Builder → 선택적 필드, 검증 불가
3. Domain 원칙 위반 → "항상 유효한 상태" 보장 불가
```

### ✅ 현재 코드의 장점
```
1. 생성자에서 모든 필드 검증
2. Private 생성자 + 팩토리 메서드 → 생성 방법 제한
3. Domain 객체는 항상 유효함을 보장
4. DDD 원칙 준수
```

### 📊 결과
```
Domain: Lombok 없음 (검증 중시)
DTO/Entity/Response: Lombok 적용 (간결성 중시)

총 41% 코드 감소 + DDD 원칙 준수
```

---

## 🎯 복붙용 (슬랙/메신저)

```
[Domain Layer에 Lombok을 안 쓴 이유]

• Domain은 비즈니스 규칙 검증이 필수
• Lombok @AllArgsConstructor는 검증을 건너뜀
• 예: userId가 음수여도 객체 생성됨

[해결 방법]
• Private 생성자에서 직접 검증
• 팩토리 메서드(Like.create())로 생성 강제
• Domain 객체는 "항상 유효한 상태" 보장

[결과]
• Domain: Lombok 없음 (검증 중시)
• DTO/Entity: Lombok 적용 (간결성 중시)
• 총 41% 코드 감소 + DDD 원칙 준수
```

---

끝!


