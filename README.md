# Music Sale Platform Backend

음악 판매 플랫폼의 백엔드 애플리케이션입니다.

## 🚀 기술 스택

- **Language**: Kotlin
- **Framework**: Spring Boot 3.3.1
- **Database**: MySQL 8.0 (Docker)
- **ORM**: Hibernate/JPA
- **Build Tool**: Gradle
- **Architecture**: Hexagonal Architecture (Clean Architecture)

## 📋 사전 요구사항

- Java 21+
- Docker & Docker Compose
- Gradle

## 🐳 데이터베이스 설정

### Docker Compose를 사용한 MySQL 실행

```bash
# MySQL 컨테이너 시작
docker-compose up -d mysql

# 상태 확인
docker-compose ps

# 로그 확인
docker-compose logs mysql

# MySQL 접속
docker exec -it mysql-music-sale mysql -u root -ppassword
```

### 데이터베이스 정보

- **Host**: localhost:3306
- **Database**: music_sale_db
- **Username**: root
- **Password**: password
- **Additional User**: music_user / music_password

## 🏗️ 프로젝트 구조

```
music-sale-platform-be/
├── music-api/                 # 웹 API 모듈
├── music-application/         # 애플리케이션 서비스 모듈
├── music-infrastructure/      # 인프라스트럭처 모듈
├── docker-compose.yml         # Docker Compose 설정
└── mysql/                     # MySQL 초기화 스크립트
    └── init/
        └── 01-init.sql
```

## 🚀 실행 방법

### 1. 데이터베이스 시작

```bash
docker-compose up -d mysql
```

### 2. 애플리케이션 실행

```bash
./gradlew :music-api:bootRun
```

### 3. 애플리케이션 접속

- **URL**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health

## 🔧 개발 환경 설정

### 빌드

```bash
# 전체 프로젝트 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :music-api:build
```

### 코드 스타일

```bash
# 코드 포맷팅
./gradlew spotlessApply

# 코드 스타일 검사
./gradlew spotlessKotlin
```

## 📊 데이터베이스 관리

### 컨테이너 관리

```bash
# 컨테이너 시작
docker-compose up -d

# 컨테이너 중지
docker-compose down

# 컨테이너 재시작
docker-compose restart mysql
```

### 데이터베이스 접속

```bash
# MySQL CLI 접속
docker exec -it mysql-music-sale mysql -u root -ppassword

# 데이터베이스 선택
USE music_sale_db;

# 테이블 확인
SHOW TABLES;
```

## 🛠️ 문제 해결

### MySQL 연결 오류

만약 MySQL 연결 오류가 발생한다면:

1. **컨테이너 상태 확인**
   ```bash
   docker-compose ps
   ```

2. **MySQL 로그 확인**
   ```bash
   docker-compose logs mysql
   ```

3. **컨테이너 재시작**
   ```bash
   docker-compose down
   docker-compose up -d mysql
   ```

4. **권한 재설정**
   ```bash
   docker exec mysql-music-sale mysql -u root -ppassword -e "ALTER USER 'root'@'localhost' IDENTIFIED BY 'password'; FLUSH PRIVILEGES;"
   ```

### 포트 충돌

3306 포트가 이미 사용 중인 경우:

```bash
# 포트 사용 확인
lsof -i :3306

# 다른 포트로 변경 (docker-compose.yml 수정)
ports:
  - "3307:3306"
```

## 📝 API 문서

애플리케이션 실행 후 다음 URL에서 API 문서를 확인할 수 있습니다:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 🤝 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.
