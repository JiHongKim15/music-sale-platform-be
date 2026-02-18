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
# .env.example을 .env.local로 복사
cp .env.example .env.local
```

`.env.local` 파일 수정 (필요한 부분만):

```bash
# H2 사용 시 (Docker 불필요) - 기본값
DB_URL=jdbc:h2:mem:testdb
DB_DRIVER=org.h2.Driver

# MySQL 사용 시 (Docker 필요)
DB_URL=jdbc:mysql://localhost:3306/music_sale_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul
DB_DRIVER=com.mysql.cj.jdbc.Driver
DB_USERNAME=music_user
DB_PASSWORD=music_password
JPA_DIALECT=org.hibernate.dialect.MySQLDialect
```

> **참고**: 환경 변수 로드 순서는 `.env.local` → `.env` → 시스템 환경 변수입니다.

### 2. 애플리케이션 실행

```bash
# H2 사용 시 (Docker 없이 바로 실행)
./gradlew :music-api:bootRun

# MySQL 사용 시 (Docker 먼저 실행)
docker-compose up -d mysql
./gradlew :music-api:bootRun
```

### 3. 접속 확인

| URL | 설명 |
|-----|------|
| http://localhost:8080 | API 서버 |
| http://localhost:8080/swagger-ui/index.html | API 문서 |
| http://localhost:8080/h2-console | H2 콘솔 (H2 사용 시) |
| http://localhost:8080/actuator/health | 헬스 체크 |

## 🔧 개발 환경 설정

### 환경별 주요 설정

| 환경 | DB | JPA_DDL_AUTO | 비고 |
|-----|-----|-------------|------|
| **local** | H2 / MySQL | `create-drop` | 개발용, 매 실행 시 테이블 재생성 |
| **dev** | MySQL | `update` | 개발 서버, 스키마 자동 업데이트 |
| **prod** | MySQL | `validate` | 운영, 스키마 변경 불가 |

### OAuth2 설정 (선택사항)

소셜 로그인을 사용하려면 `.env.local`에 추가:

```bash
SPRING_PROFILES_ACTIVE=local,oauth2
GOOGLE_CLIENT_ID=your-client-id
GOOGLE_CLIENT_SECRET=your-client-secret
# Kakao, Naver도 동일하게 설정
```

### 빌드

```bash
# 전체 프로젝트 빌드
./gradlew build

# 특정 모듈 빌드
./gradlew :music-api:build
```

### 코드 스타일

```bash
# 코드 포맷팅 적용
./gradlew spotlessApply

# 코드 스타일 검사
./gradlew spotlessCheck
```

#### Git Hooks 설정

코드 커밋 전 자동으로 Spotless를 실행하도록 Git Hook을 설정할 수 있습니다:

```bash
# Git hooks 설정 스크립트 실행
./setup-hooks.sh
```

이제 커밋할 때마다 자동으로 코드 포맷팅이 체크되며, 포맷이 올바르지 않으면 커밋이 거부됩니다.

## 🔄 CI/CD

### GitHub Actions

프로젝트는 GitHub Actions를 통한 자동화된 CI/CD 파이프라인을 제공합니다.

#### CI Workflow

`develop` 또는 `main` 브랜치로 push하거나 PR을 생성하면 자동으로 다음 작업이 실행됩니다:

1. **코드 포맷팅 검사** (`spotlessCheck`)
   - Google Java Format 준수 여부 확인
   - Ktlint 준수 여부 확인

2. **빌드** (`./gradlew build`)
   - 전체 프로젝트 컴파일
   - 의존성 해결

3. **테스트** (`./gradlew test`)
   - 단위 테스트 실행
   - 테스트 결과 리포트 생성

4. **아티팩트 업로드**
   - 테스트 결과 보고서
   - 빌드된 JAR 파일

#### Spotless Auto-fix Workflow

Pull Request를 생성하면 **자동으로** 코드 포맷팅을 수정해주는 워크플로우가 실행됩니다:

1. PR이 생성되거나 업데이트되면 자동 실행
2. `spotlessApply`를 실행하여 코드 포맷팅
3. 변경사항이 있으면 자동으로 커밋 & push
4. PR에 완료 코멘트 추가

**장점:**
- 개발자가 수동으로 `spotlessApply`를 실행할 필요 없음
- 포맷팅 때문에 CI가 실패하는 일이 없음
- 코드 리뷰에 집중할 수 있음

### 로컬에서 CI 체크하기

GitHub에 push하기 전에 로컬에서 CI 체크를 실행할 수 있습니다:

```bash
# 포맷팅 체크
./gradlew spotlessCheck

# 빌드
./gradlew build -x test

# 테스트
./gradlew test

# 모두 한 번에 실행
./gradlew spotlessCheck build test
```

### 브랜치 보호 규칙 설정 (필수)

**⚠️ 중요**: Git hooks는 로컬에서만 동작하므로 우회가 가능합니다. 따라서 GitHub 브랜치 보호 규칙을 반드시 설정해야 합니다.

#### 설정 방법:

1. GitHub 저장소 → **Settings** → **Branches**
2. **Branch protection rules** → **Add rule**
3. 다음과 같이 설정:

**보호할 브랜치:**
- `develop`
- `main`

**필수 설정:**
- ✅ **Require status checks to pass before merging**
  - ✅ `Code Formatting Check` (Spotless 체크)
  - ✅ `Build and Test` (빌드 및 테스트)
- ✅ **Require branches to be up to date before merging**
- ✅ **Require pull request before merging**
  - Require approvals: 1명 이상 (권장)
- ✅ **Do not allow bypassing the above settings**

이렇게 설정하면:
- 코드 포맷팅이 맞지 않으면 **merge 불가**
- 테스트가 실패하면 **merge 불가**
- PR 없이 직접 push **불가**
- 관리자도 규칙을 우회할 수 **없음**

## 🚀 CD (Continuous Deployment)

### 자동 배포 워크플로우

`main` 브랜치에 merge되거나 태그가 생성되면 자동으로 배포가 진행됩니다.

#### 배포 트리거

1. **main 브랜치에 push**
   ```bash
   git checkout main
   git merge develop
   git push origin main
   ```

2. **버전 태그 생성**
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

3. **수동 배포** (GitHub Actions 탭에서)
   - Actions → CD → Run workflow

#### 배포 프로세스

1. **빌드**
   - Spring Boot JAR 파일 생성
   - Docker 이미지 빌드

2. **아티팩트 저장**
   - Docker 이미지를 아티팩트로 업로드
   - JAR 파일 업로드

3. **GitHub Release 생성** (태그 push 시)
   - 자동으로 Release Notes 생성
   - 빌드된 파일 첨부

4. **서버 배포** (선택사항)
   - SSH를 통해 서버에 배포
   - Docker 컨테이너 업데이트
   - 헬스 체크

### Docker 이미지 빌드 및 실행

**로컬에서 테스트:**

```bash
# Docker 이미지 빌드
docker build -t music-sale-api:latest -f music-api/Dockerfile .

# 컨테이너 실행
docker run -d \
  --name music-sale-api \
  -p 8080:8080 \
  --env-file .env \
  music-sale-api:latest

# 로그 확인
docker logs -f music-sale-api

# 헬스 체크
curl http://localhost:8080/actuator/health
```

### 서버 배포 설정

실제 서버에 배포하려면 GitHub Secrets에 다음 정보를 설정해야 합니다:

**필수 Secrets (Settings → Secrets and variables → Actions):**

```
SERVER_HOST       # 배포 서버 IP/도메인
SERVER_USER       # SSH 사용자명
SERVER_SSH_KEY    # SSH private key
```

**선택 Secrets (Docker Hub 사용 시):**

```
DOCKER_USERNAME   # Docker Hub 사용자명
DOCKER_PASSWORD   # Docker Hub 비밀번호
```

### 배포 환경 준비

**서버 요구사항:**
- Docker 설치
- Java 21 (선택사항, Docker만 사용 시 불필요)
- 8080 포트 개방

**서버 설정:**

```bash
# 서버에서 실행
# 1. 환경변수 파일 생성
sudo mkdir -p /opt/music-sale
sudo vim /opt/music-sale/.env

# 2. 환경변수 설정 (프로덕션 설정)
SPRING_PROFILES_ACTIVE=prod
DB_HOST=your-db-host
DB_USERNAME=your-db-user
DB_PASSWORD=your-db-password
# ... 기타 필요한 환경변수

# 3. Docker 설치 (없는 경우)
curl -fsSL https://get.docker.com -o get-docker.sh
sudo sh get-docker.sh
```

### CD 워크플로우 활성화

`.github/workflows/cd.yml` 파일에서 다음 설정을 변경:

```yaml
# Docker Hub 사용 시
- name: Log in to Docker Hub (Optional)
  if: true  # false → true로 변경

# 서버 배포 사용 시
deploy-to-server:
  if: true  # false → true로 변경
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
