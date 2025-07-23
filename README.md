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

프로젝트에 기여하기 전에 다음 개발 컨벤션을 숙지해주세요.

### 📝 Git 커밋 메시지 규칙

커밋 메시지는 다음 형식을 따릅니다. 이를 통해 커밋의 목적을 명확히 하고, 히스토리 추적을 용이하게 합니다.

```
<타입>(<스코프>): <제목>

(선택) <본문>

(선택) <꼬리말>
```

**타입 (Type)**

- `feat`: 새로운 기능 추가
- `fix`: 버그 수정
- `docs`: 문서 수정 (README.md 등)
- `style`: 코드 포맷팅, 세미콜론 누락 등 스타일 관련 수정 (코드 로직 변경 없음)
- `refactor`: 코드 리팩토링 (기능 변경 없이 내부 구조 개선)
- `test`: 테스트 코드 추가 또는 수정
- `chore`: 빌드 스크립트, 패키지 매니저 설정 등 기타 잡일

**예시**

```
feat(product): 상품 상세 조회 API 추가

- 상품 ID를 기반으로 상세 정보를 반환합니다.
- 재고가 없는 상품은 예외를 발생시킵니다.

Resolves: #123
```

### 🌿 Git 브랜치 전략

브랜치는 작업의 종류에 따라 다음 접두사를 사용합니다.

- `feature/<기능>`: 새로운 기능 개발 (예: `feature/user-login`)
- `fix/<문제>`: 버그 수정 (예: `fix/product-price-error`)
- `refactor/<대상>`: 리팩토링 (예: `refactor/order-service`)
- `docs/<문서>`: 문서 작업 (예: `docs/readme-update`)

### 💻 코딩 컨벤션

- **언어**: Kotlin의 공식 코딩 컨벤션을 따릅니다.
- **스타일 가이드**: [Kotlin 공식 스타일 가이드](https://kotlinlang.org/docs/coding-conventions.html)
- **포맷팅**: 프로젝트에 포함된 `spotless` 설정을 따릅니다. 코드를 커밋하기 전에 반드시 다음 명령어를 실행하여 코드를 정리해주세요.
  ```bash
  ./gradlew spotlessApply
  ```

### 🌐 API 설계 가이드라인

일관성 있고 예측 가능한 API를 위해 다음 규칙을 준수합니다.

- **엔드포인트**: `/api/{version}/{resource}` 형식을 따릅니다.
  - 버전은 현재 `v1`을 사용합니다.
  - 리소스(Resource)는 복수형(plural)을 사용합니다. (예: `users`, `products`)
- **명명 규칙**: 모든 JSON 요청/응답 필드는 `camelCase`를 사용합니다.
- **표준 응답 형식**: 모든 API 응답은 다음 구조를 따릅니다. (`ApiResponse.kt` 참고)
  ```json
  {
    "code": "SUCCESS",
    "message": "성공적으로 처리되었습니다.",
    "data": {
      // 실제 데이터
    }
  }
  ```
- **에러 처리**: 에러 발생 시, `GlobalExceptionHandler`가 에러를 처리하여 다음 형식의 응답을 반환합니다.
  ```json
  {
    "code": "INVALID_INPUT",
    "message": "입력값이 올바르지 않습니다.",
    "data": null
  }
  ```

### 🏛️ 아키텍처 규칙 (Hexagonal Architecture)

이 프로젝트는 헥사고날 아키텍처를 따르며, 각 모듈의 역할과 의존성 규칙은 다음과 같습니다.

- **`music-application` (도메인 계층)**
  - **역할**: 순수한 비즈니스 로직과 도메인 모델을 포함합니다. 프레임워크나 외부 기술에 대한 의존성이 없어야 합니다.
  - **규칙**: `domain` 객체, `service` 인터페이스(Port), `usecase`를 정의합니다.
  - **절대 금지**: `music-api`, `music-infrastructure` 모듈에 대해 알지 못하며, 의존해서는 안 됩니다.

- **`music-api` (어댑터 - 입력)**
  - **역할**: 외부(클라이언트)의 요청을 받아 `application` 계층의 유스케이스를 호출하고, 그 결과를 HTTP 응답으로 변환합니다.
  - **규칙**: `Controller`, `Request/Response DTO`, `WebMapper` 등을 포함합니다.
  - **의존성**: `music-application` 모듈에만 의존합니다.

- **`music-infrastructure` (어댑터 - 출력)**
  - **역할**: `application` 계층의 포트(인터페이스)를 구현하며, 실제 외부 시스템(DB, 외부 API 등)과의 연동을 책임집니다.
  - **규칙**: `JPA Entity`, `Repository 구현체`, `PersistenceAdapter` 등을 포함합니다.
  - **의존성**: `music-application` 모듈에만 의존합니다.

**의존성 방향: `api` -> `application` <- `infrastructure`**

> **가장 중요한 규칙: `application` 계층은 외부 세계(api, infrastructure)를 전혀 몰라야 합니다. 이것이 헥사고날 아키텍처의 핵심입니다.**

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.
