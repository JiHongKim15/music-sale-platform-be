# 좋아요 도메인 면접 대비 가이드

## 🎯 핵심 메시지

**"처음부터 다시 짤 필요 없습니다!"**

지금 만든 코드는 **실제 좋은 코드**입니다.
다만, 여러분이 **왜 이렇게 만들었는지 설명**할 수 있어야 합니다.

---

## 📝 면접에서 이렇게 말하세요

### ❌ 절대 하지 말아야 할 말
```
"AI가 다 짜줬어요"
"잘 모르겠는데 그냥 작동해요"
"헥사고날 아키텍처가 뭔지 잘 모르겠어요"
```

### ✅ 이렇게 말해야 하는 말
```
"좋아요 도메인을 DDD와 헥사고날 아키텍처로 설계했습니다.
 특히 Domain Layer가 외부 의존성을 갖지 않도록 
 Port와 Adapter 패턴을 적용했습니다."
```

---

## 🎤 면접관이 물어볼 질문들

### Q1. "좋아요 도메인을 어떻게 설계했나요?"

#### 답변 예시
```
"3단계로 설계했습니다.

1단계: DB 스키마 설계
  - 다형적 관계(Polymorphic Association)를 사용했습니다.
  - 상품, 스토어, 판매자를 하나의 likes 테이블로 관리하기 위해
    likeable_type과 likeable_id 컬럼을 설계했습니다.
  - 중복 방지를 위해 (user_id, likeable_type, likeable_id)에
    Unique 제약조건을 걸었습니다.

2단계: 아키텍처 설계
  - 헥사고날 아키텍처를 적용했습니다.
  - Domain Layer가 외부 의존성을 갖지 않도록 격리했습니다.
  - Port & Adapter 패턴으로 의존성 역전을 구현했습니다.

3단계: API 설계
  - RESTful API 원칙을 따랐습니다.
  - 상품 찜, 스토어 구독, 판매자 팔로우를 
    각각 다른 엔드포인트로 분리했습니다."
```

#### 꼬리 질문 대비
**"왜 3개 테이블로 나누지 않고 1개로 합쳤나요?"**
```
"초기에는 product_likes, store_likes, seller_likes로 
 3개 테이블로 나누는 것도 고려했습니다.

하지만 다음 이유로 1개 테이블로 통합했습니다:
1. 비즈니스 로직이 완전히 동일함 (추가/삭제/조회)
2. 테이블 관리 복잡도 감소
3. 쿼리 성능은 인덱스로 충분히 해결 가능
4. 나중에 새로운 타입(예: 앨범)을 추가하기 쉬움

단점은 likeable_id가 외래키 제약조건을 걸 수 없다는 점이지만,
Application Layer에서 검증 로직으로 보완할 수 있습니다."
```

---

### Q2. "헥사고날 아키텍처가 뭔가요?"

#### 답변 예시
```
"Port & Adapter 패턴이라고도 불리며,
 비즈니스 로직을 외부 기술(DB, API)로부터 격리하는 아키텍처입니다.

제 프로젝트에서는 4개 레이어로 분리했습니다:

1. Domain Layer (핵심)
   - Like.java: 비즈니스 로직만 포함
   - 외부 의존성 0개 (JPA, Spring 없음)
   - 순수 Java로만 작성

2. Application Layer (UseCase)
   - LikeUseCase: Inbound Port (인터페이스)
   - LikePort: Outbound Port (인터페이스)
   - LikeService: 비즈니스 로직 구현

3. Infrastructure Layer (Adapter)
   - LikePersistenceAdapter: LikePort 구현체
   - JPA, MySQL 관련 기술 담당

4. API Layer (Adapter)
   - LikeController: REST API 담당
   - LikeUseCase를 주입받아 사용

이렇게 하면 나중에 MySQL을 MongoDB로 바꿔도
Domain과 Application Layer는 수정할 필요가 없습니다."
```

#### 꼬리 질문 대비
**"그럼 실제로 DB를 바꾼 적 있나요?"**
```
"아니요, 실제로 바꾼 적은 없습니다.
하지만 테스트할 때 이 구조의 장점을 느꼈습니다.

단위 테스트를 작성할 때 LikePort를 Mockito로 모킹하면
실제 DB 없이도 LikeService를 테스트할 수 있었습니다.

만약 Layered Architecture였다면
Service가 JpaRepository를 직접 주입받았을 거고,
그러면 테스트할 때 @DataJpaTest 같은 
무거운 통합 테스트를 써야 했을 겁니다."
```

---

### Q3. "왜 메서드를 3줄 이하로 제한했나요?"

#### 답변 예시
```
"Clean Code 원칙 중 '함수는 한 가지 일만 해야 한다'를 
 극단적으로 적용해봤습니다.

예를 들어, 기존 addLike() 메서드는 이랬습니다:

// Before (10줄)
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    if (likePort.exists(userId, likeableId, type)) {
        throw new LikeAlreadyExistsException("이미 찜한 상품입니다");
    }
    Like like = Like.create(userId, likeableId, type);
    Like savedLike = likePort.save(like);
    return likeMapper.toOutput(savedLike);
}

이걸 3줄로 분리했습니다:

// After (3줄)
public LikeOutput addLike(Long userId, Long likeableId, LikeableType type) {
    validateNotDuplicate(userId, likeableId, type);
    Like savedLike = saveNewLike(userId, likeableId, type);
    return likeMapper.toOutput(savedLike);
}

장점:
1. 메서드명이 주석 역할을 함
2. 테스트하기 쉬움 (각 메서드를 독립적으로 테스트)
3. 버그 찾기 쉬움 (어느 메서드에서 에러가 났는지 명확)

단점:
1. 메서드 개수가 많아짐
2. 처음 보는 사람은 파악하는데 시간이 걸림

하지만 실무에서는 '읽기 좋은 코드'가 '짧은 코드'보다 중요하다고 
판단해서 이 방식을 선택했습니다."
```

---

### Q4. "다형적 관계(Polymorphic Association)를 설명해주세요"

#### 답변 예시
```
"하나의 테이블이 여러 다른 테이블을 참조할 수 있는 구조입니다.

제 프로젝트에서는:

likes 테이블
┌────┬─────────┬───────────────┬──────────────┐
│ id │ user_id │ likeable_type │ likeable_id  │
├────┼─────────┼───────────────┼──────────────┤
│ 1  │ 100     │ PRODUCT       │ 1            │ ← products 테이블의 1번
│ 2  │ 100     │ STORE         │ 5            │ ← stores 테이블의 5번
│ 3  │ 100     │ SELLER        │ 10           │ ← users 테이블의 10번
└────┴─────────┴───────────────┴──────────────┘

likeable_type에 따라 likeable_id가 참조하는 테이블이 달라집니다.

장점:
- 테이블 개수 감소 (1개로 통합)
- 새로운 타입 추가가 쉬움 (ALBUM 추가 시 코드만 수정)

단점:
- 외래키 제약조건을 걸 수 없음
- 조인이 복잡해질 수 있음

실무에서는 trade-off를 고려해서 선택해야 합니다.
저는 '유연성'을 위해 이 방식을 선택했습니다."
```

---

### Q5. "테스트 코드는 어떻게 작성했나요?"

#### 답변 예시
```
"단위 테스트(Unit Test)를 12개 작성했습니다.

테스트 전략:
1. Given-When-Then 패턴 사용
2. Mockito로 의존성 모킹
3. 경계값 테스트 (성공/실패 케이스)

예시:
@Test
@DisplayName("상품 찜하기 - 성공")
void addLike_Success() {
    // given: 테스트 데이터 준비
    given(likePort.exists(userId, productId, type)).willReturn(false);
    given(likePort.save(any())).willReturn(testLike);
    
    // when: 실제 메서드 호출
    LikeOutput result = likeService.addLike(userId, productId, type);
    
    // then: 결과 검증
    assertThat(result).isNotNull();
    assertThat(result.getUserId()).isEqualTo(userId);
}

커버리지:
- 성공 케이스: 추가, 삭제, 조회
- 실패 케이스: 중복 에러, Not Found 에러
- 경계값: null, 음수 ID

테스트 덕분에 리팩토링할 때 안심하고 수정할 수 있었습니다."
```

---

### Q6. "인덱스는 왜 그렇게 설계했나요?"

#### 답변 예시
```
"3개의 인덱스를 설계했습니다.

1. Unique Index (중복 방지)
   CREATE UNIQUE INDEX uix_user_likeable 
   ON likes (user_id, likeable_type, likeable_id);
   
   → 같은 사용자가 같은 상품을 중복으로 찜하는 것을 DB 레벨에서 차단

2. 복합 인덱스 (내 찜 목록 조회)
   CREATE INDEX idx_user_type_created 
   ON likes (user_id, likeable_type, created_at DESC);
   
   → "내가 찜한 상품 목록을 최신순으로 조회" 쿼리 최적화
   → WHERE user_id=? AND likeable_type='PRODUCT' ORDER BY created_at DESC

3. 역방향 인덱스 (좋아요 수 집계)
   CREATE INDEX idx_likeable 
   ON likes (likeable_type, likeable_id);
   
   → "이 상품을 찜한 사람이 몇 명인지" 카운트 쿼리 최적화
   → SELECT COUNT(*) WHERE likeable_type='PRODUCT' AND likeable_id=?

인덱스 설계 시 고려사항:
- 자주 조회하는 쿼리 패턴 분석
- 인덱스가 많으면 INSERT/DELETE 성능 저하
- Cardinality가 높은 컬럼을 앞에 배치"
```

---

### Q7. "예외 처리는 어떻게 했나요?"

#### 답변 예시
```
"3가지 커스텀 예외를 만들었습니다.

1. LikeAlreadyExistsException (409 Conflict)
   - 이미 찜한 상품을 또 찜하려고 할 때
   - HTTP 409: "이미 찜한 상품입니다"

2. LikeNotFoundException (404 Not Found)
   - 찜하지 않은 상품을 취소하려고 할 때
   - HTTP 404: "찜 기록을 찾을 수 없습니다"

3. TargetNotFoundException (404 Not Found)
   - 존재하지 않는 상품을 찜하려고 할 때
   - HTTP 404: "상품을 찾을 수 없습니다"

예외 처리 전략:
- RuntimeException을 상속 (Unchecked Exception)
- GlobalExceptionHandler로 일관된 에러 응답
- HTTP 상태 코드를 명확하게 구분
- 사용자 친화적인 에러 메시지

@ExceptionHandler(LikeAlreadyExistsException.class)
public ResponseEntity<ApiResponse<Void>> handleLikeAlreadyExists(
    LikeAlreadyExistsException e
) {
    return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(ApiResponse.error("LIKE_ALREADY_EXISTS", e.getMessage()));
}"
```

---

## 💡 면접 팁

### 1. 왜 이렇게 설계했는지 "이유"를 말하세요

**❌ 나쁜 답변**
```
"헥사고날 아키텍처를 사용했습니다."
```

**✅ 좋은 답변**
```
"헥사고날 아키텍처를 사용했습니다.
 이유는 비즈니스 로직을 외부 기술로부터 격리하고,
 나중에 DB나 프레임워크를 바꿔도
 Domain Layer는 수정하지 않아도 되도록 하기 위해서입니다.
 
 실제로 테스트할 때 LikePort를 Mockito로 모킹하면
 실제 DB 없이도 LikeService를 테스트할 수 있어서
 이 구조의 장점을 직접 느꼈습니다."
```

### 2. Trade-off를 설명하세요

**❌ 나쁜 답변**
```
"이 방법이 가장 좋은 방법입니다."
```

**✅ 좋은 답변**
```
"다형적 관계 vs 3개 테이블로 분리하는 방법을 고민했습니다.

3개 테이블로 분리하면:
- 외래키 제약조건 사용 가능
- 조인이 단순함

다형적 관계는:
- 테이블 관리가 간단함
- 새로운 타입 추가가 쉬움

저는 '유연성'이 더 중요하다고 판단해서
다형적 관계를 선택했습니다."
```

### 3. 실제 경험을 말하세요

**❌ 나쁜 답변**
```
"테스트 코드를 작성했습니다."
```

**✅ 좋은 답변**
```
"테스트 코드를 작성했습니다.
 특히 메서드를 3줄로 분리한 후
 각 메서드를 독립적으로 테스트할 수 있어서
 버그를 빨리 찾을 수 있었습니다.
 
 예를 들어, validateNotDuplicate() 메서드에서
 에러가 발생하면 정확히 '중복 체크' 로직에 문제가 있다는 걸
 바로 알 수 있었습니다."
```

---

## 📚 면접 전에 꼭 다시 봐야 할 코드

### 1. Domain Layer (가장 중요!)

**Like.java**의 핵심 메서드 3개:
```java
// 1. 팩토리 메서드 (생성)
public static Like create(Long userId, Long likeableId, LikeableType type) {
    return new Like(null, userId, likeableId, type, LocalDateTime.now());
}

// 2. 비즈니스 로직 (검증)
private void validateUserId(Long userId) {
    if (isInvalidId(userId)) {
        throw new IllegalArgumentException("사용자 ID는 양수여야 합니다");
    }
}

// 3. 도메인 메서드 (비교)
public boolean isSameTarget(Long userId, Long likeableId, LikeableType type) {
    return isSameUser(userId) && isSameLikeable(likeableId) && isSameType(type);
}
```

**왜 중요한가?**
- Domain Layer에 외부 의존성(JPA, Spring)이 없다는 걸 보여줌
- 비즈니스 로직이 순수 Java로만 작성되었음을 증명

### 2. Port & Adapter

**LikePort.java (Outbound Port)**
```java
public interface LikePort {
    Like save(Like like);
    void delete(Long userId, Long likeableId, LikeableType type);
    boolean exists(Long userId, Long likeableId, LikeableType type);
    // ...
}
```

**LikePersistenceAdapter.java (Adapter)**
```java
@Repository
public class LikePersistenceAdapter implements LikePort {
    private final LikeRepository likeRepository;  // JPA
    
    @Override
    public Like save(Like like) {
        LikeEntity entity = LikeEntity.fromDomain(like);
        LikeEntity savedEntity = likeRepository.save(entity);
        return savedEntity.toDomain();
    }
}
```

**왜 중요한가?**
- 의존성 역전(Dependency Inversion)을 보여줌
- Application → Port(인터페이스) ← Infrastructure(구현체)

### 3. 테스트 코드

**LikeServiceTest.java** 중 1개만 완벽히 이해:
```java
@Test
@DisplayName("상품 찜하기 - 중복 시 예외 발생")
void addLike_AlreadyExists_ThrowsException() {
    // given: 이미 찜한 상태를 모킹
    given(likePort.exists(userId, productId, type)).willReturn(true);
    
    // when & then: 예외 발생 확인
    assertThatThrownBy(() -> likeService.addLike(userId, productId, type))
        .isInstanceOf(LikeAlreadyExistsException.class)
        .hasMessageContaining("이미");
    
    // then: save()가 호출되지 않았는지 확인
    then(likePort).should(never()).save(any());
}
```

**왜 중요한가?**
- Mockito 사용법 이해
- Given-When-Then 패턴 이해
- 경계값 테스트 (실패 케이스) 이해

---

## 🎯 면접 전날 체크리스트

### 반드시 설명할 수 있어야 할 것들

- [ ] 헥사고날 아키텍처가 뭔지 1분 안에 설명
- [ ] Domain Layer에 왜 외부 의존성이 없는지 설명
- [ ] Port와 Adapter의 차이 설명
- [ ] 다형적 관계(Polymorphic Association) 설명
- [ ] 왜 3개 테이블이 아니라 1개 테이블로 합쳤는지 설명
- [ ] Unique Index를 왜 걸었는지 설명
- [ ] 메서드 3줄 룰의 장단점 설명
- [ ] 테스트 코드 1개를 라인별로 설명
- [ ] 만약 MongoDB로 바꾼다면 어느 파일을 수정해야 하는지 설명
- [ ] CQRS를 적용하지 않은 이유 설명

### 실제로 해볼 것

- [ ] Like.java 파일을 처음부터 작성해보기
- [ ] LikePort 인터페이스를 처음부터 작성해보기
- [ ] 테스트 코드 1개를 처음부터 작성해보기
- [ ] API 엔드포인트 12개를 종이에 적어보기
- [ ] DB 스키마를 그림으로 그려보기

---

## 🚨 면접관이 싫어하는 답변

### 1. "AI가 짜줬어요"
→ **대신 이렇게**: "설계는 제가 했고, 구현하면서 Clean Code 원칙을 적용했습니다"

### 2. "잘 모르겠는데 작동해요"
→ **대신 이렇게**: "헥사고날 아키텍처를 적용해서 Domain과 Infrastructure를 분리했습니다"

### 3. "그냥 이렇게 하는 게 좋다고 해서요"
→ **대신 이렇게**: "Trade-off를 고려해서 A 방식보다 B 방식을 선택했습니다. 왜냐하면..."

### 4. "다 외웠어요"
→ **대신 이렇게**: "이 부분은 특히 신경 썼는데, 왜냐하면 실제로 [구체적인 경험]..."

---

## 💪 자신감을 가지세요!

### 여러분이 만든 코드는 좋은 코드입니다

1. ✅ DDD 원칙 준수
2. ✅ 헥사고날 아키텍처 적용
3. ✅ Clean Code 원칙 적용
4. ✅ 테스트 코드 작성
5. ✅ RESTful API 설계
6. ✅ DB 인덱스 최적화

많은 주니어 개발자들이 이 정도로 설계하지 못합니다.

### 처음부터 다시 짤 필요 없습니다!

**지금 필요한 것**:
- ❌ 코드를 다시 짜기
- ✅ 코드를 이해하고 설명할 수 있게 되기

**방법**:
1. 이 가이드를 3번 읽기
2. 코드를 직접 타이핑해보기 (복붙 금지)
3. 친구에게 설명해보기
4. 질문을 스스로 만들어서 답해보기

---

## 📞 실전 연습

### 혼자서 해볼 수 있는 연습

1. **화이트보드 연습**
   - 헥사고날 아키텍처를 그려보기
   - DB 스키마를 그려보기
   - 요청 흐름도 그려보기

2. **설명 녹음**
   - 스마트폰으로 자신이 설명하는 걸 녹음
   - 들어보고 어색한 부분 수정
   - 1분, 3분, 5분 버전으로 연습

3. **코드 리뷰 연습**
   - 자신의 코드를 리뷰하는 척하기
   - "이 부분은 왜 이렇게 했나요?" 질문하고 답하기

---

## 🎓 핵심 암기 문구

면접 전에 이것만 외우세요:

```
"좋아요 도메인을 헥사고날 아키텍처로 설계했습니다.
 Domain Layer를 순수하게 유지하기 위해 Port와 Adapter 패턴을 적용했고,
 다형적 관계를 사용해 하나의 테이블로 3가지 타입을 관리했습니다.
 
 특히 메서드를 3줄 이하로 제한해서 가독성을 높였고,
 Mockito를 사용한 단위 테스트 12개를 작성해
 리팩토링할 때 안심하고 수정할 수 있었습니다.
 
 DB 인덱스는 Unique Index로 중복을 방지하고,
 복합 인덱스로 조회 성능을 최적화했습니다."
```

이것만 1분 안에 자연스럽게 말할 수 있으면 됩니다! 🎯



