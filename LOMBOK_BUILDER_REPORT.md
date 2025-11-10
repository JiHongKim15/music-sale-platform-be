# Lombok & Builder 패턴 적용 완료 보고서

## 📅 작업 일자
- **2024-11-09 (토)**

---

## 📋 작업 개요

**좋아요 도메인에 Lombok과 Builder 패턴 적용**  
→ DTO, Response, Entity의 보일러플레이트 코드 제거  
→ 코드 간결성 향상 및 가독성 개선

---

## ✅ 적용 대상

### 1. Application Layer - DTO (4개)
- ✅ `LikeOutput.java`
- ✅ `LikeStatusOutput.java`
- ✅ `AddLikeInput.java`
- ✅ `DeleteLikeInput.java`

### 2. API Layer - Response (2개)
- ✅ `LikeResponse.java`
- ✅ `LikeStatusResponse.java`

### 3. Infrastructure Layer - Entity (1개)
- ✅ `LikeEntity.java`

### ❌ 적용하지 않은 대상
- **Domain Layer** (`Like.java`, `LikeableType.java`)
  - 이유: 비즈니스 규칙 검증이 필요하므로 생성자에서 직접 검증

---

## 🔧 적용된 Lombok 어노테이션

| 어노테이션 | 설명 | 적용 대상 |
|-----------|------|----------|
| `@Getter` | Getter 메서드 자동 생성 | DTO, Response, Entity |
| `@Setter` | Setter 메서드 자동 생성 | Entity만 |
| `@AllArgsConstructor` | 모든 필드 생성자 | DTO, Response, Entity |
| `@NoArgsConstructor(access = AccessLevel.PROTECTED)` | 기본 생성자 (JPA용) | Entity만 |
| `@Builder` | Builder 패턴 | DTO, Response, Entity |

---

## 📊 Before/After 비교

### 1. LikeOutput.java (45줄 → 24줄)

#### Before
```java
public class LikeOutput {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;

    public LikeOutput(Long id, Long userId, Long likeableId, 
                      LikeableType likeableType, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getLikeableId() { return likeableId; }
    public LikeableType getLikeableType() { return likeableType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
```

#### After
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
```

**효과**: 
- **45줄 → 24줄 (47% 감소)**
- Getter 메서드 5개 제거
- 생성자 제거 (Lombok이 자동 생성)
- Builder 패턴 추가

---

### 2. LikeStatusOutput.java (17줄 → 15줄)

#### Before
```java
public class LikeStatusOutput {
    private final boolean isLiked;

    public LikeStatusOutput(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }
}
```

#### After
```java
@Getter
@AllArgsConstructor
public class LikeStatusOutput {
    private final boolean isLiked;
}
```

**효과**: 
- **17줄 → 15줄 (12% 감소)**
- Getter, 생성자 제거

---

### 3. AddLikeInput.java (30줄 → 20줄)

#### Before
```java
public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;

    public AddLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }

    public Long getUserId() { return userId; }
    public Long getLikeableId() { return likeableId; }
    public LikeableType getLikeableType() { return likeableType; }
}
```

#### After
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

**효과**: 
- **30줄 → 20줄 (33% 감소)**
- Builder 패턴으로 확장성 향상

---

### 4. LikeEntity.java (132줄 → 69줄)

#### Before
```java
@Entity
@Table(name = "likes", uniqueConstraints = {...}, indexes = {...})
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    // ... 3개 필드 더

    protected LikeEntity() { }
    
    public LikeEntity(Long id, Long userId, ...) {
        this.id = id;
        this.userId = userId;
        // ...
    }

    // Getter 5개
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    // ...

    // Setter 5개
    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    // ...

    public Like toDomain() { ... }
    public static LikeEntity fromDomain(Like like) { ... }
    
    @PrePersist
    protected void onCreate() { ... }
}
```

#### After
```java
@Entity
@Table(name = "likes", uniqueConstraints = {...}, indexes = {...})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    // ... 3개 필드 더

    public Like toDomain() {
        return Like.of(id, userId, likeableId, likeableType, createdAt);
    }

    public static LikeEntity fromDomain(Like like) {
        return LikeEntity.builder()
                .id(like.getId())
                .userId(like.getUserId())
                .likeableId(like.getLikeableId())
                .likeableType(like.getLikeableType())
                .createdAt(like.getCreatedAt())
                .build();
    }
    
    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}
```

**효과**: 
- **132줄 → 69줄 (48% 감소)**
- Getter 5개, Setter 5개 제거
- 생성자 2개 제거
- Builder 패턴으로 fromDomain() 간결화

---

## 🎯 Builder 패턴 사용 예시

### Before (생성자)
```java
// DTO 생성
LikeOutput output = new LikeOutput(
    1L,                      // id
    100L,                    // userId
    200L,                    // likeableId
    LikeableType.PRODUCT,    // likeableType
    LocalDateTime.now()      // createdAt
);
// ← 순서 헷갈림, 파라미터가 많으면 가독성 나쁨
```

### After (Builder)
```java
// DTO 생성 (Builder 패턴)
LikeOutput output = LikeOutput.builder()
    .id(1L)
    .userId(100L)
    .likeableId(200L)
    .likeableType(LikeableType.PRODUCT)
    .createdAt(LocalDateTime.now())
    .build();
// ← 순서 상관없음, 가독성 Good!
```

---

### Entity에서 Builder 사용

#### Before
```java
public static LikeEntity fromDomain(Like like) {
    return new LikeEntity(
        like.getId(),
        like.getUserId(),
        like.getLikeableId(),
        like.getLikeableType(),
        like.getCreatedAt()
    );
}
```

#### After
```java
public static LikeEntity fromDomain(Like like) {
    return LikeEntity.builder()
        .id(like.getId())
        .userId(like.getUserId())
        .likeableId(like.getLikeableId())
        .likeableType(like.getLikeableType())
        .createdAt(like.getCreatedAt())
        .build();
}
```

**효과**: 
- 필드명이 명시되어 가독성 향상
- 순서 변경에도 안전
- 선택적 필드 설정 가능

---

## 📊 전체 통계

| 파일 | Before (줄) | After (줄) | 감소율 |
|------|-------------|------------|--------|
| `LikeOutput.java` | 45 | 24 | **47%** ↓ |
| `LikeStatusOutput.java` | 17 | 15 | 12% ↓ |
| `AddLikeInput.java` | 30 | 20 | **33%** ↓ |
| `DeleteLikeInput.java` | 30 | 20 | **33%** ↓ |
| `LikeResponse.java` | 45 | 24 | **47%** ↓ |
| `LikeStatusResponse.java` | 17 | 15 | 12% ↓ |
| `LikeEntity.java` | 132 | 69 | **48%** ↓ |
| **총합** | **316줄** | **187줄** | **41%** ↓ |

**총 129줄 감소!**

---

## 🚫 Domain Layer에 Lombok을 적용하지 않은 이유

### Like.java는 그대로 유지

```java
// ❌ Lombok @AllArgsConstructor 사용 시 문제점
@AllArgsConstructor  // ← 검증을 건너뛴 채 객체 생성됨
public class Like {
    private final Long userId;
    private final Long likeableId;
}

Like invalidLike = new Like(-999L, -1L);  
// ← 음수인데 생성됨! (검증 안 됨)
```

```java
// ✅ 현재 코드 (생성자 검증 포함)
public class Like {
    private final Long userId;
    
    private Like(Long id, Long userId, Long likeableId, ...) {
        validateUserId(userId);  // ← 검증!
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
        this.userId = userId;
        // ...
    }
    
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
}
```

**이유**:
1. **비즈니스 규칙 검증**: Domain 객체는 항상 유효한 상태여야 함
2. **불변성 보장**: 팩토리 메서드로 생성 강제
3. **명시적 코드**: 검증 로직이 명확히 보임

---

## 🎓 학습한 개념

### 1. Lombok이란?
```
반복적인 보일러플레이트 코드를 어노테이션으로 자동 생성하는 라이브러리

@Getter     → public T getXxx() { return xxx; }
@Setter     → public void setXxx(T value) { this.xxx = value; }
@AllArgsConstructor → 모든 필드를 받는 생성자
@NoArgsConstructor  → 기본 생성자
@Builder    → Builder 패턴 (체이닝)
```

### 2. Builder 패턴이란?
```
객체를 단계별로 생성하는 디자인 패턴

Before (생성자):
  User user = new User(1L, "홍길동", "email@...", 25, ...);
  ↑ 파라미터 순서 헷갈림

After (Builder):
  User user = User.builder()
      .id(1L)
      .name("홍길동")
      .email("email@...")
      .age(25)
      .build();
  ↑ 순서 상관없음, 가독성 Good!
```

### 3. 언제 Lombok을 쓰고, 안 쓰나?

| 레이어 | Lombok 사용 | 이유 |
|--------|------------|------|
| **Domain** | ❌ | 비즈니스 규칙 검증 필요 |
| **Application (DTO)** | ✅ | 단순 데이터 전달 |
| **Infrastructure (Entity)** | ✅ | JPA 매핑만 담당 |
| **API (Response)** | ✅ | 단순 DTO |

---

## 💡 장점

### 1. 코드 간결성
- **총 129줄 감소 (41% 감소)**
- Getter/Setter 반복 코드 제거
- 생성자 보일러플레이트 제거

### 2. 가독성 향상
- 필드만 보면 클래스 구조 파악 가능
- Builder 패턴으로 객체 생성 의도 명확

### 3. 유지보수성
- 필드 추가 시 Getter/Setter 자동 생성
- 리팩토링 시 실수 감소

### 4. Builder 패턴 이점
- 파라미터 순서 무관
- 선택적 필드 설정 가능
- 체이닝으로 가독성 향상

---

## 🔍 면접 대비 답변

### Q: "Lombok을 사용한 이유는?"

**답변**:
```
"DTO와 Entity에 Lombok을 적용해 보일러플레이트 코드를 제거했습니다.

총 316줄이었던 코드가 187줄로 41% 감소했고,
Getter/Setter 반복 코드가 사라져 가독성이 향상됐습니다.

특히 @Builder를 적용해 객체 생성 시 
파라미터 순서에 무관하게 가독성 좋은 코드를 작성할 수 있게 됐습니다.

다만 Domain 레이어는 비즈니스 규칙 검증이 필요해서
Lombok을 적용하지 않고 생성자에서 직접 검증합니다."
```

### Q: "Builder 패턴의 장점은?"

**답변**:
```
"Builder 패턴은 세 가지 장점이 있습니다:

1. 가독성: 
   new User(1L, "홍길동", ...) 대신
   User.builder().id(1L).name("홍길동").build()
   처럼 필드명이 명시돼서 의도가 명확합니다.

2. 유연성:
   파라미터 순서에 무관하고, 선택적 필드만 설정 가능합니다.

3. 불변성:
   final 필드와 함께 사용하면 불변 객체를 쉽게 만들 수 있습니다.

특히 Entity의 fromDomain() 메서드에서
5개 필드를 Builder로 설정하니 코드가 훨씬 깔끔해졌습니다."
```

### Q: "Domain에는 왜 Lombok을 안 썼나?"

**답변**:
```
"Domain 레이어는 핵심 비즈니스 로직이므로
Lombok의 @AllArgsConstructor를 쓰면 검증 로직을 건너뛰게 됩니다.

예를 들어 userId가 음수여도 객체가 생성되는데,
이는 Domain 객체가 '항상 유효한 상태'라는 원칙을 위반합니다.

그래서 생성자에서 직접 validateUserId()를 호출해
비즈니스 규칙을 검증하고,
팩토리 메서드(Like.create())로 생성을 강제합니다."
```

---

## 📚 참고 자료

- `LOMBOK_DECISION.md` - Lombok 사용 여부 결정 가이드
- `LOMBOK_AND_BUILDER_EXPLAINED.md` - Lombok과 Builder 상세 설명
- `CQRS_REFACTORING_REPORT.md` - CQRS 패턴 적용 보고서

---

## 💬 복붙용 (슬랙/지라)

```
[Lombok & Builder 패턴 적용]

• DTO 4개, Response 2개, Entity 1개에 Lombok 적용
• @Getter, @AllArgsConstructor, @Builder 사용
• 총 316줄 → 187줄 (41% 감소, 129줄 제거)

[주요 변경]
• LikeOutput: 45줄 → 24줄 (47% 감소)
• LikeEntity: 132줄 → 69줄 (48% 감소)
• Getter/Setter 반복 코드 제거
• Builder 패턴으로 객체 생성 가독성 향상

[제외]
• Domain Layer (Like.java) - 비즈니스 규칙 검증 필요
```

---

끝!


