# 메서드 3줄 룰 리팩토링 가이드

## 🎯 핵심 원칙

**"하나의 메서드는 한 가지 일만 한다"**
- 4줄 이상이면 무조건 분리
- private 메서드로 추출
- 메서드명이 주석 역할

---

## 📝 실제 리팩토링 예시

### 예시 1: addLike() 메서드

#### ❌ Before (10줄) - 너무 김
```java
@Override
public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
    // 1. 중복 체크
    if (likePort.exists(userId, likeableId, likeableType)) {
        throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
    }
    
    // 2. Like 생성 및 저장
    Like like = Like.create(userId, likeableId, likeableType);
    Like savedLike = likePort.save(like);
    
    // 3. DTO 변환
    return likeMapper.toOutput(savedLike);
}
```

**문제점**:
- 한 메서드에서 3가지 일을 함 (검증, 저장, 변환)
- 주석이 필요함
- 테스트하기 어려움

---

#### ✅ After (3줄) - 딱 좋음
```java
@Override
public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
    validateNotDuplicate(userId, likeableId, likeableType);        // 1줄
    Like savedLike = saveNewLike(userId, likeableId, likeableType); // 2줄
    return likeMapper.toOutput(savedLike);                         // 3줄
}
```

**장점**:
- 메서드명이 주석 역할 (`validateNotDuplicate` = "중복 아닌지 검증")
- 각 단계가 명확히 보임
- 각 단계를 독립적으로 테스트 가능

---

### 🔍 Step 1: validateNotDuplicate() 분리

#### ❌ 처음 추출한 코드 (3줄)
```java
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
    if (likePort.exists(userId, likeableId, likeableType)) {
        throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
    }
}
```

**이것도 3줄이지만, 더 쪼갤 수 있음!**

---

#### ✅ 최종 리팩토링 (2줄)
```java
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
    if (isDuplicate(userId, likeableId, likeableType)) {       // 1줄
        throw createDuplicateException(likeableType);          // 2줄
    }
}

// 1줄짜리 헬퍼 메서드 1
private boolean isDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
    return likePort.exists(userId, likeableId, likeableType);
}

// 1줄짜리 헬퍼 메서드 2
private LikeAlreadyExistsException createDuplicateException(LikeableType type) {
    return new LikeAlreadyExistsException("이미 " + getLikeableTypeKorean(type) + "한 대상입니다.");
}
```

**왜 이렇게까지?**
- `isDuplicate()`: "중복인가?" 의미가 명확
- `createDuplicateException()`: 예외 메시지 생성 책임 분리
- 나중에 중복 체크 로직이 복잡해지면 `isDuplicate()`만 수정하면 됨

---

### 🔍 Step 2: saveNewLike() 분리

#### ❌ Before (3줄) - 그냥 두면 안 될까?
```java
private Like saveNewLike(Long userId, Long likeableId, LikeableType likeableType) {
    Like like = Like.create(userId, likeableId, likeableType);
    return likePort.save(like);
}
```

**이것도 2줄이니 더 줄일 수 있지만, 여기서 멈춤!**

왜?
- 이미 충분히 간결함
- 더 쪼개면 오히려 복잡해짐
- "새 Like 생성 및 저장"이라는 하나의 책임만 수행

---

### 예시 2: deleteLike() 메서드

#### ❌ Before (5줄)
```java
@Override
public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
    if (!likePort.exists(userId, likeableId, likeableType)) {
        throw new LikeNotFoundException("찜 기록을 찾을 수 없습니다");
    }
    likePort.delete(userId, likeableId, likeableType);
}
```

---

#### ✅ After (2줄)
```java
@Override
public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
    validateExists(userId, likeableId, likeableType);  // 1줄
    likePort.delete(userId, likeableId, likeableType); // 2줄
}

private void validateExists(Long userId, Long likeableId, LikeableType likeableType) {
    if (notExists(userId, likeableId, likeableType)) {
        throw createNotFoundException(likeableType);
    }
}

private boolean notExists(Long userId, Long likeableId, LikeableType likeableType) {
    return !likePort.exists(userId, likeableId, likeableType);
}

private LikeNotFoundException createNotFoundException(LikeableType type) {
    return new LikeNotFoundException(getLikeableTypeKorean(type) + " 기록을 찾을 수 없습니다.");
}
```

---

### 예시 3: getMyLikes() 메서드

#### ❌ Before (3줄) - 이미 3줄이네?
```java
@Override
public Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
    Page<Like> likes = likePort.findByUserIdAndType(userId, likeableType, pageable);
    return likes.map(like -> likeMapper.toOutput(like));
}
```

---

#### ✅ After (2줄) - 더 명확하게
```java
@Override
public Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
    Page<Like> likes = findLikes(userId, likeableType, pageable);
    return convertToOutputPage(likes);
}

private Page<Like> findLikes(Long userId, LikeableType likeableType, Pageable pageable) {
    return likePort.findByUserIdAndType(userId, likeableType, pageable);
}

private Page<Object> convertToOutputPage(Page<Like> likes) {
    return likes.map(this::convertToOutput);
}

private Object convertToOutput(Like like) {
    return likeMapper.toOutput(like);
}
```

**왜 이렇게까지?**
- `findLikes()`: "좋아요 목록을 찾는다"
- `convertToOutputPage()`: "Page를 DTO로 변환한다"
- 나중에 변환 로직이 복잡해지면 `convertToOutputPage()`만 수정

---

## 🧠 리팩토링 전략

### 단계 1: 주석을 찾아라
```java
// ❌ 주석이 있다 = 메서드로 분리해야 한다는 신호!
public void something() {
    // 1. 검증
    if (condition) { ... }
    
    // 2. 처리
    doSomething();
    
    // 3. 저장
    save();
}

// ✅ 주석을 메서드명으로 바꾸기
public void something() {
    validate();    // 주석 삭제!
    process();     // 주석 삭제!
    save();        // 주석 삭제!
}
```

---

### 단계 2: if문을 분리하라
```java
// ❌ Before
public void addLike(...) {
    if (likePort.exists(...)) {
        throw new Exception("...");
    }
    // 다음 로직...
}

// ✅ After
public void addLike(...) {
    validateNotDuplicate(...);  // if문 전체를 메서드로!
    // 다음 로직...
}

private void validateNotDuplicate(...) {
    if (isDuplicate(...)) {
        throw createException(...);
    }
}
```

---

### 단계 3: 변수 생성도 메서드로
```java
// ❌ Before
public LikeOutput addLike(...) {
    Like like = Like.create(...);
    Like savedLike = likePort.save(like);
    return likeMapper.toOutput(savedLike);
}

// ✅ After
public LikeOutput addLike(...) {
    Like savedLike = saveNewLike(...);  // 변수 생성도 메서드로!
    return likeMapper.toOutput(savedLike);
}

private Like saveNewLike(...) {
    Like like = Like.create(...);
    return likePort.save(like);
}
```

---

### 단계 4: 복잡한 조건도 메서드로
```java
// ❌ Before
private String getTypeName(LikeableType type) {
    if (type == LikeableType.PRODUCT) return "찜";
    if (type == LikeableType.STORE) return "구독";
    if (type == LikeableType.SELLER) return "팔로우";
    return "좋아요";
}

// ✅ After (조건을 메서드로 분리)
private String getTypeName(LikeableType type) {
    if (isProduct(type)) return "찜";
    if (isStore(type)) return "구독";
    if (isSeller(type)) return "팔로우";
    return "좋아요";
}

private boolean isProduct(LikeableType type) {
    return type == LikeableType.PRODUCT;
}

private boolean isStore(LikeableType type) {
    return type == LikeableType.STORE;
}

private boolean isSeller(LikeableType type) {
    return type == LikeableType.SELLER;
}
```

**왜?**
- `type == LikeableType.PRODUCT`보다 `isProduct(type)`이 읽기 쉬움
- 나중에 조건이 복잡해지면 `isProduct()` 안에서만 수정

---

## 🤔 언제 멈춰야 하나?

### ❌ 너무 과하게 쪼개면 안 됨
```java
// 이건 너무 과함!
private Like saveNewLike(...) {
    Like like = createLike(...);
    return saveLike(like);
}

private Like createLike(...) {
    return Like.create(...);  // 1줄인데 메서드로?
}

private Like saveLike(Like like) {
    return likePort.save(like);  // 1줄인데 메서드로?
}
```

### ✅ 적당한 선에서 멈추기
```java
// 이 정도가 적당함
private Like saveNewLike(...) {
    Like like = Like.create(...);
    return likePort.save(like);
}
```

**판단 기준**:
- 메서드가 "한 가지 책임"만 가지는가?
- 메서드명이 "무엇을 하는지" 명확한가?
- 더 쪼개면 오히려 복잡해지는가?

---

## 📊 Before vs After 전체 비교

### Before: 전통적인 방식 (40줄)
```java
@Service
public class LikeService {
    
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
        // 중복 체크
        if (likePort.exists(userId, likeableId, likeableType)) {
            String typeName = likeableType == LikeableType.PRODUCT ? "찜" : 
                             likeableType == LikeableType.STORE ? "구독" : "팔로우";
            throw new LikeAlreadyExistsException("이미 " + typeName + "한 대상입니다.");
        }
        
        // 좋아요 생성 및 저장
        Like like = Like.create(userId, likeableId, likeableType);
        Like savedLike = likePort.save(like);
        
        // DTO 변환
        return likeMapper.toOutput(savedLike);
    }
    
    public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
        // 존재 체크
        if (!likePort.exists(userId, likeableId, likeableType)) {
            String typeName = likeableType == LikeableType.PRODUCT ? "찜" : 
                             likeableType == LikeableType.STORE ? "구독" : "팔로우";
            throw new LikeNotFoundException(typeName + " 기록을 찾을 수 없습니다.");
        }
        
        // 삭제
        likePort.delete(userId, likeableId, likeableType);
    }
    
    public Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        // 조회
        Page<Like> likes = likePort.findByUserIdAndType(userId, likeableType, pageable);
        
        // DTO 변환
        return likes.map(like -> likeMapper.toOutput(like));
    }
}
```

---

### After: 3줄 룰 적용 (126줄)
```java
@Service
public class LikeService {
    
    // ============ Public 메서드 (3줄 이하) ============
    
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
        validateNotDuplicate(userId, likeableId, likeableType);
        Like savedLike = saveNewLike(userId, likeableId, likeableType);
        return likeMapper.toOutput(savedLike);
    }
    
    public void deleteLike(Long userId, Long likeableId, LikeableType likeableType) {
        validateExists(userId, likeableId, likeableType);
        likePort.delete(userId, likeableId, likeableType);
    }
    
    public Page<Object> getMyLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        Page<Like> likes = findLikes(userId, likeableId, pageable);
        return convertToOutputPage(likes);
    }
    
    // ============ Private 헬퍼 메서드 ============
    
    private void validateNotDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
        if (isDuplicate(userId, likeableId, likeableType)) {
            throw createDuplicateException(likeableType);
        }
    }
    
    private boolean isDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
        return likePort.exists(userId, likeableId, likeableType);
    }
    
    private LikeAlreadyExistsException createDuplicateException(LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + getLikeableTypeKorean(type) + "한 대상입니다.");
    }
    
    private Like saveNewLike(Long userId, Long likeableId, LikeableType likeableType) {
        Like like = Like.create(userId, likeableId, likeableType);
        return likePort.save(like);
    }
    
    private void validateExists(Long userId, Long likeableId, LikeableType likeableType) {
        if (notExists(userId, likeableId, likeableType)) {
            throw createNotFoundException(likeableType);
        }
    }
    
    private boolean notExists(Long userId, Long likeableId, LikeableType likeableType) {
        return !likePort.exists(userId, likeableId, likeableType);
    }
    
    private LikeNotFoundException createNotFoundException(LikeableType type) {
        return new LikeNotFoundException(getLikeableTypeKorean(type) + " 기록을 찾을 수 없습니다.");
    }
    
    private Page<Like> findLikes(Long userId, LikeableType likeableType, Pageable pageable) {
        return likePort.findByUserIdAndType(userId, likeableType, pageable);
    }
    
    private Page<Object> convertToOutputPage(Page<Like> likes) {
        return likes.map(this::convertToOutput);
    }
    
    private Object convertToOutput(Like like) {
        return likeMapper.toOutput(like);
    }
    
    private String getLikeableTypeKorean(LikeableType type) {
        if (isProduct(type)) return "찜";
        if (isStore(type)) return "구독";
        if (isSeller(type)) return "팔로우";
        return "좋아요";
    }
    
    private boolean isProduct(LikeableType type) {
        return type == LikeableType.PRODUCT;
    }
    
    private boolean isStore(LikeableType type) {
        return type == LikeableType.STORE;
    }
    
    private boolean isSeller(LikeableType type) {
        return type == LikeableType.SELLER;
    }
}
```

---

## 📊 결과 비교

| 항목 | Before | After |
|------|--------|-------|
| **전체 줄 수** | 40줄 | 126줄 |
| **Public 메서드** | 평균 8줄 | 평균 2.5줄 |
| **Private 메서드** | 0개 | 13개 |
| **주석** | 필요 | 불필요 (메서드명이 주석) |
| **가독성** | 중간 | 매우 높음 |
| **테스트 가능성** | 어려움 | 쉬움 |
| **버그 찾기** | 어려움 | 쉬움 |

---

## 💡 실전 팁

### 1. IntelliJ 리팩토링 기능 활용
```
1. 분리할 코드 선택
2. Cmd + Option + M (Extract Method)
3. 메서드명 입력
4. Enter
```

### 2. 메서드명 짓는 법
```java
// ❌ 나쁜 메서드명
private void check()
private void do()
private void process()

// ✅ 좋은 메서드명
private void validateNotDuplicate()   // "중복 아닌지 검증한다"
private Like saveNewLike()            // "새 좋아요를 저장한다"
private boolean isDuplicate()         // "중복인가?"
private String getTypeName()          // "타입 이름을 가져온다"
```

### 3. 리팩토링 순서
```
1. 주석 찾기 → 메서드로 분리
2. if문 찾기 → 메서드로 분리
3. 변수 생성 찾기 → 메서드로 분리
4. 4줄 이상 찾기 → 메서드로 분리
5. 테스트 실행 → 정상 작동 확인
6. 반복
```

---

## 🎯 면접에서 이렇게 말하세요

**면접관**: "메서드를 3줄로 제한한 이유가 뭔가요?"

**답변**:
```
"Clean Code 원칙 중 '함수는 한 가지 일만 해야 한다'를
 극단적으로 적용해봤습니다.

예를 들어, addLike() 메서드는 원래 10줄이었는데
validateNotDuplicate(), saveNewLike()로 분리해서 3줄로 줄였습니다.

장점은:
1. 메서드명이 주석 역할을 해서 가독성이 높아졌습니다
2. 각 메서드를 독립적으로 테스트할 수 있습니다
3. 버그가 발생하면 어느 메서드에서 에러가 났는지 바로 알 수 있습니다

단점은:
1. 메서드 개수가 많아져서 파일이 길어졌습니다

하지만 실무에서는 '읽기 좋은 코드'가 '짧은 코드'보다 중요하다고
판단해서 이 방식을 선택했습니다."
```

---

## 🚀 연습 문제

직접 리팩토링 해보세요!

```java
// 연습 1: 이 메서드를 3줄로 줄여보세요
public UserOutput registerUser(String email, String password) {
    if (userRepository.existsByEmail(email)) {
        throw new DuplicateEmailException("이미 존재하는 이메일입니다");
    }
    if (password.length() < 8) {
        throw new InvalidPasswordException("비밀번호는 8자 이상이어야 합니다");
    }
    String encodedPassword = passwordEncoder.encode(password);
    User user = User.create(email, encodedPassword);
    User savedUser = userRepository.save(user);
    return userMapper.toOutput(savedUser);
}

// 답은 다음 페이지에...
```

---

끝! 이제 3줄 룰의 달인이 되셨습니다! 🎉



