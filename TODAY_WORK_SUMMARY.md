# 오늘 한 일 - 메서드 3줄 리팩토링

## 1. validate가 뭔가?

**validate = 검증하다**

```java
validateNotDuplicate()  // "중복이 아닌지 검증하다"
validateExists()        // "존재하는지 검증하다"
validateUserId()        // "사용자 ID를 검증하다"
```

### 실제 코드
```java
private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
    if (isDuplicate(userId, likeableId, type)) {
        throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
    }
}
//    ↑
// "중복이 아닌지 검증"하고, 중복이면 예외 던지기
```

---

## 2. 오늘 한 일 보고 (Before/After)

### 📝 간단 버전 (슬랙/메신저용)

```
[오늘 한 일]
좋아요 도메인 - 메서드 3줄 리팩토링 완료

Before: 
- addLike() 메서드 10줄 (검증, 생성, 저장 로직이 한곳에)

After:
- addLike() 메서드 3줄로 분리
- validateNotDuplicate(), saveNewLike() 등 
  private 헬퍼 메서드로 책임 분리

효과:
- 가독성 향상 (메서드명이 주석 역할)
- 테스트 용이성 증가
```

---

### 📊 상세 버전 (일일보고/PR 설명용)

```markdown
## 메서드 3줄 리팩토링 (Clean Code 원칙 적용)

### 목적
"함수는 한 가지 일만 해야 한다" 원칙에 따라 
복잡한 메서드를 작은 단위로 분리

### Before
```java
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    // 중복 체크
    if (likePort.exists(userId, likeableId, type)) {
        throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
    }
    
    // 생성 및 저장
    Like like = Like.create(userId, likeableId, type);
    Like savedLike = likePort.save(like);
    
    // 변환
    return likeMapper.toOutput(savedLike);
}
```
- 10줄
- 3가지 책임 혼재 (검증, 저장, 변환)
- 주석 필요

### After
```java
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    validateNotDuplicate(userId, likeableId, type);
    Like savedLike = saveNewLike(userId, likeableId, type);
    return likeMapper.toOutput(savedLike);
}

private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
    if (isDuplicate(userId, likeableId, type)) {
        throw createDuplicateException(type);
    }
}

private Like saveNewLike(Long userId, Long likeableId, LikeableType type) {
    Like like = Like.create(userId, likeableId, type);
    return likePort.save(like);
}
```
- Public 메서드 3줄
- 각 메서드가 단일 책임
- 주석 불필요 (메서드명이 의미 전달)

### 결과
- LikeService 클래스 전체 리팩토링 완료
- Public 메서드 4개 모두 3줄 이하로 축소
- Private 헬퍼 메서드 13개 추가
- 테스트 코드 12개 모두 통과
```

---

## 3. 전체 구조 흐름 (Request → Response)

### 📍 실제 API 호출 흐름

```
1. 클라이언트 요청
   ↓
2. API Layer (Controller)
   ↓
3. Application Layer (Service)
   ↓
4. Infrastructure Layer (Repository)
   ↓
5. Database (MySQL)
   ↓
6. Infrastructure Layer (Entity → Domain)
   ↓
7. Application Layer (Domain → DTO)
   ↓
8. API Layer (DTO → Response)
   ↓
9. 클라이언트 응답
```

---

### 🔍 상세 흐름 (상품 찜하기 예시)

#### Step 1: 클라이언트 요청
```bash
POST /api/v1/products/1/likes
Header: X-User-Id: 100

# Postman, Axios 등으로 요청
```

---

#### Step 2: API Layer (Controller) - Request 받기
```java
📂 music-api/src/main/java/com/music/sale/web/like/LikeController.java

@RestController
@RequestMapping("/api/v1")
public class LikeController {
    private final LikeUseCase likeUseCase;  // ← Application Layer 주입
    
    @PostMapping("/products/{productId}/likes")
    public ResponseEntity<ApiResponse<LikeResponse>> likeProduct(
        @PathVariable Long productId,       // ← URL에서 1 추출
        @RequestHeader("X-User-Id") Long userId  // ← Header에서 100 추출
    ) {
        // ↓ Application Layer로 전달
        LikeOutput output = likeUseCase.addLike(userId, productId, LikeableType.PRODUCT);
        
        // ↓ DTO → Response 변환
        LikeResponse response = mapper.toResponse(output);
        
        // ↓ 클라이언트에게 응답
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(response, "LIKE_CREATED"));
    }
}
```

**역할**: HTTP 요청을 받아서 Application Layer로 전달

---

#### Step 3: Application Layer (Service) - 비즈니스 로직
```java
📂 music-application/src/main/java/com/music/sale/application/like/service/LikeService.java

@Service
@Transactional
public class LikeService implements LikeUseCase {
    private final LikePort likePort;  // ← Infrastructure Layer 주입
    
    @Override
    public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
        // 1단계: 중복 검증
        validateNotDuplicate(userId, likeableId, type);
        
        // 2단계: 도메인 생성 및 저장
        Like savedLike = saveNewLike(userId, likeableId, type);
        
        // 3단계: DTO 변환
        return likeMapper.toOutput(savedLike);
    }
    
    private void validateNotDuplicate(Long userId, Long likeableId, LikeableType type) {
        if (isDuplicate(userId, likeableId, type)) {
            throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
        }
    }
    
    private boolean isDuplicate(Long userId, Long likeableId, LikeableType type) {
        // ↓ Infrastructure Layer로 전달
        return likePort.exists(userId, likeableId, type);
    }
    
    private Like saveNewLike(Long userId, Long likeableId, LikeableType type) {
        // ↓ Domain Layer에서 생성
        Like like = Like.create(userId, likeableId, type);
        
        // ↓ Infrastructure Layer로 전달
        return likePort.save(like);
    }
}
```

**역할**: 비즈니스 로직 처리 (검증, 생성, 저장)

---

#### Step 4: Domain Layer - 비즈니스 규칙
```java
📂 music-domain/src/main/java/com/music/sale/domain/like/Like.java

public class Like {
    private final Long id;
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    private final LocalDateTime createdAt;
    
    // 팩토리 메서드
    public static Like create(Long userId, Long likeableId, LikeableType type) {
        // ↓ 생성자 호출 (내부 검증 포함)
        return new Like(null, userId, likeableId, type, LocalDateTime.now());
    }
    
    private Like(Long id, Long userId, Long likeableId, LikeableType type, LocalDateTime createdAt) {
        // 비즈니스 규칙 검증
        validateUserId(userId);
        validateLikeableId(likeableId);
        validateLikeableType(type);
        
        this.id = id;
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = type;
        this.createdAt = createdAt;
    }
    
    private void validateUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
        }
    }
}
```

**역할**: 비즈니스 규칙 검증, 도메인 객체 생성

---

#### Step 5: Infrastructure Layer (Adapter) - DB 접근
```java
📂 music-infrastructure/src/main/java/com/music/sale/persistence/like/LikePersistenceAdapter.java

@Repository
@Transactional
public class LikePersistenceAdapter implements LikePort {
    private final LikeRepository likeRepository;  // ← Spring Data JPA
    
    @Override
    public boolean exists(Long userId, Long likeableId, LikeableType type) {
        // ↓ JPA Repository로 전달
        return likeRepository.existsByUserIdAndLikeableIdAndLikeableType(
            userId, likeableId, type
        );
    }
    
    @Override
    public Like save(Like like) {
        // 1. Domain → Entity 변환
        LikeEntity entity = LikeEntity.fromDomain(like);
        
        // 2. DB 저장
        LikeEntity savedEntity = likeRepository.save(entity);
        
        // 3. Entity → Domain 변환
        return savedEntity.toDomain();
    }
}
```

**역할**: Domain ↔ Entity 변환, DB 접근

---

#### Step 6: Repository (JPA) - 실제 DB 쿼리
```java
📂 music-infrastructure/src/main/java/com/music/sale/persistence/like/repository/LikeRepository.java

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    
    // Spring Data JPA가 자동으로 쿼리 생성
    boolean existsByUserIdAndLikeableIdAndLikeableType(
        Long userId,
        Long likeableId,
        LikeableType likeableType
    );
    // ↓ 실행되는 SQL:
    // SELECT EXISTS(SELECT 1 FROM likes 
    //               WHERE user_id = 100 
    //               AND likeable_id = 1 
    //               AND likeable_type = 'PRODUCT')
}
```

**역할**: SQL 쿼리 생성 및 실행

---

#### Step 7: Database (MySQL)
```sql
-- 실행되는 SQL
SELECT EXISTS(
    SELECT 1 FROM likes 
    WHERE user_id = 100 
      AND likeable_id = 1 
      AND likeable_type = 'PRODUCT'
);  -- 결과: false (중복 아님)

-- 중복이 아니므로 INSERT 실행
INSERT INTO likes (user_id, likeable_id, likeable_type, created_at)
VALUES (100, 1, 'PRODUCT', '2024-11-06 10:00:00');
-- 결과: id=1로 생성됨
```

---

#### Step 8: Response 반환 (역순으로 돌아감)

```
Database
   ↓ (id=1 반환)
Repository
   ↓ (LikeEntity 반환)
LikePersistenceAdapter
   ↓ (Like 도메인 반환)
LikeService
   ↓ (LikeOutput DTO 반환)
LikeController
   ↓ (LikeResponse 반환)
클라이언트
```

#### 최종 Response
```json
{
  "code": "LIKE_CREATED",
  "message": "성공",
  "data": {
    "id": 1,
    "userId": 100,
    "likeableId": 1,
    "likeableType": "PRODUCT",
    "createdAt": "2024-11-06T10:00:00"
  }
}
```

---

## 📊 시각적 구조 (헥사고날 아키텍처)

```
┌─────────────────────────────────────────────────────────┐
│                   외부 세계 (클라이언트)                    │
│                  POST /products/1/likes                 │
└─────────────────────────────────────────────────────────┘
                            ↓ HTTP Request
┌─────────────────────────────────────────────────────────┐
│                 API Layer (Adapter In)                  │
│              LikeController.likeProduct()               │
│  역할: HTTP 요청 → 도메인 데이터 변환                      │
└─────────────────────────────────────────────────────────┘
                            ↓ addLike()
┌─────────────────────────────────────────────────────────┐
│              Application Layer (Service)                │
│                   LikeService.addLike()                 │
│  역할: 비즈니스 로직 (검증, 생성, 저장 오케스트레이션)       │
│                                                         │
│  ┌─────────────────────────────────────┐              │
│  │  LikeUseCase (Inbound Port)        │ ← 인터페이스  │
│  │  - addLike()                       │              │
│  │  - deleteLike()                    │              │
│  └─────────────────────────────────────┘              │
│                                                         │
│  ┌─────────────────────────────────────┐              │
│  │  LikePort (Outbound Port)          │ ← 인터페이스  │
│  │  - save()                          │              │
│  │  - exists()                        │              │
│  └─────────────────────────────────────┘              │
└─────────────────────────────────────────────────────────┘
                            ↓ save()
┌─────────────────────────────────────────────────────────┐
│                Domain Layer (순수 비즈니스)                │
│                     Like.create()                       │
│  역할: 비즈니스 규칙 검증, 도메인 객체 생성                 │
│  특징: 외부 의존성 0 (JPA, Spring 없음)                   │
└─────────────────────────────────────────────────────────┘
                            ↓ toDomain()
┌─────────────────────────────────────────────────────────┐
│           Infrastructure Layer (Adapter Out)            │
│              LikePersistenceAdapter.save()              │
│  역할: Domain ↔ Entity 변환, DB 접근                     │
└─────────────────────────────────────────────────────────┘
                            ↓ SQL
┌─────────────────────────────────────────────────────────┐
│                    Database (MySQL)                     │
│            INSERT INTO likes VALUES (...)               │
└─────────────────────────────────────────────────────────┘
```

---

## 🎯 핵심 포인트

### 1. validate = 검증
```java
validateNotDuplicate()  // 중복 아닌지 확인
validateExists()        // 존재하는지 확인
validateUserId()        // 올바른 ID인지 확인
```

### 2. 오늘 한 일 요약 (복붙용)
```
[오늘 한 일]
좋아요 도메인 - Clean Code 원칙 적용 (메서드 3줄 리팩토링)

Before: addLike() 메서드 10줄 (검증, 생성, 저장 로직이 혼재)
After: 3줄로 축소 (validateNotDuplicate, saveNewLike로 책임 분리)

효과: 가독성 향상, 테스트 용이성 증가
전체: LikeService 클래스 Public 메서드 4개 모두 3줄 이하로 완료
```

### 3. 전체 흐름 (한 줄 요약)
```
Client → Controller → Service → Domain → Adapter → Repository → MySQL
       → Controller → Client
       
Request 받기 → 비즈니스 로직 → 도메인 검증 → DB 저장 → Response 반환
```

---

## 💡 면접에서 설명하기

**면접관**: "Request부터 Response까지 흐름을 설명해주세요"

**답변**:
```
"상품 찜하기 API를 예로 들면:

1. Controller가 POST /products/1/likes 요청을 받습니다
2. Service의 addLike()를 호출합니다
3. Service는 3단계로 처리합니다:
   - validateNotDuplicate(): 중복 검증
   - saveNewLike(): 도메인 생성 및 저장
   - toOutput(): DTO 변환
4. Domain에서 Like.create()로 비즈니스 규칙 검증
5. PersistenceAdapter가 Domain을 Entity로 변환
6. Repository가 MySQL에 INSERT
7. 역순으로 올라가며 Domain → DTO → Response 변환
8. Controller가 클라이언트에게 201 Created 응답

핵심은 각 레이어가 자신의 책임만 수행한다는 점입니다.
Controller는 HTTP 처리만,
Service는 비즈니스 로직만,
Domain은 검증만,
Infrastructure는 DB 접근만 담당합니다."
```

---

끝! 이제 구조를 완벽히 이해하셨을 거예요! 🎉



