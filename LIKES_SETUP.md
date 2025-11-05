# Likes 도메인 설정 및 실행 가이드

## 🚀 빠른 시작 (Docker 전용)

### 1. 로컬 MySQL 중지 (필수)

Docker MySQL과 포트 충돌을 방지하기 위해 로컬 MySQL을 중지합니다:

#### macOS (System Preferences에서 MySQL 설치한 경우):
```bash
# MySQL 중지
sudo /usr/local/mysql/support-files/mysql.server stop

# MySQL 상태 확인
ps aux | grep mysqld | grep -v grep
```

#### Homebrew로 설치한 경우:
```bash
brew services stop mysql
```

### 2. Docker 컨테이너 시작

```bash
# 프로젝트 루트에서 실행
cd /Users/hydro/Desktop/java1/back_end/music-sale-platform-be

# MySQL + Redis 시작
docker compose up -d

# 컨테이너 상태 확인
docker compose ps

# MySQL 시작 대기 (약 10-15초)
docker exec mysql-music-sale mysqladmin ping -h localhost -u root -ppassword
```

### 3. Spring Boot 애플리케이션 실행

#### Gradle 명령어:
```bash
./gradlew :music-api:bootRun --args='--spring.profiles.active=local'
```

#### IntelliJ:
1. `music-api/src/main/kotlin/com/music/sale/MusicSaleApplication.kt` 파일 열기
2. 상단의 녹색 ▶️ 버튼 클릭
3. VM options: `-Dspring.profiles.active=local`

### 4. 애플리케이션 확인

```bash
# Health Check
curl http://localhost:8080/actuator/health

# Swagger UI
open http://localhost:8080/swagger-ui/index.html
```

---

## 📊 구현된 API 엔드포인트 (12개)

### 상품 찜 (Product Likes)
| Method | Endpoint | 설명 | Response Code |
|--------|----------|------|---------------|
| POST | `/api/v1/products/{productId}/likes` | 상품 찜하기 | 201 |
| DELETE | `/api/v1/products/{productId}/likes` | 찜 취소 | 204 |
| GET | `/api/v1/products/{productId}/likes/status` | 찜 상태 확인 | 200 |
| GET | `/api/v1/users/me/likes/products` | 내 찜 목록 | 200 |

### 스토어 구독 (Store Subscription)
| Method | Endpoint | 설명 | Response Code |
|--------|----------|------|---------------|
| POST | `/api/v1/stores/{storeId}/likes` | 스토어 구독 | 201 |
| DELETE | `/api/v1/stores/{storeId}/likes` | 구독 취소 | 204 |
| GET | `/api/v1/stores/{storeId}/likes/status` | 구독 상태 | 200 |
| GET | `/api/v1/users/me/likes/stores` | 내 구독 목록 | 200 |

### 판매자 팔로우 (Seller Follow)
| Method | Endpoint | 설명 | Response Code |
|--------|----------|------|---------------|
| POST | `/api/v1/sellers/{sellerId}/likes` | 판매자 팔로우 | 201 |
| DELETE | `/api/v1/sellers/{sellerId}/likes` | 언팔로우 | 204 |
| GET | `/api/v1/sellers/{sellerId}/likes/status` | 팔로우 상태 | 200 |
| GET | `/api/v1/users/me/likes/sellers` | 내 팔로우 목록 | 200 |

---

## 🧪 API 테스트 예제

### cURL 명령어

```bash
# 1. 상품 찜하기
curl -X POST "http://localhost:8080/api/v1/products/1/likes" \
  -H "X-User-Id: 100" \
  -H "Content-Type: application/json"

# 2. 찜 상태 확인
curl -X GET "http://localhost:8080/api/v1/products/1/likes/status" \
  -H "X-User-Id: 100"

# 3. 내 찜 목록 조회
curl -X GET "http://localhost:8080/api/v1/users/me/likes/products?page=0&size=10" \
  -H "X-User-Id: 100"

# 4. 찜 취소
curl -X DELETE "http://localhost:8080/api/v1/products/1/likes" \
  -H "X-User-Id: 100"

# 5. 스토어 구독
curl -X POST "http://localhost:8080/api/v1/stores/5/likes" \
  -H "X-User-Id: 100"

# 6. 판매자 팔로우
curl -X POST "http://localhost:8080/api/v1/sellers/7/likes" \
  -H "X-User-Id: 100"
```

---

## 🏗️ 아키텍처

### 헥사고날 아키텍처 (Ports & Adapters)

```
┌─────────────────────────────────────────┐
│         API Layer (Adapter-In)          │
│   LikeController.java (12 endpoints)    │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────┴───────────────────────┐
│       Application Layer (Use Cases)      │
│         LikeUseCase, LikeService         │
└─────────────────┬───────────────────────┘
                  │
┌─────────────────┴───────────────────────┐
│    Domain Layer (Pure Java - DDD)       │
│      Like.java, LikeableType.java       │
│         (외부 의존성 0)                  │
└──────────────────────────────────────────┘
                  ↑
┌─────────────────┴───────────────────────┐
│    Infrastructure Layer (Adapter-Out)    │
│  LikePersistenceAdapter, LikeRepository  │
└──────────────────────────────────────────┘
```

### 디렉토리 구조

```
music-sale-platform-be/
├── music-domain/
│   └── src/main/java/com/music/sale/domain/like/
│       ├── Like.java                    # 순수 도메인 모델
│       └── LikeableType.java            # Enum (PRODUCT, STORE, SELLER)
│
├── music-application/
│   └── src/main/java/com/music/sale/application/like/
│       ├── port/
│       │   ├── inport/
│       │   │   └── LikeUseCase.java     # 인바운드 포트
│       │   └── outport/
│       │       └── LikePort.java        # 아웃바운드 포트
│       ├── service/
│       │   └── LikeService.java         # 비즈니스 로직 (3줄 이하)
│       ├── dto/
│       │   ├── LikeOutput.java
│       │   └── LikeStatusOutput.java
│       ├── mapper/
│       │   └── LikeMapper.java
│       └── exception/
│           ├── LikeAlreadyExistsException.java
│           ├── LikeNotFoundException.java
│           └── TargetNotFoundException.java
│
├── music-infrastructure/
│   └── src/main/java/com/music/sale/persistence/like/
│       ├── entity/
│       │   └── LikeEntity.java          # JPA 엔티티
│       ├── repository/
│       │   └── LikeRepository.java      # Spring Data JPA
│       └── LikePersistenceAdapter.java  # 포트 구현체
│
└── music-api/
    └── src/main/java/com/music/sale/web/like/
        ├── LikeController.java          # REST API (12 endpoints)
        ├── response/
        │   ├── LikeResponse.java
        │   └── LikeStatusResponse.java
        └── mapper/
            └── LikeWebMapper.java
```

---

## 🧪 테스트

### 단위 테스트 실행

```bash
# 전체 테스트
./gradlew :music-application:test

# 특정 테스트
./gradlew :music-application:test --tests LikeServiceTest

# 테스트 리포트 확인
open music-application/build/reports/tests/test/index.html
```

### 테스트 케이스 (12개)

- ✅ 상품 찜하기 - 성공
- ✅ 상품 찜하기 - 중복 시 예외
- ✅ 찜 취소 - 성공
- ✅ 찜 취소 - 존재하지 않으면 예외
- ✅ 좋아요 상태 조회 - 찜한 경우
- ✅ 좋아요 상태 조회 - 찜하지 않은 경우
- ✅ 내 찜 목록 조회
- ✅ 스토어 구독
- ✅ 판매자 팔로우
- ✅ Domain 객체 생성 검증
- ✅ 잘못된 userId 예외
- ✅ null LikeableType 예외

---

## 🗄️ 데이터베이스

### likes 테이블 스키마

```sql
CREATE TABLE likes (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  likeable_id BIGINT NOT NULL,
  likeable_type VARCHAR(20) NOT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  
  -- 중복 방지 (사용자당 한 번만 좋아요 가능)
  UNIQUE KEY uix_user_likeable (user_id, likeable_type, likeable_id),
  
  -- 성능 최적화
  INDEX idx_user_type_created (user_id, likeable_type, created_at DESC),
  INDEX idx_likeable (likeable_type, likeable_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### MySQL 접속

```bash
# Docker MySQL 접속
docker exec -it mysql-music-sale mysql -u root -ppassword music_sale_db

# 데이터 확인
SELECT * FROM likes ORDER BY created_at DESC LIMIT 10;
```

---

## 🐛 트러블슈팅

### 1. 포트 3306이 이미 사용 중

**원인**: 로컬 MySQL이 실행 중

**해결**:
```bash
# 로컬 MySQL 중지
sudo /usr/local/mysql/support-files/mysql.server stop

# 또는 Homebrew
brew services stop mysql

# 확인
lsof -i :3306
```

### 2. Docker 컨테이너가 시작되지 않음

```bash
# 컨테이너 로그 확인
docker compose logs mysql

# 컨테이너 재시작
docker compose restart mysql

# 완전히 재생성
docker compose down
docker compose up -d
```

### 3. Spring Boot 연결 오류

```bash
# MySQL 컨테이너 상태 확인
docker compose ps

# MySQL 준비 상태 확인
docker exec mysql-music-sale mysqladmin ping -h localhost -u root -ppassword

# Health check
curl http://localhost:8080/actuator/health
```

---

## 📝 DDD & Clean Code 원칙

### DDD (Domain-Driven Design)
- ✅ Domain Layer는 순수 Java (외부 의존성 0)
- ✅ Application Layer는 Port 인터페이스로 추상화
- ✅ Infrastructure Layer는 Port 구현
- ✅ 모든 의존성 역전 완료

### Clean Code
- ✅ 모든 메소드 3줄 이하
- ✅ 단일 책임 원칙 (SRP)
- ✅ 명확한 메소드 이름
- ✅ private 메소드로 로직 분해

### 예시: LikeService.addLike()

```java
@Override
public LikeOutput addLike(Long userId, Long likeableId, LikeableType likeableType) {
    validateNotDuplicate(userId, likeableId, likeableType);
    Like savedLike = saveNewLike(userId, likeableId, likeableType);
    return likeMapper.toOutput(savedLike);
}

private void validateNotDuplicate(Long userId, Long likeableId, LikeableType likeableType) {
    if (isDuplicate(userId, likeableId, likeableType)) {
        throw createDuplicateException(likeableType);
    }
}
```

---

## 🔗 참고 링크

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Actuator Health: http://localhost:8080/actuator/health
- H2 Console (개발용): http://localhost:8080/h2-console

