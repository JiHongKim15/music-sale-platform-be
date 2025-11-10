# Music Sale Platform Backend

음악 판매 플랫폼의 백엔드 애플리케이션입니다.

## 🎵 주요 기능

### 🔐 사용자 관리
- 이메일/전화번호 회원가입 및 로그인
- 소셜 로그인 (Google, Naver, Kakao)
- JWT 기반 인증/인가
- 사용자 역할 관리 (Admin, Seller, User)

### 🎼 상품 관리
- 음악 상품 등록/수정/삭제
- 상품 카테고리 분류
- 상품 이미지 관리
- 상품 검색 및 필터링
- 조회수 추적

### 🛒 주문 및 장바구니
- 장바구니 담기/제거
- 위시리스트 관리
- 주문 처리 및 결제
- 배송 정보 관리

### 🏪 스토어 관리
- 판매자 스토어 생성/관리
- 스토어별 상품 관리
- 판매자 인증 시스템

## 🚀 기술 스택

### Core
- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.3.1
- **JVM**: Java 21
- **Build Tool**: Gradle with Kotlin DSL
- **Architecture**: Hexagonal Architecture (Clean Architecture)

### Database & Persistence
- **Database**: MySQL 8.0 (Docker)
- **Cache**: Redis 7 (Docker)
- **ORM**: Hibernate/JPA
- **Query Builder**: QueryDSL
- **Migration**: Flyway (via JPA DDL)

### Security & Authentication
- **Security**: Spring Security
- **Authentication**: JWT (JSON Web Token)
- **OAuth2**: Google, Naver, Kakao 소셜 로그인

### Web & API
- **Web Framework**: Spring Web MVC
- **Validation**: Spring Boot Validation
- **API Documentation**: SpringDoc OpenAPI 3 (Swagger)
- **Real-time Communication**: WebSocket

### Development & Operations
- **Code Style**: Spotless + Ktlint
- **Configuration**: Environment Variables (.env)
- **Containerization**: Docker & Docker Compose
- **Monitoring**: Spring Boot Actuator

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
- **Root User**: root / password
- **Application User**: music_user / music_password

### Redis 정보

- **Host**: localhost:6379
- **Database**: 0 (기본값)
- **Password**: 없음

## 🏗️ 프로젝트 구조

```
music-sale-platform-be/
├── music-api/                 # 웹 API 모듈 (Presentation Layer)
├── music-application/         # 애플리케이션 서비스 모듈 (Application Layer)
├── music-domain/              # 도메인 모듈 (Domain Layer)
├── music-infrastructure/      # 인프라스트럭처 모듈 (Infrastructure Layer)
├── docker-compose.yml         # Docker Compose 설정
└── mysql/                     # MySQL 초기화 스크립트
    └── init/
        └── 01-init.sql
```

### 모듈별 상세 구조

#### 🌐 music-api (Presentation Layer)
```
music-api/
├── config/                    # 보안, JWT, OAuth2, WebSocket 설정
├── web/                       # 웹 컨트롤러
│   ├── category/              # 카테고리 관련 API
│   ├── product/               # 상품 관련 API
│   └── user/                  # 사용자 관련 API
└── common/                    # 공통 응답, 예외 처리
```

#### 🔧 music-application (Application Layer)
```
music-application/
└── application/
    ├── auth/                  # 인증/인가 서비스
    ├── cart/                  # 장바구니 서비스
    ├── category/              # 카테고리 서비스
    ├── product/               # 상품 서비스
    ├── user/                  # 사용자 서비스
    ├── viewcount/             # 조회수 서비스
    └── wishlist/              # 위시리스트 서비스
```

#### 💎 music-domain (Domain Layer)
```
music-domain/
└── domain/
    ├── cart/                  # 장바구니 도메인
    ├── category/              # 카테고리 도메인
    ├── order/                 # 주문 도메인
    ├── product/               # 상품 도메인
    ├── shipping/              # 배송 도메인
    ├── shop/                  # 상점 도메인
    ├── store/                 # 스토어 도메인
    ├── user/                  # 사용자 도메인
    ├── viewcount/             # 조회수 도메인
    └── wishlist/              # 위시리스트 도메인
```

#### 🏗️ music-infrastructure (Infrastructure Layer)
```
music-infrastructure/
├── config/                    # JPA, Redis, QueryDSL 설정
└── persistence/               # 데이터 영속성
    ├── cart/                  # 장바구니 저장소
    ├── category/              # 카테고리 저장소
    ├── product/               # 상품 저장소
    ├── user/                  # 사용자 저장소
    ├── viewcount/             # 조회수 저장소
    └── wishlist/              # 위시리스트 저장소
```

## 🚀 실행 방법

### 1. 환경변수 설정

```bash
# .env.example을 .env로 복사
cp .env.example .env

# .env 파일에서 필요한 설정 수정
# 특히 DB_PASSWORD를 실제 MySQL 비밀번호로 변경
```

### 2. 데이터베이스 시작

```bash
docker-compose up -d mysql
```

### 3. Redis 시작 (선택사항)

```bash
docker-compose up -d redis
```

### 4. 애플리케이션 실행

```bash
# 환경별 실행 (권장)
./gradlew local      # 로컬 개발 환경
./gradlew dev        # 개발 서버 환경
./gradlew prod       # 운영 서버 환경
./gradlew testEnv    # 테스트 환경

# 또는 직접 실행
./gradlew :music-api:bootRun
```

### 5. 애플리케이션 접속

- **URL**: http://localhost:8080
- **Health Check**: http://localhost:8080/actuator/health
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## 🔧 개발 환경 설정

### 환경변수 설정

프로젝트는 환경변수를 통해 모든 설정을 관리합니다. 개발 시작 전에 다음 단계를 따라주세요:

#### 1. 환경변수 파일 생성

```bash
# .env.example을 .env로 복사
cp .env.example .env
```

#### 2. 필수 환경변수 설정

`.env` 파일에서 다음 환경변수들을 설정해주세요:

**기본 설정**

```bash
# Spring Profile 설정
SPRING_PROFILES_ACTIVE=local

# Server 설정
SERVER_PORT=8080
INCLUDE_STACKTRACE=never
```

**데이터베이스 설정**

```bash
# Database 설정
DB_HOST=localhost
DB_PORT=3306
DB_NAME=music_sale_db
DB_USERNAME=root
DB_PASSWORD=password  # 실제 MySQL 비밀번호로 변경
DB_MAX_POOL_SIZE=5
DB_MIN_IDLE=2
DB_CONNECTION_TIMEOUT=30000
DB_IDLE_TIMEOUT=600000
DB_MAX_LIFETIME=1800000
```

**JPA 설정 (환경별)**

```bash
# 로컬 개발 환경
JPA_DDL_AUTO=create-drop  # 테이블 재생성
JPA_SHOW_SQL=true         # SQL 로그 출력
JPA_FORMAT_SQL=true       # SQL 포맷팅
JPA_USE_SQL_COMMENTS=true # SQL 주석 출력
JPA_DEFER_DATASOURCE_INIT=false
JPA_SQL_INIT_MODE=never   # import.sql 비활성화

# 개발 서버 환경
JPA_DDL_AUTO=update       # 테이블 구조 업데이트
JPA_SHOW_SQL=false        # SQL 로그 비활성화
JPA_FORMAT_SQL=false      # SQL 포맷팅 비활성화
JPA_USE_SQL_COMMENTS=false # SQL 주석 비활성화
JPA_DEFER_DATASOURCE_INIT=true
JPA_SQL_INIT_MODE=always  # import.sql 활성화

# 운영 서버 환경
JPA_DDL_AUTO=validate     # 테이블 구조 검증만
JPA_SHOW_SQL=false        # SQL 로그 비활성화
JPA_FORMAT_SQL=false      # SQL 포맷팅 비활성화
JPA_USE_SQL_COMMENTS=false # SQL 주석 비활성화
JPA_DEFER_DATASOURCE_INIT=false
JPA_SQL_INIT_MODE=never   # import.sql 비활성화
```

**JWT 설정**

```bash
# JWT 설정
JWT_SECRET=your-super-secret-jwt-key-here-make-it-long-and-secure
JWT_EXPIRATION_MS=86400000  # 24시간
```

**Redis 설정**

```bash
# Redis 설정
REDIS_HOST=localhost
REDIS_PORT=6379
REDIS_PASSWORD=           # Redis 비밀번호 (없으면 빈 값)
REDIS_DATABASE=0
REDIS_TIMEOUT=2000ms
REDIS_MAX_ACTIVE=8
REDIS_MAX_IDLE=8
REDIS_MIN_IDLE=0
REDIS_MAX_WAIT=-1ms
```

**OAuth2 설정 (소셜 로그인)**

```bash
# Google OAuth2
GOOGLE_CLIENT_ID=your-google-client-id
GOOGLE_CLIENT_SECRET=your-google-client-secret

# Naver OAuth2
NAVER_CLIENT_ID=your-naver-client-id
NAVER_CLIENT_SECRET=your-naver-client-secret
NAVER_AUTH_URI=https://nid.naver.com/oauth2.0/authorize
NAVER_TOKEN_URI=https://nid.naver.com/oauth2.0/token
NAVER_USER_INFO_URI=https://openapi.naver.com/v1/nid/me

# Kakao OAuth2
KAKAO_CLIENT_ID=your-kakao-client-id
KAKAO_CLIENT_SECRET=your-kakao-client-secret
KAKAO_AUTH_URI=https://kauth.kakao.com/oauth/authorize
KAKAO_TOKEN_URI=https://kauth.kakao.com/oauth/token
KAKAO_USER_INFO_URI=https://kapi.kakao.com/v2/user/me
```

#### 3. 환경별 설정 가이드

**로컬 개발 환경**

- `SPRING_PROFILES_ACTIVE=local`
- `JPA_DDL_AUTO=create-drop`
- `JPA_SHOW_SQL=true`
- `JPA_SQL_INIT_MODE=never`

**개발 서버 환경**

- `SPRING_PROFILES_ACTIVE=dev`
- `JPA_DDL_AUTO=update`
- `JPA_SHOW_SQL=false`
- `JPA_SQL_INIT_MODE=always`

**운영 서버 환경**

- `SPRING_PROFILES_ACTIVE=prod`
- `JPA_DDL_AUTO=validate`
- `JPA_SHOW_SQL=false`
- `JPA_SQL_INIT_MODE=never`
- `INCLUDE_STACKTRACE=never`

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
# 모든 서비스 시작
docker-compose up -d

# 특정 서비스만 시작
docker-compose up -d mysql
docker-compose up -d redis

# 컨테이너 상태 확인
docker-compose ps

# 로그 확인
docker-compose logs mysql
docker-compose logs redis

# 컨테이너 중지
docker-compose down

# 컨테이너 재시작
docker-compose restart mysql
docker-compose restart redis

# 데이터 볼륨까지 삭제 (주의!)
docker-compose down -v
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

### 환경변수 관련 문제

#### 애플리케이션이 환경변수를 읽지 못하는 경우

1. **`.env` 파일 존재 확인**
   ```bash
   ls -la .env
   ```

2. **환경변수 파일 복사**
   ```bash
   cp .env.example .env.local
   ```

3. **환경변수 값 확인**
   ```bash
   cat .env
   ```

#### 데이터베이스 연결 오류

1. **환경변수 확인**
   ```bash
   # .env 파일에서 DB 설정 확인
   cat .env | grep DB_
   ```

2. **MySQL 비밀번호 확인**
   ```bash
   # Docker Compose의 MySQL 비밀번호와 .env의 DB_PASSWORD가 일치하는지 확인
   docker-compose exec mysql mysql -u root -p
   ```

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

### Redis 연결 오류

1. **Redis 컨테이너 상태 확인**
   ```bash
   docker-compose ps redis
   ```

2. **Redis 연결 테스트**
   ```bash
   docker exec redis-music-sale redis-cli ping
   ```

3. **환경변수 확인**
   ```bash
   # .env 파일에서 Redis 설정 확인
   cat .env | grep REDIS_
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

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
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

#### 🎯 의존성 방향
```
┌─────────────────┐
│   music-api     │ (Presentation Layer)
│   (어댑터-입력)    │
└─────────────────┘
          ↓ depends on
┌─────────────────┐
│ music-application│ (Application Layer)
│   (유스케이스)     │
└─────────────────┘
          ↓ depends on
┌─────────────────┐
│  music-domain   │ (Domain Layer)
│   (도메인 로직)    │
└─────────────────┘
          ↑ depends on
┌─────────────────┐
│music-infrastructure│ (Infrastructure Layer)
│   (어댑터-출력)    │
└─────────────────┘
```

#### 📋 모듈별 역할과 규칙

- **💎 `music-domain` (도메인 계층)**
    - **역할**: 순수한 비즈니스 로직과 도메인 모델을 포함합니다. 프레임워크나 외부 기술에 대한 의존성이 없어야 합니다.
    - **포함**: 도메인 엔티티, 값 객체, 도메인 서비스, 도메인 이벤트, 열거형 등
    - **절대 금지**: 다른 모듈에 대한 의존성을 가져서는 안 됩니다. 순수한 Kotlin/Java 코드만 포함해야 합니다.

- **🔧 `music-application` (애플리케이션 계층)**
    - **역할**: 도메인 객체를 조합하여 유스케이스를 구현합니다. 포트(인터페이스)를 정의하여 외부 의존성을 추상화합니다.
    - **포함**: 애플리케이션 서비스, 포트 인터페이스(inport/outport), DTO, 매퍼
    - **의존성**: `music-domain` 모듈에만 의존합니다.
    - **절대 금지**: `music-api`, `music-infrastructure` 모듈을 알아서는 안 됩니다.

- **🌐 `music-api` (어댑터 - 입력)**
    - **역할**: 외부(클라이언트)의 요청을 받아 `application` 계층의 유스케이스를 호출하고, 그 결과를 HTTP 응답으로 변환합니다.
    - **포함**: 컨트롤러, Request/Response DTO, 웹 매퍼, 보안 설정, 예외 처리
    - **의존성**: `music-application`, `music-domain`, `music-infrastructure` 모듈에 의존합니다.

- **🏗️ `music-infrastructure` (어댑터 - 출력)**
    - **역할**: `application` 계층의 포트(인터페이스)를 구현하며, 실제 외부 시스템(DB, 외부 API 등)과의 연동을 책임집니다.
    - **포함**: JPA 엔티티, 리포지토리 구현체, 퍼시스턴스 어댑터, 외부 API 클라이언트
    - **의존성**: `music-application`, `music-domain` 모듈에만 의존합니다.

#### 🎯 핵심 원칙

> **⚠️ 가장 중요한 규칙: `music-domain`과 `music-application` 계층은 외부 세계(api, infrastructure)를 전혀 몰라야 합니다. 이것이 헥사고날 아키텍처의 핵심입니다.**

- **의존성 역전**: 고수준 모듈(domain, application)이 저수준 모듈(infrastructure)에 의존하지 않습니다.
- **포트와 어댑터**: 외부 의존성은 포트(인터페이스)를 통해 추상화하고, 어댑터에서 구현합니다.
- **단일 책임**: 각 모듈은 명확하고 단일한 책임을 가집니다.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 라이선스

이 프로젝트는 MIT 라이선스 하에 배포됩니다.
