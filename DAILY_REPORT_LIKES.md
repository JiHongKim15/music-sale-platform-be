# 좋아요 도메인 - 일일 업무 보고

## 📅 2024-11-09 (토)

---

### ✅ 어제 한 일 - DONE

- **좋아요 도메인 - domain, application, infrastructure, api 레이어 Java로 구현 완료**
  - Kotlin으로 작성된 코드를 Java로 전환
  - 헥사고날 아키텍처 적용 (Port & Adapter 패턴)
  - 의존성 역전 원칙 준수 (Application → Port ← Infrastructure)

- **Clean Code 원칙 적용 - 메서드 3줄 리팩토링**
  - Before: `addLike()` 메서드 10줄 (검증, 생성, 저장 로직 혼재)
  - After: 3줄로 축소 (validateNotDuplicate, saveNewLike로 책임 분리)
  - LikeService 전체 Public 메서드 4개 모두 3줄 이하로 완료
  - Private 헬퍼 메서드 13개 추가하여 단일 책임 원칙 준수

- **단위 테스트 작성**
  - LikeServiceTest.java 작성 (JUnit 5 + Mockito)
  - 12개 테스트 케이스 작성 및 통과
  - 성공 케이스, 예외 케이스 모두 커버

- **MySQL DDL 작성 및 Docker 환경 구축**
  - `likes` 테이블 DDL 작성 (유니크 제약조건, 인덱스 포함)
  - docker-compose.yml로 MySQL 컨테이너 실행 확인
  - JPA Entity 매핑 완료

---

### 📝 오늘 할 일 - TODO

#### 1. 좋아요 도메인 정보 정리 및 문서화
- [x] 아키텍처 구조 문서 작성 (Request → Response 흐름)
- [ ] API 명세서 작성 (Swagger 또는 README에 엔드포인트 정리)
- [ ] ERD 다이어그램 작성 (likes 테이블 구조)

#### 2. API 실제 동작 테스트
- [ ] Postman으로 12개 엔드포인트 실제 호출 테스트
  - POST /products/{id}/likes (상품 찜하기)
  - DELETE /products/{id}/likes (상품 찜 취소)
  - GET /products/{id}/likes/status (찜 상태 조회)
  - GET /me/likes/products (내가 찜한 상품 목록)
  - 같은 방식으로 Store, Seller도 테스트

#### 3. 통합 테스트 작성 (선택)
- [ ] LikePersistenceAdapterTest.java 작성
  - 실제 MySQL에 연결하여 CRUD 테스트
  - @DataJpaTest 사용

#### 4. 코드 리뷰 준비
- [ ] 코드 전체 재점검 (변수명, 주석, 불필요한 코드 제거)
- [ ] PR 설명 작성 (변경 사항, Before/After 정리)

---

### 🚧 현재 막혀있는 것 - BLOCKER

**현재 특별한 Blocker 없음** ✅

*(동료분 이슈와 비교)*

#### 동료가 겪은 문제 (참고용)
1. **Kotlin/Java 혼용 문제**
   - 동료: Kotlin 코드와 Java 코드에서 Store 도메인 참고 시 클래스명 동일로 빨간줄
   - 나: 좋아요 도메인은 순수 Java로 작성 → **해당 없음**

2. **Builder/Lombok 문제**
   - 동료: Builder 객체를 Lombok으로 구현했는데 괜찮은지 고민
   - 나: Lombok 사용 안 함, 생성자 기반 불변 객체 → **해당 없음**

3. **Docker/로컬 DB 연결 문제**
   - 동료: iMac에 Docker 없어서 Colima 사용, 로컬DB 연결 문제
   - 나: Docker Desktop으로 MySQL 정상 실행 중 → **해결됨**

#### 향후 발생 가능한 이슈 (예상)
- [ ] 실제 User, Product, Store, Seller 도메인과 연동 시 FK 제약조건 문제
  - 현재는 `likeable_id`만 저장 (실제 존재 여부 검증 안 함)
  - 추후 Product 도메인과 협의 필요

- [ ] 성능 문제 (대용량 데이터 시)
  - `COUNT(*)` 쿼리 최적화 필요할 수 있음
  - 캐싱(Redis) 고려 가능

---

### 💡 참고 사항

#### 동료 이슈에서 배운 점
1. **Kotlin/Java 혼용 주의**
   - 패키지 구조 명확히 분리 필요
   - 클래스명 중복 방지

2. **Lombok vs 순수 Java**
   - Lombok: 코드 간결, 빌더 패턴 쉬움
   - 순수 Java: 명시적, 불변 객체 보장
   - 나는 순수 Java 선택 → Clean Code 원칙 준수

3. **Docker 환경 통일**
   - 팀 전체가 Docker Desktop 또는 Colima 통일 필요
   - `docker-compose.yml`로 환경 표준화

---

## 📊 진행률

```
Domain 구축: ████████████████████ 100%
Clean Code 적용: ████████████████████ 100%
단위 테스트: ████████████████████ 100%
API 테스트: ░░░░░░░░░░░░░░░░░░░░ 0%
문서화: █████░░░░░░░░░░░░░░░ 25%
통합 테스트: ░░░░░░░░░░░░░░░░░░░░ 0%
```

**전체 진행률: 약 70%**

---

## 🎯 다음 마일스톤

1. **단기 (오늘~내일)**
   - API 실제 동작 테스트 완료
   - 문서화 완료 (API 명세서, ERD)

2. **중기 (다음주)**
   - 통합 테스트 작성
   - 다른 도메인과 연동 협의
   - PR 제출 및 코드 리뷰

3. **장기 (향후)**
   - CQRS 패턴 적용 검토 (Product 도메인과 통일)
   - 성능 최적화 (캐싱, 인덱스 튜닝)
   - 이벤트 기반 아키텍처 고려

---

## 복붙용 간단 버전

```
[어제 한 일 - DONE]
• 좋아요 도메인 - domain, infrastructure, api 레이어 Java로 구현 완료
• Clean Code 원칙 적용 - 메서드 3줄 리팩토링 (addLike 10줄 → 3줄)
• 단위 테스트 12개 작성 및 통과

[오늘 할 일 - TODO]
• 좋아요 도메인 정보 정리 및 문서화 (API 명세서, ERD)
• Postman으로 12개 엔드포인트 실제 동작 테스트

[현재 막혀있는 것 - BLOCKER]
• 특별한 Blocker 없음
• (참고) 향후 Product 도메인과 연동 시 FK 제약조건 협의 필요할 수 있음
```

---

끝!


