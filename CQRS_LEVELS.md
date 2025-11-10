# CQRS의 3가지 수준

Gemini가 말한 것과 제가 말한 것은 **완전히 다른 수준**입니다!

---

## 🎯 CQRS 수준 비교

### Level 1: 가벼운 CQRS (제가 말한 것)
```
같은 DB, 같은 테이블 사용
단지 Service와 Controller를 분리

LikeCommandService  ────┐
                        ├──→ likes 테이블 (MySQL)
LikeQueryService    ────┘

특징:
- 구현 간단 (파일만 분리)
- 같은 DB 사용
- 동기 처리
- 코드 구조 개선이 목적
```

### Level 2: DB 분리 CQRS
```
쓰기용 DB와 읽기용 DB를 분리

LikeCommandService → Write DB (Master)
                          ↓
                    DB Replication
                          ↓
LikeQueryService   → Read DB (Slave)

특징:
- MySQL Replication
- 읽기 부하 분산
- 약간의 데이터 지연 허용
```

### Level 3: Event Sourcing CQRS (Gemini가 말한 것)
```
완전히 다른 구조의 DB 사용 + 이벤트 기반

Command → Write DB → Kafka → Event Handler → Read DB
(INSERT)  (likes)            (처리)          (product_counts)
                                                   ↓
                                              Query → Read DB

특징:
- 이벤트 발행/구독
- 비동기 처리
- 완전히 다른 데이터 모델
- 초고성능 (Netflix, 쿠팡)
```

---

## 📊 상세 비교

### Level 1: 가벼운 CQRS

#### 코드 구조
```java
// Command Service (쓰기)
@Service
public class LikeCommandService {
    public LikeOutput addLike(...) {
        // likes 테이블에 INSERT
        return likeRepository.save(like);
    }
}

// Query Service (읽기)
@Service
public class LikeQueryService {
    public Page<Like> getMyLikes(...) {
        // likes 테이블에서 SELECT
        return likeRepository.findByUserId(userId);
    }
}
```

#### DB 구조
```sql
-- 하나의 테이블만 사용
likes 테이블
┌────┬─────────┬───────────────┬──────────────┐
│ id │ user_id │ likeable_type │ likeable_id  │
├────┼─────────┼───────────────┼──────────────┤
│ 1  │ 100     │ PRODUCT       │ 1            │
│ 2  │ 100     │ PRODUCT       │ 5            │
└────┴─────────┴───────────────┴──────────────┘

쓰기: INSERT INTO likes ...
읽기: SELECT * FROM likes WHERE user_id = 100
```

#### 장점
- ✅ 구현 매우 간단
- ✅ 데이터 정합성 보장
- ✅ 트랜잭션 관리 쉬움

#### 단점
- ❌ 읽기 부하 분산 불가
- ❌ 성능 최적화 제한적

#### 적용 시점
- 사용자 수 ~10만 명
- 동시 접속 ~1,000명
- 일반적인 서비스

---

### Level 2: DB 분리 CQRS

#### 코드 구조
```java
// Command Service (Master DB)
@Service
public class LikeCommandService {
    @Transactional  // Master DB
    public LikeOutput addLike(...) {
        return masterRepository.save(like);
    }
}

// Query Service (Slave DB)
@Service
public class LikeQueryService {
    @Transactional(readOnly = true)  // Slave DB
    public Page<Like> getMyLikes(...) {
        return slaveRepository.findByUserId(userId);
    }
}
```

#### DB 구조
```
Master DB (쓰기 전용)
likes 테이블
┌────┬─────────┬───────────────┐
│ id │ user_id │ likeable_type │
├────┼─────────┼───────────────┤
│ 1  │ 100     │ PRODUCT       │
└────┴─────────┴───────────────┘
       ↓
   Replication (자동 복제)
       ↓
Slave DB (읽기 전용)
likes 테이블
┌────┬─────────┬───────────────┐
│ 1  │ 100     │ PRODUCT       │  ← 복제된 데이터
└────┴─────────┴───────────────┘
```

#### 장점
- ✅ 읽기 부하 분산
- ✅ 쓰기 성능 유지
- ✅ 같은 데이터 구조

#### 단점
- ❌ 복제 지연 (0.1~1초)
- ❌ DB 비용 증가

#### 적용 시점
- 사용자 수 ~100만 명
- 읽기가 쓰기보다 10배 많음
- 약간의 데이터 지연 허용

---

### Level 3: Event Sourcing CQRS (Gemini가 말한 것)

#### 코드 구조
```java
// 1. Command Service (Write DB)
@Service
public class LikeCommandService {
    public LikeOutput addLike(...) {
        // 1) likes 테이블에 INSERT
        Like like = likeRepository.save(like);
        
        // 2) Kafka에 이벤트 발행
        eventPublisher.publish(new LikeAddedEvent(
            productId, 
            userId,
            timestamp
        ));
        
        return like;
    }
}

// 2. Event Handler (비동기 처리)
@Service
public class LikeEventHandler {
    @KafkaListener(topics = "like-events")
    public void handle(LikeAddedEvent event) {
        // Read DB 업데이트 (비동기)
        productCountRepository.incrementLikeCount(event.getProductId());
    }
}

// 3. Query Service (Read DB)
@Service
public class LikeQueryService {
    public int getLikeCount(Long productId) {
        // 미리 계산된 값을 바로 읽음
        return productCountRepository.findById(productId).getLikeCount();
    }
}
```

#### DB 구조
```
Write DB (이벤트 저장)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
likes 테이블 (이력 저장)
┌────┬─────────┬──────────────┬─────────────────────┐
│ id │ user_id │ product_id   │ created_at          │
├────┼─────────┼──────────────┼─────────────────────┤
│ 1  │ 100     │ 1            │ 2024-11-06 10:00:00 │
│ 2  │ 101     │ 1            │ 2024-11-06 10:00:01 │
│ 3  │ 102     │ 1            │ 2024-11-06 10:00:02 │
└────┴─────────┴──────────────┴─────────────────────┘

        ↓ Kafka 이벤트 발행
        ↓ Event Handler 처리
        ↓

Read DB (집계 저장)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
product_counts 테이블
┌────────────┬────────────┬──────────────┐
│ product_id │ like_count │ updated_at   │
├────────────┼────────────┼──────────────┤
│ 1          │ 3          │ 10:00:02     │ ← 미리 계산됨!
└────────────┴────────────┴──────────────┘

조회 쿼리:
❌ SELECT COUNT(*) FROM likes WHERE product_id = 1;  (느림)
✅ SELECT like_count FROM product_counts WHERE product_id = 1;  (빠름!)
```

#### 장점
- ✅ 초고성능 읽기 (COUNT 불필요)
- ✅ 완전한 이벤트 이력 저장
- ✅ 무한 확장 가능

#### 단점
- ❌ 구현 복잡도 매우 높음
- ❌ 이벤트 처리 지연 (1~5초)
- ❌ 인프라 비용 높음 (Kafka, Redis)
- ❌ 데이터 정합성 이슈 가능

#### 적용 시점
- 사용자 수 1,000만 명+
- 초당 수만 건 조회
- Netflix, 쿠팡, 배달의민족 수준

---

## 🤔 Gemini가 맞나? 제가 맞나?

### Gemini가 옳은 경우 (Level 3 필요)

```
서비스 규모:
- 일 사용자: 1,000만 명+
- 초당 조회: 10,000+ QPS
- 좋아요 수 조회가 매우 빈번함

예시:
- YouTube "좋아요 수" (실시간 집계)
- Instagram 하트 개수
- Netflix "내가 찜한 콘텐츠" 목록

이 경우 Gemini 방식 필수:
✅ COUNT(*) 쿼리는 너무 느림
✅ 미리 계산된 값을 Redis에 캐싱
✅ Kafka로 비동기 처리
```

### 제가 옳은 경우 (Level 1로 충분)

```
서비스 규모:
- 일 사용자: ~10만 명
- 초당 조회: ~100 QPS
- 일반적인 스타트업

예시:
- 여러분의 음악 판매 플랫폼
- 중소형 쇼핑몰
- 사내 시스템

이 경우 Level 1로 충분:
✅ MySQL 인덱스만으로 충분히 빠름
✅ 구현 간단, 유지보수 쉬움
✅ 데이터 정합성 완벽
```

---

## 📊 실전 의사결정 트리

```
Q1. 일 사용자가 100만 명 이상인가?
    ├─ NO  → Level 1 (가벼운 CQRS)
    └─ YES → Q2로

Q2. 좋아요 "수" 조회가 초당 1,000회 이상인가?
    ├─ NO  → Level 1 또는 Level 2
    └─ YES → Q3로

Q3. 1~5초 데이터 지연을 허용할 수 있는가?
    ├─ NO  → Level 2 (DB Replication)
    └─ YES → Level 3 (Event Sourcing CQRS)

Q4. Kafka, Redis 인프라를 운영할 수 있는가?
    ├─ NO  → Level 2로 다운그레이드
    └─ YES → Level 3 적용
```

---

## 💡 여러분 프로젝트에는?

### 현재 상황
```
서비스: 음악 판매 플랫폼
예상 사용자: ?
개발 팀: 소규모
인프라: Docker, MySQL

→ Level 1로 충분합니다!
```

### 추천 단계
```
1단계 (현재): Level 1도 안 함 (통합 Service)
   - LikeService (Command + Query)
   - 충분히 빠름
   
2단계 (사용자 10만+): Level 1 적용
   - LikeCommandService
   - LikeQueryService
   - 코드 구조 개선
   
3단계 (사용자 100만+): Level 2 적용
   - Master/Slave DB 분리
   - 읽기 부하 분산
   
4단계 (사용자 1,000만+): Level 3 적용
   - Kafka + Event Sourcing
   - 별도 팀 필요
```

---

## 🎤 면접에서 이렇게 말하세요

**면접관**: "CQRS를 아시나요? 좋아요 기능에 적용할 수 있나요?"

**답변 (레벨별로 구분해서 설명)**:
```
"네, CQRS는 3가지 수준이 있습니다.

Level 1 (가벼운 CQRS):
- CommandService와 QueryService로 분리
- 같은 DB 사용, 코드 구조 개선이 목적
- 제 프로젝트에 적용 가능합니다

Level 2 (DB 분리):
- Master/Slave DB 분리
- 읽기 부하 분산
- 사용자 100만 명 이상일 때 필요

Level 3 (Event Sourcing):
- Kafka로 이벤트 발행
- 별도 Read DB (집계 테이블)
- Netflix, 쿠팡 수준에서 필요

제 프로젝트는 Level 1로 충분하다고 판단했습니다.
왜냐하면:
1. 예상 사용자 규모가 크지 않음
2. MySQL 인덱스로 충분히 빠름
3. 복잡한 인프라 없이 단순하게 유지

만약 서비스가 성장해서 조회 성능이 문제가 되면
그때 Level 2나 Level 3로 전환할 수 있습니다.

과도한 아키텍처는 오히려 유지보수 비용만 증가시킨다고
판단했습니다."
```

---

## 📚 추가 학습

### Gemini가 말한 방식을 공부하려면
```
1. Kafka 기본 개념
2. Event Sourcing 패턴
3. Event-Driven Architecture
4. CQRS with Event Sourcing (Martin Fowler)
5. Redis 캐싱 전략
```

### 실무에서 단계적으로 적용하려면
```
1단계: 인덱스 최적화 (현재)
2단계: Redis 캐싱 추가
3단계: Master/Slave 분리
4단계: Kafka + Event Sourcing

각 단계마다 "정말 필요한가?" 질문하기!
```

---

## 🎯 결론

### Gemini vs 제 답변

| 항목 | Gemini | 제 답변 |
|------|--------|---------|
| **CQRS 수준** | Level 3 (Event Sourcing) | Level 1 (Service 분리) |
| **적용 시점** | Netflix, YouTube 수준 | 일반 스타트업 |
| **복잡도** | 매우 높음 | 낮음 |
| **필요 인프라** | Kafka, Redis, Event Bus | 없음 (기존 MySQL) |
| **데이터 지연** | 1~5초 허용 | 즉시 (실시간) |

### 누가 맞는가?

**둘 다 맞습니다!**

- Gemini: "좋아요 기능은 Level 3 CQRS를 적용하기 좋은 예시다" ✅
- 저: "여러분 프로젝트에는 Level 1도 불필요하다" ✅

**왜?** 서비스 규모가 다르기 때문!

---

## 💪 여러분이 해야 할 것

### 지금 당장
```
✅ 현재 상태 유지 (통합 Service)
✅ MySQL 인덱스 최적화
✅ INTERVIEW_GUIDE.md 공부
```

### 면접에서
```
✅ CQRS 3가지 수준 설명
✅ 각 수준의 적용 시점 설명
✅ "과도한 아키텍처는 독이다" 언급
✅ "서비스 규모에 맞게 선택" 강조
```

### 나중에 (서비스가 커지면)
```
1. Redis 캐싱 추가
2. Level 2 (DB 분리) 고려
3. Level 3은 정말 필요할 때만!
```

---

**정리**: Gemini가 말한 건 "이상적인 대규모 시스템"이고,
제가 말한 건 "여러분 프로젝트에 적합한 현실적 수준"입니다.

면접에서는 **둘 다 설명**할 수 있어야 합니다! 🎯



