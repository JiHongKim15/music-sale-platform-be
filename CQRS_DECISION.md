# 좋아요 도메인에 CQRS를 적용해야 할까? 🤔

## CQRS란?

**Command-Query Responsibility Segregation** (명령-조회 책임 분리)

### 핵심 원칙
```
명령 (Command) - 데이터 변경 (CUD)
├── POST   - 생성
├── PUT    - 수정
└── DELETE - 삭제

조회 (Query) - 데이터 읽기 (R)
└── GET    - 조회
```

---

## 좋아요 도메인 분석

### 현재 API 엔드포인트 (12개)

#### 명령 (Command) - 6개
```
POST   /api/v1/products/{productId}/likes    (상품 찜하기)
DELETE /api/v1/products/{productId}/likes    (상품 찜 취소)

POST   /api/v1/stores/{storeId}/likes        (스토어 구독)
DELETE /api/v1/stores/{storeId}/likes        (스토어 구독 취소)

POST   /api/v1/sellers/{sellerId}/likes      (판매자 팔로우)
DELETE /api/v1/sellers/{sellerId}/likes      (판매자 팔로우 취소)
```

#### 조회 (Query) - 6개
```
GET /api/v1/products/{productId}/likes/status   (상품 찜 상태 조회)
GET /api/v1/users/me/likes/products             (내 찜 목록)

GET /api/v1/stores/{storeId}/likes/status       (스토어 구독 상태 조회)
GET /api/v1/users/me/likes/stores               (내 구독 목록)

GET /api/v1/sellers/{sellerId}/likes/status     (판매자 팔로우 상태 조회)
GET /api/v1/users/me/likes/sellers              (내 팔로우 목록)
```

### 비즈니스 로직 복잡도
```
Command (명령)
├── addLike()     - 중복 체크 + 저장 (간단)
└── deleteLike()  - 존재 체크 + 삭제 (간단)

Query (조회)
├── getLikeStatus() - 존재 여부만 확인 (매우 간단)
└── getMyLikes()    - 페이징 조회 (간단)
```

**결론: 비즈니스 로직이 매우 단순함**

---

## CQRS 적용 시 장단점

### ✅ 장점

#### 1. 책임 분리 명확
```
현재
└── LikeService
    ├── addLike()        👈 명령
    ├── deleteLike()     👈 명령
    ├── getLikeStatus()  👈 조회
    └── getMyLikes()     👈 조회
    (명령과 조회가 섞여있음)

CQRS 적용 후
├── LikeCommandService
│   ├── addLike()
│   └── deleteLike()
│
└── LikeQueryService
    ├── getLikeStatus()
    └── getMyLikes()
    (명령과 조회가 완전히 분리)
```

#### 2. 트랜잭션 최적화
```java
// Command Service
@Transactional  // 읽기+쓰기 트랜잭션
public class LikeCommandService { }

// Query Service
@Transactional(readOnly = true)  // 읽기 전용 (성능 ↑)
public class LikeQueryService { }
```

#### 3. 팀 코딩 컨벤션 통일
- 상품 도메인이 CQRS를 사용한다면, 좋아요 도메인도 맞추는 게 일관성 유지에 좋음

#### 4. 확장성
- 나중에 조회 성능이 중요해지면 Query Service만 별도로 최적화 가능
- Redis 캐싱을 Query Service에만 적용 가능

---

### ❌ 단점

#### 1. 파일 개수 2배 증가
```
현재 (3개 파일)
├── LikeUseCase.java
├── LikeService.java
└── LikeController.java

CQRS 후 (6개 파일)
├── LikeCommandUseCase.java
├── LikeQueryUseCase.java
├── LikeCommandService.java
├── LikeQueryService.java
├── LikeCommandController.java
└── LikeQueryController.java
```

#### 2. 코드 복잡도 증가
- 파일이 많아져서 찾기 어려움
- 간단한 도메인에는 오버엔지니어링

#### 3. 작업 시간 증가
- 리팩토링에 약 8시간 소요
- 테스트 코드도 2배로 늘어남

#### 4. 유지보수 포인트 증가
- 수정할 때 Command/Query 2곳을 확인해야 함

---

## 좋아요 도메인에 CQRS가 필요한가?

### ❌ CQRS 불필요 (추천)

#### 이유
1. **비즈니스 로직이 매우 단순**
   - 단순 CRUD만 있음
   - 복잡한 도메인 로직 없음
   - 트랜잭션도 단순함

2. **성능 이슈 없음**
   - 조회 쿼리가 간단함 (단일 테이블)
   - 인덱스만 잘 잡으면 충분히 빠름

3. **코드 가독성**
   - 파일 개수가 적을수록 이해하기 쉬움
   - 신입 개발자도 쉽게 파악 가능

4. **유지보수 효율**
   - 수정할 파일이 적어서 빠른 수정 가능

### ✅ CQRS 적용 고려

#### 이런 경우에만 적용
1. **팀 컨벤션**
   - 모든 도메인이 CQRS를 사용하는 경우
   - 팀 규칙으로 정해진 경우

2. **미래 확장성**
   - 좋아요 기능이 복잡해질 예정
   - 조회 성능이 매우 중요한 서비스
   - 읽기/쓰기 DB 분리 계획이 있는 경우

---

## CQRS가 필요한 도메인 vs 불필요한 도메인

### CQRS 적용이 좋은 도메인 (복잡한 도메인)

#### 1. 주문 (Order) 도메인
```
Command
├── createOrder()         - 재고 확인, 결제, 주문 생성
├── cancelOrder()         - 환불, 재고 복구
├── updateShippingInfo()  - 배송지 변경
└── confirmOrder()        - 주문 확정

Query
├── getOrderDetail()      - 주문 상세 (조인 5개 이상)
├── getMyOrders()         - 내 주문 목록 (페이징 + 검색)
├── getOrderStatistics()  - 주문 통계
└── searchOrders()        - 복잡한 검색 조건

👉 비즈니스 로직이 복잡하고, 조회 쿼리도 복잡함
   → CQRS 적용 필수!
```

#### 2. 결제 (Payment) 도메인
```
Command
├── requestPayment()      - PG사 연동, 트랜잭션 처리
├── confirmPayment()      - 결제 승인
├── refundPayment()       - 환불 처리
└── cancelPayment()       - 결제 취소

Query
├── getPaymentStatus()    - 결제 상태 조회 (실시간)
├── getPaymentHistory()   - 결제 이력 (복잡한 필터)
└── getPaymentStats()     - 결제 통계 (집계 쿼리)

👉 금융 트랜잭션이므로 명령과 조회를 명확히 분리해야 함
   → CQRS 적용 필수!
```

### CQRS 불필요한 도메인 (단순한 도메인)

#### 1. 좋아요 (Like) 도메인 ← 지금 여기!
```
Command
├── addLike()     - 중복 체크 + INSERT (간단)
└── deleteLike()  - 존재 체크 + DELETE (간단)

Query
├── getLikeStatus() - 단순 존재 체크 (SELECT 1개)
└── getMyLikes()    - 단순 페이징 (SELECT 1개)

👉 비즈니스 로직 단순, 조회도 단순
   → CQRS 불필요!
```

#### 2. 카테고리 (Category) 도메인
```
Command
├── createCategory()  - INSERT
├── updateCategory()  - UPDATE
└── deleteCategory()  - DELETE

Query
├── getCategoryById()     - SELECT (PK 조회)
└── getAllCategories()    - SELECT (전체 조회)

👉 CRUD만 있음
   → CQRS 불필요!
```

---

## 최종 판단 기준

```
┌─────────────────────────────────────────┐
│   좋아요 도메인에 CQRS를 적용할까?     │
└─────────────────────────────────────────┘

1. 팀 컨벤션이 "모든 도메인 CQRS 적용"인가?
   ├─ YES → CQRS 적용 (팀 규칙 준수)
   └─ NO  → 2번으로

2. 비즈니스 로직이 복잡한가? (트랜잭션, 외부 API 호출 등)
   ├─ YES → CQRS 적용
   └─ NO  → 3번으로

3. 조회 성능이 매우 중요한가? (복잡한 조인, 집계 쿼리)
   ├─ YES → CQRS 적용
   └─ NO  → CQRS 불필요 ✅
```

---

## 제 추천

### 좋아요 도메인: CQRS 불필요 ✅

#### 이유
1. ✅ **현재 상태가 이미 완벽함**
   - DDD 원칙 준수
   - 헥사고날 아키텍처 적용
   - 메서드 3줄 룰 준수
   - 테스트 코드 12개 완성

2. ✅ **비즈니스 로직이 단순함**
   - 단순 CRUD
   - 복잡한 트랜잭션 없음

3. ✅ **성능 문제 없음**
   - DB 인덱스로 충분히 빠름
   - 캐싱도 필요 없을 정도

4. ✅ **코드 가독성이 더 좋음**
   - 파일 개수 적음 (3개 vs 6개)
   - 신입도 쉽게 이해 가능

---

## 결론

### 옵션 1: 현재 상태 유지 (강력 추천 ⭐⭐⭐)
```
✅ 지금 바로 사용 가능
✅ 코드 간결하고 명확
✅ 유지보수 쉬움
✅ 추가 작업 0시간

👉 develop에 push하고 다음 도메인으로 넘어가기
```

### 옵션 2: CQRS 적용
```
✅ 팀 컨벤션 통일
✅ 상품 도메인과 동일한 구조
⚠️ 파일 개수 2배 증가
⚠️ 8시간 추가 작업 필요
⚠️ 오버엔지니어링 가능성

👉 팀에서 "모든 도메인 CQRS 필수"라고 하면 적용
   그게 아니면 불필요
```

---

## 실무 관점

### 시니어 개발자의 조언
```
"CQRS는 복잡한 도메인에 적용할 때 빛을 발합니다.
 좋아요 같은 단순한 기능에 CQRS를 적용하면
 '아키텍처를 위한 아키텍처'가 되어버립니다.
 
 나중에 정말 필요해지면 그때 리팩토링하세요.
 YAGNI 원칙을 기억하세요!"
 
 (YAGNI = You Aren't Gonna Need It)
```

---

## 질문드립니다

**팀의 코딩 컨벤션이 어떻게 되나요?**

1. 모든 도메인에 CQRS 적용이 필수인가요?
2. 상품 도메인만 CQRS를 사용하나요?
3. 특별한 규칙이 없나요?

답변에 따라 최종 결정을 도와드리겠습니다! 🙂



