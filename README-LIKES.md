# 🎯 좋아요(Likes) 도메인 실행 가이드

## 🚀 빠른 시작 (3단계)

### 1️⃣ MySQL 시작
```bash
./start-db.sh
```

### 2️⃣ 애플리케이션 실행
```bash
./gradlew bootRun
```

### 3️⃣ API 테스트
```bash
# Swagger UI 열기
open http://localhost:8080/swagger-ui/index.html
```

---

## 📝 수동 실행 방법

### Docker로 MySQL 시작
```bash
# Docker Compose로 시작
docker compose up -d mysql

# 또는
docker-compose up -d mysql

# 상태 확인
docker ps
```

### 테이블 생성
```bash
# 방법 1: Docker exec로 실행
docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/V1__create_likes_table.sql

# 방법 2: 애플리케이션 실행 시 자동 생성 (JPA)
./gradlew bootRun
# ↑ JPA가 자동으로 테이블을 생성합니다!
```

### 테이블 확인
```bash
# MySQL 컨테이너 접속
docker exec -it mysql-music-sale mysql -u root -ppassword music_sale_db

# SQL 실행
mysql> SHOW TABLES;
mysql> DESC likes;
mysql> SELECT * FROM likes;
mysql> exit
```

---

## 🔥 API 테스트

### 1. 상품 찜하기
```bash
curl -X POST http://localhost:8080/api/v1/products/1/likes \
  -H "X-User-Id: 100" \
  -H "Content-Type: application/json"
```

**응답:**
```json
{
  "code": "LIKE_CREATED",
  "message": "좋아요가 성공적으로 등록되었습니다.",
  "data": {
    "id": 1,
    "userId": 100,
    "likeableId": 1,
    "likeableType": "PRODUCT",
    "createdAt": "2025-11-05T18:00:00"
  }
}
```

### 2. 찜 상태 조회
```bash
curl -X GET http://localhost:8080/api/v1/products/1/likes/status \
  -H "X-User-Id: 100"
```

**응답:**
```json
{
  "code": "SUCCESS",
  "message": "성공적으로 처리되었습니다.",
  "data": {
    "isLiked": true
  }
}
```

### 3. 내 찜 목록 조회
```bash
curl -X GET "http://localhost:8080/api/v1/users/me/likes/products?page=0&size=20" \
  -H "X-User-Id: 100"
```

### 4. 찜 취소
```bash
curl -X DELETE http://localhost:8080/api/v1/products/1/likes \
  -H "X-User-Id: 100"
```

---

## 🐞 문제 해결

### MySQL 연결 안 됨
```bash
# Docker 컨테이너 재시작
docker restart mysql-music-sale

# 로그 확인
docker logs mysql-music-sale

# 포트 확인
lsof -i :3306
```

### 테이블이 생성 안 됨
```bash
# JPA 로그 확인 (애플리케이션 실행 시)
# application.yml에 이미 설정됨:
# jpa.hibernate.ddl-auto: update
# jpa.show-sql: true

# 수동으로 테이블 생성
docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/V1__create_likes_table.sql
```

### Gradle 빌드 오류
```bash
# 권한 부여
chmod +x ./gradlew

# Clean 후 재빌드
./gradlew clean build -x test
```

---

## 📊 구성 파일 위치

```
music-sale-platform-be/
├── mysql/
│   └── V1__create_likes_table.sql       ← DB 스키마
├── music-domain/
│   └── .../like/
│       ├── Like.java                     ← 도메인 모델
│       └── LikeableType.java            ← Enum
├── music-application/
│   └── .../like/
│       ├── service/LikeService.java     ← 비즈니스 로직
│       └── port/                        ← 인터페이스
├── music-infrastructure/
│   └── .../like/
│       ├── entity/LikeEntity.java       ← JPA Entity
│       └── repository/LikeRepository.java ← JPA Repository
└── music-api/
    └── .../like/
        └── LikeController.java           ← REST API
```

---

## ✅ 완료 체크리스트

- [ ] Docker Desktop 실행 확인
- [ ] MySQL 컨테이너 시작 (`./start-db.sh`)
- [ ] 애플리케이션 실행 (`./gradlew bootRun`)
- [ ] Swagger UI 접속 (http://localhost:8080/swagger-ui/index.html)
- [ ] API 테스트 (Swagger 또는 curl)

---

## 🎯 다음 단계

1. **Product 도메인과 연동**: 실제 상품 데이터와 연결
2. **JWT 인증**: X-User-Id 헤더를 JWT로 교체
3. **좋아요 개수 집계**: Product 테이블에 like_count 추가
4. **이벤트 기반 아키텍처**: 비동기 처리로 성능 최적화

---

**문의사항이 있으면 언제든 물어보세요!** 🚀

