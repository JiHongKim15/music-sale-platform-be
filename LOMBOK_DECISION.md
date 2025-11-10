# Lombok 사용 여부 결정 가이드

## 🤔 현재 상황

### Lombok 의존성은 이미 있음
```kotlin
// music-domain/build.gradle.kts
dependencies {
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}
```

### 하지만 기존 코드는 Lombok 안 씀
```java
// 기존 BaseEntity.java (Lombok 없음)
public abstract class BaseEntity {
    private LocalDateTime createdAt;
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
```

---

## ❓ 왜 Lombok을 사용하지 않았는가?

### 1. 기존 코드 스타일 따름
프로젝트의 `BaseEntity.java`를 보면 **Lombok 없이** Getter/Setter를 직접 작성했습니다.

```java
// music-infrastructure/../BaseEntity.java
public abstract class BaseEntity {
    private LocalDateTime createdAt;
    
    // Lombok @Getter/@Setter 대신 직접 작성
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
```

### 2. Domain 레이어의 명시성
Domain 레이어에서는 **생성자 검증 로직**이 중요한데, Lombok의 `@AllArgsConstructor`는 검증을 건너뜁니다.

```java
// 현재 Like.java (검증 포함)
public class Like {
    private final Long userId;
    
    private Like(Long id, Long userId, ...) {
        validateUserId(userId);  // ← 생성자에서 검증!
        this.userId = userId;
    }
}

// Lombok @AllArgsConstructor 사용 시
@AllArgsConstructor  // ← 검증 없이 바로 할당됨
public class Like {
    private final Long userId;
}
```

### 3. Clean Code 원칙 (명시적 코드)
- Lombok은 **숨겨진 코드**를 생성 (IDE에서만 보임)
- 순수 Java는 **모든 코드가 명시적**
- 팀원이 Lombok 플러그인 없어도 코드 이해 가능

---

## ✅ Lombok 사용해도 되는 경우

### 1. DTO/Response 클래스 (Application/API Layer)
검증 로직이 없고, 단순 데이터 전달만 하는 경우

```java
// Before (Lombok 없음)
public class LikeOutput {
    private Long id;
    private Long userId;
    private LikeableType likeableType;
    private LocalDateTime createdAt;
    
    public LikeOutput(Long id, Long userId, LikeableType type, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.likeableType = type;
        this.createdAt = createdAt;
    }
    
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public LikeableType getLikeableType() { return likeableType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}

// After (Lombok 사용)
@Getter
@AllArgsConstructor
public class LikeOutput {
    private Long id;
    private Long userId;
    private LikeableType likeableType;
    private LocalDateTime createdAt;
}
```
**25줄 → 7줄** 🎉

---

### 2. Entity 클래스 (Infrastructure Layer)
JPA Entity는 단순 DB 매핑이므로 Lombok 적합

```java
// Before (Lombok 없음)
@Entity
@Table(name = "likes")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    protected LikeEntity() {}
    
    public LikeEntity(Long id, Long userId, ...) {
        this.id = id;
        this.userId = userId;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}

// After (Lombok 사용)
@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
}
```
**30줄 → 12줄** 🎉

---

## ❌ Lombok 사용하면 안 되는 경우

### 1. Domain 클래스 (비즈니스 로직 포함)
```java
// ❌ BAD: Lombok 사용 시 검증 불가
@AllArgsConstructor
public class Like {
    private final Long userId;  // ← 음수 들어와도 막을 수 없음
}

// ✅ GOOD: 생성자에서 검증
public class Like {
    private final Long userId;
    
    private Like(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
        this.userId = userId;
    }
}
```

### 2. @Builder 사용 시 불변성 문제
```java
// ❌ BAD: 빌더 패턴으로 중간 상태 객체 생성 가능
@Builder
public class Like {
    private final Long userId;
}

Like invalidLike = Like.builder()
    .build();  // ← userId가 null인 채로 생성됨!

// ✅ GOOD: 팩토리 메서드로 생성 강제
public class Like {
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        // 검증 후 생성
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
}
```

---

## 🎯 추천 전략

| 레이어 | Lombok 사용 | 이유 |
|--------|------------|------|
| **Domain** | ❌ 사용 안 함 | 비즈니스 규칙 검증 필요 |
| **Application (DTO)** | ✅ 사용 가능 | 단순 데이터 전달 |
| **Infrastructure (Entity)** | ✅ 사용 가능 | JPA 매핑만 담당 |
| **API (Request/Response)** | ✅ 사용 가능 | 단순 DTO |

---

## 📊 Lombok 적용 전/후 비교

### 현재 Like.java (Lombok 없음)
```java
public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
    
    // 생성자 (검증 포함)
    private Like(Long id, Long userId, Long likeableId, 
                 LikeableType likeableType, LocalDateTime createdAt) {
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = resolveCreatedAt(createdAt);
    }
    
    // Getter 5개
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getLikeableId() { return likeableId; }
    public LikeableType getLikeableType() { return likeableType; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    
    // equals, hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(id, like.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

### Lombok 사용 시 (권장하지 않음 - Domain이므로)
```java
@Getter
@EqualsAndHashCode(of = "id")
public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
    
    // 생성자 (검증 포함) - 이건 직접 작성해야 함
    private Like(Long id, Long userId, Long likeableId, 
                 LikeableType likeableType, LocalDateTime createdAt) {
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(likeableType);
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
        this.createdAt = resolveCreatedAt(createdAt);
    }
}
```
→ Getter/equals/hashCode만 줄어들 뿐, **생성자는 여전히 필요**하므로 큰 이득 없음

---

## 💡 결론

### 좋아요 도메인에서 Lombok을 사용하지 않은 이유
```
1. Domain 레이어는 비즈니스 규칙 검증이 중요 (생성자 검증 필수)
2. 기존 프로젝트 코드 스타일 따름 (BaseEntity도 Lombok 없이 작성)
3. Clean Code 원칙 (명시적인 코드 선호)
4. Lombok 사용 시 큰 이득이 없음 (생성자는 어차피 직접 작성)
```

### Lombok 사용해도 되는 곳
```
✅ LikeOutput.java (DTO) - @Getter, @AllArgsConstructor
✅ LikeResponse.java (Response) - @Getter, @AllArgsConstructor
✅ LikeEntity.java (Entity) - @Getter, @Setter, @NoArgsConstructor
```

### Lombok 사용하면 안 되는 곳
```
❌ Like.java (Domain) - 비즈니스 규칙 검증 필요
❌ Service/Controller - 로직이 있으므로 명시적 코드 선호
```

---

## 🔧 Lombok 적용 예시 (DTO/Response만)

### 1. LikeOutput.java
```java
// Before
public class LikeOutput {
    private Long id;
    private Long userId;
    private Long likeableId;
    private LikeableType likeableType;
    private LocalDateTime createdAt;
    
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

// After
@Getter
@AllArgsConstructor
public class LikeOutput {
    private Long id;
    private Long userId;
    private Long likeableId;
    private LikeableType likeableType;
    private LocalDateTime createdAt;
}
```

### 2. LikeEntity.java
```java
// Before
@Entity
@Table(name = "likes")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
    
    protected LikeEntity() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    // ... 10개 getter/setter
}

// After
@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id")
    private Long userId;
}
```

---

## 🎯 최종 추천

### 현재 상태 유지 (Lombok 없음)
```
✅ 장점:
- 모든 코드가 명시적 (팀원 이해 쉬움)
- 기존 프로젝트 스타일 일관성
- Lombok 플러그인 없어도 코드 보임

❌ 단점:
- 보일러플레이트 코드 많음 (Getter/Setter 직접 작성)
```

### Lombok 일부 적용 (DTO/Entity만)
```
✅ 장점:
- DTO/Response 코드 간결해짐
- 중요한 Domain은 여전히 명시적

❌ 단점:
- 프로젝트 스타일 불일치
- Lombok 플러그인 필요
```

---

## 💬 면접에서 답변하기

**면접관**: "Lombok을 사용하지 않은 이유가 있나요?"

**답변**:
```
"Domain 레이어에서는 Lombok을 사용하지 않았습니다.

이유는 3가지입니다:

1. 비즈니스 규칙 검증: 생성자에서 userId가 양수인지, 
   likeableType이 null이 아닌지 검증하는데,
   Lombok의 @AllArgsConstructor는 검증 로직을 넣을 수 없습니다.

2. 명시적인 코드: Domain은 핵심 비즈니스 로직이므로
   모든 코드를 명시적으로 작성해서 팀원 누구나 쉽게 이해할 수 있게 했습니다.

3. 프로젝트 일관성: 기존 프로젝트의 BaseEntity도
   Lombok 없이 작성되어 있어서 스타일을 통일했습니다.

다만 DTO나 Entity처럼 단순 데이터 전달만 하는 곳에서는
Lombok을 사용하는 것도 좋은 선택이라고 생각합니다."
```

---

끝!


