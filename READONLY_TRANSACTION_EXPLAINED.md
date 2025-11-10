# 읽기 전용 트랜잭션 (@Transactional(readOnly = true))

## 📋 간단 요약

**읽기 전용 트랜잭션 = 데이터를 읽기만 하고 수정하지 않는다고 DB에 알려주는 최적화 설정**

---

## 🤔 트랜잭션이란?

### 일반 트랜잭션 (@Transactional)
```java
@Service
@Transactional  // ← 읽기/쓰기 모두 가능
public class LikeCommandService {
    public LikeOutput addLike(...) {
        // DB에 INSERT (쓰기)
        likeRepository.save(like);
        return output;
    }
}
```

**특징**:
- 데이터를 읽고(**SELECT**) 쓸 수 있음(**INSERT, UPDATE, DELETE**)
- DB는 "변경이 일어날 수 있다"고 가정
- 트랜잭션 종료 시 변경사항을 확정(COMMIT)하거나 취소(ROLLBACK)

---

### 읽기 전용 트랜잭션 (@Transactional(readOnly = true))
```java
@Service
@Transactional(readOnly = true)  // ← 읽기만 가능
public class LikeQueryService {
    public LikeStatusOutput getLikeStatus(...) {
        // DB에서 SELECT만 (읽기만)
        boolean isLiked = likeRepository.exists(...);
        return new LikeStatusOutput(isLiked);
    }
}
```

**특징**:
- 데이터를 읽기만 함(**SELECT만**)
- DB는 "변경이 절대 없다"고 가정
- 여러 가지 최적화 가능

---

## 🎯 왜 넣었는가?

### 1. CQRS 패턴 적용

```
CQRS = Command(쓰기) / Query(읽기) 분리

Command Service:
  @Transactional  // ← 쓰기 가능
  - addLike()
  - deleteLike()

Query Service:
  @Transactional(readOnly = true)  // ← 읽기만
  - getLikeStatus()
  - getMyLikes()
```

**Query는 데이터를 변경하지 않으므로 `readOnly = true`로 최적화!**

---

### 2. 성능 최적화

읽기 전용 트랜잭션의 장점:

#### 장점 1: Dirty Checking 생략
```java
// 일반 트랜잭션
@Transactional
public void someMethod() {
    Like like = likeRepository.findById(1L);
    // JPA는 like 객체의 변경을 계속 감시함 (Dirty Checking)
    // 트랜잭션 종료 시 변경사항 확인 → UPDATE 쿼리 생성
}

// 읽기 전용 트랜잭션
@Transactional(readOnly = true)
public void someMethod() {
    Like like = likeRepository.findById(1L);
    // JPA는 변경 감시 안 함! (성능 향상)
}
```

#### 장점 2: DB 커넥션 최적화
```
일반 트랜잭션:
  - DB는 "쓰기가 일어날 수 있다"고 가정
  - 락(Lock) 설정, 로그 기록 등 준비

읽기 전용 트랜잭션:
  - DB는 "읽기만 한다"고 가정
  - 불필요한 락, 로그 기록 생략
  - 성능 향상!
```

#### 장점 3: DB 레플리케이션 준비
```
Master DB (쓰기 전용)
  ↓
Slave DB (읽기 전용)

읽기 전용 트랜잭션 → Slave DB로 라우팅 가능
일반 트랜잭션 → Master DB로 라우팅

현재는 설정 안 했지만, 나중에 적용 가능!
```

---

## 📂 실제 코드

### 1. LikeQueryService (읽기 전용)

```java
@Service
@Transactional(readOnly = true)  // ← 클래스 레벨에 적용
public class LikeQueryService implements LikeQueryUseCase {
    private final LikeQueryPort likeQueryPort;

    // 좋아요 상태 조회 (읽기만)
    @Override
    public LikeStatusOutput getLikeStatus(Long userId, Long likeableId, LikeableType type) {
        boolean isLiked = checkIsLiked(userId, likeableId, type);
        return new LikeStatusOutput(isLiked);
    }

    // 내가 좋아요한 목록 조회 (읽기만)
    @Override
    public Page<Object> getMyLikes(Long userId, LikeableType type, Pageable pageable) {
        Page<Like> likes = findLikes(userId, type, pageable);
        return convertToOutputPage(likes);
    }
}
```

**효과**:
- 모든 메서드가 읽기만 하므로 클래스 레벨에 `readOnly = true`
- Dirty Checking 생략으로 성능 향상

---

### 2. LikeQueryPersistenceAdapter (읽기 전용)

```java
@Repository
@Transactional(readOnly = true)  // ← 클래스 레벨에 적용
public class LikeQueryPersistenceAdapter implements LikeQueryPort {
    private final LikeRepository likeRepository;

    @Override
    public boolean exists(Long userId, Long likeableId, LikeableType type) {
        // SELECT 쿼리만 실행 (읽기만)
        return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(...);
    }

    @Override
    public Page<Like> findByUserIdAndType(Long userId, LikeableType type, Pageable pageable) {
        // SELECT 쿼리만 실행 (읽기만)
        PageRequest pageRequest = createPageRequest(pageable);
        return findAndConvert(userId, type, pageRequest);
    }
}
```

**효과**:
- DB 커넥션 최적화
- 나중에 Slave DB로 라우팅 가능

---

### 3. LikeCommandService (쓰기 가능)

```java
@Service
@Transactional  // ← readOnly 없음 (쓰기 가능)
public class LikeCommandService implements LikeCommandUseCase {
    private final LikeCommandPort likeCommandPort;

    @Override
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
        validateNotDuplicate(userId, likeableId, type);
        Like savedLike = saveNewLike(userId, likeableId, type);  // ← INSERT
        return likeMapper.toOutput(savedLike);
    }

    @Override
    public void deleteLike(Long userId, Long likeableId, LikeableType type) {
        validateExists(userId, likeableId, type);
        likeCommandPort.delete(userId, likeableId, type);  // ← DELETE
    }
}
```

**효과**:
- INSERT, DELETE 가능
- 일반 트랜잭션으로 데이터 변경 허용

---

## 📊 Before / After

### Before (CQRS 적용 전)

```java
@Service
@Transactional  // ← 모든 메서드가 쓰기 가능
public class LikeService {
    public LikeOutput addLike(...) {
        // INSERT (쓰기)
    }
    
    public LikeStatusOutput getLikeStatus(...) {
        // SELECT만 (읽기만)
        // 하지만 Dirty Checking 동작 (불필요한 성능 소모)
    }
}
```

**문제점**:
- 읽기 메서드도 쓰기 가능한 트랜잭션 사용
- Dirty Checking으로 성능 저하
- DB 최적화 불가

---

### After (CQRS 적용 후)

```java
// Command (쓰기)
@Service
@Transactional  // ← 쓰기 가능
public class LikeCommandService {
    public LikeOutput addLike(...) {
        // INSERT, UPDATE, DELETE 가능
    }
}

// Query (읽기)
@Service
@Transactional(readOnly = true)  // ← 읽기만
public class LikeQueryService {
    public LikeStatusOutput getLikeStatus(...) {
        // SELECT만
        // Dirty Checking 생략 (성능 향상)
    }
}
```

**장점**:
- 읽기/쓰기 명확히 분리
- Query는 성능 최적화
- DB 레플리케이션 준비 완료

---

## 💡 장점 요약

### 1. 성능 향상
```
- Dirty Checking 생략
- DB 커넥션 최적화
- 불필요한 락, 로그 생략
```

### 2. 명확한 의도
```
readOnly = true → "이 메서드는 읽기만 합니다"
코드만 봐도 의도 파악 가능
```

### 3. 확장성
```
나중에 DB를 Master/Slave로 분리할 때:
- readOnly = true → Slave DB
- readOnly = false → Master DB

코드 수정 없이 설정만으로 가능!
```

### 4. 안전성
```
readOnly = true인 메서드에서 INSERT/UPDATE 시도 시:
- 예외 발생 (실수 방지)
```

---

## 🎓 면접 대비 답변

### Q: "readOnly = true를 왜 사용했나요?"

**답변 (1분 버전)**:
```
"CQRS 패턴을 적용하면서 Query Service에 
@Transactional(readOnly = true)를 적용했습니다.

3가지 이유가 있습니다:

1. 성능 최적화:
   읽기 전용 트랜잭션은 JPA의 Dirty Checking을 생략해서
   성능이 향상됩니다.

2. 명확한 의도:
   코드만 봐도 '이 메서드는 읽기만 한다'는 걸 알 수 있어서
   코드 가독성이 좋아집니다.

3. 확장성:
   나중에 DB를 Master/Slave로 분리할 때
   readOnly = true인 메서드는 자동으로 Slave DB로 라우팅할 수 있습니다.

Query Service는 데이터를 읽기만 하므로
readOnly = true가 적합합니다."
```

**답변 (30초 버전)**:
```
"Query Service는 데이터를 읽기만 하므로
@Transactional(readOnly = true)를 적용했습니다.

JPA의 Dirty Checking을 생략해서 성능이 향상되고,
나중에 DB 레플리케이션 시 Slave DB로 라우팅할 수 있습니다."
```

---

## 🔍 추가 예시

### 실제 SQL 쿼리 차이

#### 일반 트랜잭션
```sql
-- 1. 트랜잭션 시작
BEGIN;

-- 2. SELECT 쿼리 (락 설정 가능)
SELECT * FROM likes WHERE user_id = 100;

-- 3. JPA가 변경 감지 (Dirty Checking)
-- 객체가 변경되었는지 확인

-- 4. 변경사항이 있으면 UPDATE
UPDATE likes SET ... WHERE id = 1;

-- 5. 트랜잭션 종료
COMMIT;
```

#### 읽기 전용 트랜잭션
```sql
-- 1. 트랜잭션 시작 (읽기 전용 모드)
BEGIN READ ONLY;

-- 2. SELECT 쿼리 (락 설정 안 함)
SELECT * FROM likes WHERE user_id = 100;

-- 3. Dirty Checking 생략!

-- 4. 트랜잭션 종료 (빠름)
COMMIT;
```

**차이**:
- 락 설정 생략
- Dirty Checking 생략
- COMMIT이 빠름

---

## 💬 복붙용 (슬랙/메신저)

```
[읽기 전용 트랜잭션이란?]
• @Transactional(readOnly = true)
• 데이터를 읽기만 하고 수정하지 않는다고 DB에 알려주는 설정

[왜 넣었나?]
• CQRS 패턴: Query Service는 읽기만 하므로 최적화
• 성능 향상: Dirty Checking 생략
• 확장성: DB 레플리케이션 준비 (Slave DB 라우팅)

[어디에 적용?]
• LikeQueryService (읽기만)
• LikeQueryPersistenceAdapter (읽기만)
• LikeCommandService는 적용 안 함 (쓰기 필요)

[효과]
• 성능 향상 (JPA 최적화)
• 코드 의도 명확
• DB 레플리케이션 준비 완료
```

---

## 🎯 핵심 정리

### 개념
```
@Transactional(readOnly = true)
= "이 메서드는 데이터를 읽기만 합니다"
```

### 왜 넣었나?
```
1. Query Service는 읽기만 → 최적화 가능
2. JPA Dirty Checking 생략 → 성능 향상
3. DB 레플리케이션 준비 → Slave DB 라우팅 가능
```

### 어디에 적용?
```
✅ LikeQueryService
✅ LikeQueryPersistenceAdapter
❌ LikeCommandService (쓰기 필요)
```

---

끝!


