# 🚀 좋아요 도메인 리팩토링 빠른 시작

## 현재 상태 vs 목표 상태

### 현재 (70% 완료)
```
❌ CQRS 패턴 미적용 (Command/Query 통합)
❌ Input DTO 없음
✅ Domain Layer 완료
✅ Application Layer 완료 (단일 Service)
✅ Infrastructure Layer 완료
✅ API Layer 완료 (단일 Controller)
✅ 단위 테스트 12개 완료
✅ DB 스키마 완료
```

### 목표 (100% 완료)
```
✅ CQRS 패턴 적용 (Command/Query 분리)
✅ Input DTO 추가
✅ UseCase 분리 (CommandUseCase, QueryUseCase)
✅ Service 분리 (CommandService, QueryService)
✅ Controller 분리 (CommandController, QueryController)
✅ 통합 테스트 추가
✅ Controller 테스트 추가
```

---

## 📝 5단계로 끝내는 리팩토링

### 1단계: Input DTO 생성 (30분)
```bash
# 파일 위치
music-application/src/main/java/com/music/sale/application/like/dto/

# 생성할 파일
AddLikeInput.java
GetMyLikesInput.java
```

**AddLikeInput.java 예시**
```java
package com.music.sale.application.like.dto;

import com.music.sale.domain.like.LikeableType;

public class AddLikeInput {
    private final Long userId;
    private final Long likeableId;
    private final LikeableType likeableType;
    
    public AddLikeInput(Long userId, Long likeableId, LikeableType likeableType) {
        this.userId = userId;
        this.likeableId = likeableId;
        this.likeableType = likeableType;
    }
    
    // Getter만 추가
}
```

**체크포인트**
```bash
# DTO 개수 확인 (4개여야 함)
ls -l music-application/src/main/java/com/music/sale/application/like/dto/
# AddLikeInput.java, GetMyLikesInput.java, LikeOutput.java, LikeStatusOutput.java
```

---

### 2단계: UseCase 분리 (1시간)

```bash
# 파일 위치
music-application/src/main/java/com/music/sale/application/like/port/inport/

# 생성할 파일
LikeCommandUseCase.java  (addLike, deleteLike)
LikeQueryUseCase.java    (getLikeStatus, getMyLikes)
```

**참고**: `ProductCommandUseCase.kt`, `ProductQueryUseCase.kt`

**체크포인트**
```bash
# UseCase 개수 확인 (2개여야 함)
ls -l music-application/src/main/java/com/music/sale/application/like/port/inport/
```

---

### 3단계: Service 분리 (1.5시간)

```bash
# 파일 위치
music-application/src/main/java/com/music/sale/application/like/service/

# 생성할 파일
LikeCommandService.java  (@Transactional)
LikeQueryService.java    (@Transactional(readOnly = true))

# 삭제할 파일
LikeService.java (기존)
```

**주의사항**
- 기존 `LikeService.java`의 코드를 복사해서 분리
- private 메서드들도 각 Service로 이동
- **메서드 3줄 룰** 준수

**체크포인트**
```bash
# Service 개수 확인 (2개여야 함)
ls -l music-application/src/main/java/com/music/sale/application/like/service/

# 컴파일 확인
./gradlew :music-application:compileJava
```

---

### 4단계: Controller 분리 (1.5시간)

```bash
# 파일 위치
music-api/src/main/java/com/music/sale/web/like/

# 생성할 파일
LikeCommandController.java  (POST, DELETE)
LikeQueryController.java    (GET)

# 삭제할 파일
LikeController.java (기존)
```

**LikeCommandController - 6개 엔드포인트**
- POST /api/v1/products/{productId}/likes
- DELETE /api/v1/products/{productId}/likes
- POST /api/v1/stores/{storeId}/likes
- DELETE /api/v1/stores/{storeId}/likes
- POST /api/v1/sellers/{sellerId}/likes
- DELETE /api/v1/sellers/{sellerId}/likes

**LikeQueryController - 6개 엔드포인트**
- GET /api/v1/products/{productId}/likes/status
- GET /api/v1/users/me/likes/products
- GET /api/v1/stores/{storeId}/likes/status
- GET /api/v1/users/me/likes/stores
- GET /api/v1/sellers/{sellerId}/likes/status
- GET /api/v1/users/me/likes/sellers

**체크포인트**
```bash
# Controller 개수 확인 (2개여야 함)
ls -l music-api/src/main/java/com/music/sale/web/like/*Controller.java

# API 엔드포인트 개수 확인 (12개)
grep -E "@(Post|Get|Delete)Mapping" music-api/src/main/java/com/music/sale/web/like/*.java | wc -l

# 컴파일 확인
./gradlew :music-api:compileJava

# 애플리케이션 실행
./gradlew :music-api:bootRun --args='--spring.profiles.active=local'
```

---

### 5단계: 테스트 코드 추가 (2시간)

```bash
# 파일 위치
music-application/src/test/java/com/music/sale/application/like/service/
music-infrastructure/src/test/java/com/music/sale/persistence/like/
music-api/src/test/java/com/music/sale/web/like/

# 생성할 파일
LikeCommandServiceTest.java       (기존 LikeServiceTest에서 분리)
LikeQueryServiceTest.java         (기존 LikeServiceTest에서 분리)
LikePersistenceAdapterTest.java   (통합 테스트 - 새로 작성)
LikeCommandControllerTest.java    (Controller 테스트 - 새로 작성)
LikeQueryControllerTest.java      (Controller 테스트 - 새로 작성)
```

**체크포인트**
```bash
# 테스트 파일 개수 확인 (5개 이상)
find music-application/src/test -name "*Like*Test.java"
find music-infrastructure/src/test -name "*Like*Test.java"
find music-api/src/test -name "*Like*Test.java"

# 테스트 실행
./gradlew test --tests "*Like*"

# 테스트 성공 확인
# ✅ 모든 테스트 PASSED
```

---

## ⚡ 한눈에 보는 체크리스트

```bash
# 전체 진행 상황 확인 스크립트
cd /Users/hydro/Desktop/java1/back_end/music-sale-platform-be

echo "=== 1단계: Input DTO (목표: 2개) ==="
find music-application/src/main/java/com/music/sale/application/like/dto -name "*Input.java" | wc -l

echo "=== 2단계: UseCase (목표: 2개) ==="
find music-application/src/main/java/com/music/sale/application/like/port/inport -name "*UseCase.java" | wc -l

echo "=== 3단계: Service (목표: 2개) ==="
find music-application/src/main/java/com/music/sale/application/like/service -name "*Service.java" | wc -l

echo "=== 4단계: Controller (목표: 2개) ==="
find music-api/src/main/java/com/music/sale/web/like -name "*Controller.java" | wc -l

echo "=== 5단계: Test (목표: 5개 이상) ==="
find . -path "*/test/*" -name "*Like*Test.java" | wc -l

echo "=== 최종 빌드 ==="
./gradlew clean build

echo "=== 최종 테스트 ==="
./gradlew test
```

---

## 🎯 어제한일 / 오늘할일 / Blocker 작성법

### 어제한일 (Yesterday)
```
✅ Likes 도메인 기본 구조 구축 완료
  - Domain/Application/Infrastructure/API Layer 구현
  - 12개 REST API 엔드포인트 구현
  - 단위 테스트 12개 작성
  - DB 스키마 설계 및 구축
  - Docker MySQL 연동 완료
  - Clean Code 원칙 적용 (메서드 3줄 이하)
  - DDD 원칙 준수 (의존성 분리)
```

### 오늘할일 (Today)
```
🎯 좋아요 도메인을 상품 도메인 수준으로 리팩토링

[ ] 1단계: Input DTO 생성 (30분)
[ ] 2단계: UseCase 분리 (1시간)
[ ] 3단계: Service 분리 (1.5시간)
[ ] 4단계: Controller 분리 (1.5시간)
[ ] 5단계: 테스트 코드 추가 (2시간)
[ ] 최종 검증 (빌드, 테스트, API 확인) (1시간)
[ ] develop 브랜치에 Push 및 PR 생성 (30분)

총 예상 소요 시간: 8시간
```

### Blocker (차단 이슈)
```
현재 차단 이슈: 없음 ✅

[만약 있다면]
❌ MySQL 포트 충돌 문제
  - 문제: 로컬 MySQL이 3306 포트 사용 중
  - 해결 방안: brew services stop mysql
  - 예상 해결 시간: 30분
```

---

## 📖 상세 가이드

더 자세한 내용은 아래 문서를 참고하세요:
- **[LIKES_REFACTORING_GUIDE.md](./LIKES_REFACTORING_GUIDE.md)** - 단계별 상세 가이드
- **[DAILY_REPORT_TEMPLATE.md](./DAILY_REPORT_TEMPLATE.md)** - 일일 보고서 템플릿

---

## 🆘 도움이 필요할 때

### 컴파일 오류
```bash
./gradlew clean
./gradlew :music-application:compileJava --stacktrace
./gradlew :music-api:compileJava --stacktrace
```

### 테스트 실패
```bash
./gradlew test --info
./gradlew test --tests "*Like*" --info
```

### API 테스트
```bash
# 애플리케이션 실행
./gradlew :music-api:bootRun --args='--spring.profiles.active=local'

# 별도 터미널에서
curl -X POST http://localhost:8080/api/v1/products/1/likes -H "X-User-Id: 1"
curl -X GET http://localhost:8080/api/v1/products/1/likes/status -H "X-User-Id: 1"
```

---

**시작 전 확인**
- [ ] 상품 도메인 코드 열어두기 (참고용)
- [ ] LIKES_REFACTORING_GUIDE.md 열어두기
- [ ] 터미널 2개 준비 (빌드용, 실행용)
- [ ] Postman 준비 (API 테스트용)

**화이팅! 🚀**



