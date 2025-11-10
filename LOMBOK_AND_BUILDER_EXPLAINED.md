# Lombok과 Builder 패턴 설명

## 1. Lombok이란? 🤖

**Lombok = 보일러플레이트 코드 자동 생성 라이브러리**

Java에서 반복적으로 작성하는 코드(Getter, Setter, 생성자, equals, hashCode 등)를  
**어노테이션(@Getter, @Setter 등)만 붙이면 컴파일 시 자동으로 생성**해주는 도구입니다.

### Before Lombok (60줄)
```java
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
    
    // 생성자
    public User(Long id, String name, String email, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
    
    // 기본 생성자
    public User() {}
    
    // Getter 4개
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    
    // Setter 4개
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setAge(int age) { this.age = age; }
    
    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
               Objects.equals(id, user.id) &&
               Objects.equals(name, user.name) &&
               Objects.equals(email, user.email);
    }
    
    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }
    
    // toString
    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", age=" + age +
               '}';
    }
}
```

### After Lombok (9줄!)
```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private int age;
}
```

**60줄 → 9줄!** 컴파일러가 나머지 코드를 자동 생성합니다.

---

## 2. Builder 패턴이란? 🏗️

**Builder = 객체를 단계별로 생성하는 디자인 패턴**

복잡한 객체를 생성할 때, **생성자에 파라미터를 10개씩 넣는 대신**  
`.name("홍길동").email("hong@example.com")` 처럼 **체이닝 방식**으로 생성합니다.

### 일반 생성자의 문제점
```java
// ❌ 파라미터가 많으면 가독성이 떨어짐
User user = new User(
    1L,           // ← 이게 뭐였더라?
    "홍길동",       // ← 순서 헷갈림
    "hong@naver.com",
    25,
    "010-1234-5678",
    "서울",
    "남자",
    true,
    LocalDateTime.now(),
    "ACTIVE"
);
```

### Builder 패턴 사용
```java
// ✅ 가독성 Good!
User user = User.builder()
    .id(1L)
    .name("홍길동")
    .email("hong@naver.com")
    .age(25)
    .phone("010-1234-5678")
    .address("서울")
    .gender("남자")
    .isActive(true)
    .createdAt(LocalDateTime.now())
    .status("ACTIVE")
    .build();
```

**순서 상관없고, 필요한 것만 설정 가능!**

---

## 3. Lombok의 @Builder

Lombok을 쓰면 Builder 패턴도 자동 생성됩니다.

### Builder를 직접 작성하면... (100줄+)
```java
public class User {
    private Long id;
    private String name;
    private String email;
    
    // Builder 클래스
    public static class Builder {
        private Long id;
        private String name;
        private String email;
        
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public User build() {
            return new User(id, name, email);
        }
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    private User(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
```

### Lombok @Builder 사용 (5줄!)
```java
@Builder
@Getter
public class User {
    private Long id;
    private String name;
    private String email;
}

// 사용
User user = User.builder()
    .id(1L)
    .name("홍길동")
    .email("hong@naver.com")
    .build();
```

---

## 4. Lombok 주요 어노테이션

| 어노테이션 | 설명 | 생성되는 코드 |
|-----------|------|--------------|
| `@Getter` | Getter 메서드 생성 | `public T getXxx()` |
| `@Setter` | Setter 메서드 생성 | `public void setXxx(T value)` |
| `@NoArgsConstructor` | 기본 생성자 | `public ClassName() {}` |
| `@AllArgsConstructor` | 모든 필드 생성자 | `public ClassName(모든 필드...)` |
| `@Builder` | 빌더 패턴 | `Builder` 내부 클래스 생성 |
| `@ToString` | toString 메서드 | `"User{id=1, name='홍길동'}"` |
| `@EqualsAndHashCode` | equals/hashCode | `equals()`, `hashCode()` |
| `@Data` | 위의 모든 것 | Getter/Setter/toString/equals/hashCode |

---

## 5. 동료가 말한 "Builder를 Lombok으로 구현"이란?

동료분은 아마 이런 상황이었을 겁니다:

### Store 도메인 (Lombok @Builder 사용)
```java
@Builder
@Getter
public class Store {
    private Long id;
    private String name;
    private String address;
    private String phone;
}

// 사용
Store store = Store.builder()
    .id(1L)
    .name("홍길동 상점")
    .address("서울시 강남구")
    .phone("010-1234-5678")
    .build();
```

**고민**: "Builder 패턴을 Lombok으로 쓰는 게 맞나? 검증은 어떻게 하지?"

---

## 6. Builder 패턴의 문제점 (Domain Layer에서)

### ❌ 검증을 건너뛸 수 있음
```java
@Builder
public class Like {
    private Long userId;
    private Long likeableId;
}

// 잘못된 데이터도 생성 가능!
Like invalidLike = Like.builder()
    .userId(-999L)  // ← 음수인데 막을 수 없음
    .build();       // ← likeableId가 null인데 생성됨
```

### ✅ 생성자로 검증 강제
```java
public class Like {
    private final Long userId;
    private final Long likeableId;
    
    // 팩토리 메서드
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
    
    // Private 생성자 (검증 포함)
    private Like(Long id, Long userId, Long likeableId, ...) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
        if (likeableId == null || likeableId <= 0) {
            throw new IllegalArgumentException("대상 ID는 양수여야 합니다");
        }
        this.userId = userId;
        this.likeableId = likeableId;
    }
}

// 사용 (잘못된 값은 예외 발생)
Like like = Like.create(-999L, 1L, LikeableType.PRODUCT);  
// ← IllegalArgumentException 발생!
```

---

## 7. 결론

### Lombok이란?
```
반복적인 코드(Getter, Setter, 생성자 등)를 어노테이션으로 자동 생성하는 라이브러리
@Getter, @Setter, @Builder 등을 붙이면 컴파일 시 코드 생성
```

### Builder 객체란?
```
복잡한 객체를 단계별로 생성하는 패턴
User.builder().name("홍길동").email("...").build() 처럼 체이닝 방식
Lombok의 @Builder를 쓰면 자동 생성됨
```

### 좋아요 도메인에서는?
```
❌ Domain(Like.java) - Lombok 안 씀 (검증 로직 필요)
✅ DTO/Response - Lombok 써도 됨 (단순 데이터 전달)
✅ Entity - Lombok 써도 됨 (DB 매핑만 담당)
```

---

끝!


