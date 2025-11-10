# 메서드 호출 vs 메서드 정의

## 🎯 핵심 개념

```java
// 1. 메서드 정의 (Method Definition) - 실제 로직이 있는 곳
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
    // 실제 로직
    if (isDuplicate(userId, likeableId, type)) {
        throw createDuplicateException(type);
    }
}

// 2. 메서드 호출 (Method Call) - 위의 메서드를 실행하는 곳
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    validateNotDuplicate(userId, likeableId, type);  // ← 이게 메서드 호출!
    // ...
}
```

---

## 📝 실제 LikeService.java 코드

### 전체 구조

```java
@Service
public class LikeService {
    
    // ========================================
    // 1. Public 메서드 (다른 클래스에서 호출 가능)
    // ========================================
    
    @Override
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
        validateNotDuplicate(userId, likeableId, type);  // ← 메서드 호출
        Like savedLike = saveNewLike(userId, likeableId, type);  // ← 메서드 호출
        return likeMapper.toOutput(savedLike);  // ← 메서드 호출
    }
    //    ↑ 이게 메서드 정의
    
    // ========================================
    // 2. Private 메서드 (내부에서만 사용)
    // ========================================
    
    private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
        if (isDuplicate(userId, likeableId, type)) {  // ← 메서드 호출
            throw createDuplicateException(type);  // ← 메서드 호출
        }
    }
    //    ↑ 이게 메서드 정의
    
    private boolean isDuplicate(Long userId, Long likeableId, LikeableType type) {
        return likePort.exists(userId, likeableId, type);  // ← 메서드 호출
    }
    //    ↑ 이게 메서드 정의
    
    private LikeAlreadyExistsException createDuplicateException(LikeableType type) {
        return new LikeAlreadyExistsException("이미 " + getLikeableTypeKorean(type) + "한 대상입니다.");
    }
    //    ↑ 이게 메서드 정의
    
    private Like saveNewLike(Long userId, Long likeableId, LikeableType type) {
        Like like = Like.create(userId, likeableId, type);
        return likePort.save(like);
    }
    //    ↑ 이게 메서드 정의
}
```

---

## 🔍 자세히 보기

### 메서드 정의 (Method Definition)
```java
// 형식: [접근제어자] [반환타입] [메서드명](파라미터) { 실제 로직 }

private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
// ↑         ↑         ↑                       ↑
// 접근제어자  반환타입   메서드명                 파라미터
    
    // 여기에 실제 로직
    if (isDuplicate(userId, likeableId, type)) {
        throw createDuplicateException(type);
    }
}
```

### 메서드 호출 (Method Call)
```java
// 형식: [메서드명](인자)

validateNotDuplicate(userId, likeableId, type);
// ↑                  ↑
// 메서드명            인자 (실제 값)
```

---

## 💡 실행 흐름

### 단계별 실행

```java
// Step 1: Controller에서 Service 호출
@PostMapping("/products/{productId}/likes")
public ResponseEntity<LikeResponse> likeProduct(
    @PathVariable Long productId,
    @RequestHeader("X-User-Id") Long userId
) {
    // 여기서 addLike() 메서드 호출
    LikeOutput output = likeService.addLike(userId, productId, LikeableType.PRODUCT);
    //                              ↑
    //                    메서드 호출 (실행 시작)
    return ResponseEntity.ok(...);
}
```

```java
// Step 2: addLike() 메서드로 이동
@Override
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    // ← 여기로 실행 흐름이 들어옴
    
    // 1번째 줄: validateNotDuplicate() 호출
    validateNotDuplicate(userId, likeableId, type);
    //        ↓
    //   이 메서드로 이동
    
    // 2번째 줄: saveNewLike() 호출
    Like savedLike = saveNewLike(userId, likeableId, type);
    
    // 3번째 줄: 반환
    return likeMapper.toOutput(savedLike);
}
```

```java
// Step 3: validateNotDuplicate() 메서드로 이동
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
    // ← 여기로 실행 흐름이 들어옴
    
    // isDuplicate() 호출
    if (isDuplicate(userId, likeableId, type)) {
        //     ↓
        //  이 메서드로 이동
        
        throw createDuplicateException(type);
    }
    
    // ← 메서드 종료, addLike()로 돌아감
}
```

```java
// Step 4: isDuplicate() 메서드로 이동
private boolean isDuplicate(Long userId, Long likeableId, LikeableType type) {
    // ← 여기로 실행 흐름이 들어옴
    
    // likePort.exists() 호출 (Infrastructure Layer)
    return likePort.exists(userId, likeableId, type);
    //               ↓
    //   LikePersistenceAdapter.exists()로 이동
    
    // ← true 또는 false 반환, validateNotDuplicate()로 돌아감
}
```

---

## 📊 호출 스택 (Call Stack)

```
실행 순서:
┌─────────────────────────────────────┐
│ 1. LikeController.likeProduct()     │
│    ↓ 호출                            │
│ 2. LikeService.addLike()            │
│    ↓ 호출                            │
│ 3. LikeService.validateNotDuplicate()│
│    ↓ 호출                            │
│ 4. LikeService.isDuplicate()        │
│    ↓ 호출                            │
│ 5. LikePort.exists()                │
│    ↓ 호출                            │
│ 6. LikePersistenceAdapter.exists()  │
│    ↓ 호출                            │
│ 7. LikeRepository.exists...()       │ ← DB 조회
│    ↑ 반환 (true/false)              │
│ 6. LikePersistenceAdapter로 돌아감 │
│    ↑ 반환                            │
│ 5. LikePort로 돌아감                │
│    ↑ 반환                            │
│ 4. isDuplicate()로 돌아감           │
│    ↑ 반환                            │
│ 3. validateNotDuplicate()로 돌아감 │
│    ↑ 반환 (또는 예외 발생)          │
│ 2. addLike()로 돌아감               │
│    ↑ 반환                            │
│ 1. Controller로 돌아감              │
└─────────────────────────────────────┘
```

---

## 🎨 시각적 표현

### Before: 한 메서드에 다 있음
```java
public LikeOutput addLike(...) {
    ┌─────────────────────────────┐
    │ if (likePort.exists(...)) { │ ← 중복 체크
    │     throw new Exception();  │
    │ }                           │
    │                             │
    │ Like like = Like.create();  │ ← 생성
    │ Like saved = save(like);    │ ← 저장
    │                             │
    │ return toOutput(saved);     │ ← 변환
    └─────────────────────────────┘
}
```

### After: 메서드 호출로 분리
```java
public LikeOutput addLike(...) {
    validateNotDuplicate();  ────┐
                                 │ 메서드 호출
    Like saved = saveNewLike();──┤
                                 │
    return toOutput(saved);  ────┘
}

private void validateNotDuplicate() {
    ┌─────────────────────────────┐
    │ if (isDuplicate()) {        │
    │     throw exception;        │
    │ }                           │
    └─────────────────────────────┘
}

private Like saveNewLike() {
    ┌─────────────────────────────┐
    │ Like like = Like.create();  │
    │ return save(like);          │
    └─────────────────────────────┘
}
```

---

## 🔍 메서드 구분법

### 1. 메서드 정의 (Definition)
```java
// 특징:
// - { } 중괄호가 있음
// - 실제 로직이 있음
// - private/public 접근제어자로 시작

private void validateNotDuplicate(...) {  // ← 정의 시작
    if (isDuplicate(...)) {
        throw exception;
    }
}  // ← 정의 끝
```

### 2. 메서드 호출 (Call)
```java
// 특징:
// - 중괄호 없음
// - 세미콜론(;)으로 끝남
// - 다른 메서드 안에서 사용됨

public void addLike(...) {
    validateNotDuplicate(...);  // ← 호출 (세미콜론으로 끝)
    saveNewLike(...);           // ← 호출
}
```

---

## 💬 면접에서 설명하기

**면접관**: "validateNotDuplicate가 뭔가요?"

**답변**:
```
"validateNotDuplicate는 중복 여부를 검증하는 private 메서드입니다.

addLike() 메서드 안에서 호출되며,
실제 로직은:

1. isDuplicate() 메서드로 중복 여부 확인
2. 중복이면 예외 발생

이렇게 검증 로직을 별도 메서드로 분리한 이유는
'한 메서드는 한 가지 일만 한다'는 원칙을 지키기 위해서입니다."
```

---

## 🎯 정리

### validateNotDuplicate는:
```
✅ 메서드 이름 (Method Name)
✅ 메서드 정의가 있음 (Method Definition)
✅ 다른 곳에서 호출됨 (Method Call)

// 정의
private void validateNotDuplicate(...) {
    // 실제 로직
}

// 호출
validateNotDuplicate(userId, likeableId, type);
```

### 쉽게 기억하기
```
메서드 정의 = 요리 레시피 📖
  - "중복 검증하는 방법" 정의
  - { } 안에 실제 로직

메서드 호출 = 요리 시작 👨‍🍳
  - "지금 중복 검증 해!" 실행
  - 세미콜론(;)으로 끝
```

---

## 🔗 연관 개념

### 1. 메서드 시그니처 (Method Signature)
```java
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type)
// ↑         ↑         ↑                  ↑
// 접근제어자  반환타입   메서드명            파라미터
// 
// 시그니처 = 메서드명 + 파라미터 타입
// 즉, validateNotDuplicate(Long, Long, LikeableType)
```

### 2. 메서드 오버로딩 (Method Overloading)
```java
// 같은 이름, 다른 파라미터
private void validate(Long userId) { }
private void validate(Long userId, Long itemId) { }
private void validate(String email) { }
```

---

끝! 이제 메서드 호출과 정의의 차이를 완벽히 이해하셨을 거예요! 🎉



