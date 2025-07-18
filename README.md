# 🎵 Music Sale Platform Backend

악기 거래 플랫폼의 백엔드 API 서버입니다. 사용자들이 악기를 구매하고 판매할 수 있는 중고 거래 플랫폼을 제공합니다.

## 🚀 기술 스택

### Backend
- **Language**: Kotlin
- **Framework**: Spring Boot 3.3.1
- **Database**: H2 (개발), MySQL (운영)
- **ORM**: Spring Data JPA, QueryDSL
- **Security**: Spring Security, JWT
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Build Tool**: Gradle (Kotlin DSL)

### Architecture
- **Pattern**: Hexagonal Architecture (Clean Architecture)
- **Module Structure**: Multi-module (API, Application, Infrastructure)

## 📁 프로젝트 구조

```
music-sale-platform-be/
├── music-api/                    # 웹 계층 (API 엔드포인트)
│   ├── src/main/kotlin/
│   │   └── com/music/sale/
│   │       ├── MusicSaleApplication.kt  # 메인 클래스
│   │       └── web/                     # 컨트롤러
│   └── src/main/resources/
│       └── application.yml
├── music-application/            # 비즈니스 로직 계층
│   └── src/main/kotlin/
│       └── com/music/sale/
│           ├── application/      # 서비스, 유스케이스
│           ├── domain/           # 도메인 모델
│           └── common/           # 공통 유틸리티
└── music-infrastructure/         # 데이터 접근 계층
    └── src/main/kotlin/
        └── com/music/sale/
            ├── config/           # 설정 클래스
            └── persistence/      # 데이터베이스 관련
```

## 🏗️ 아키텍처

### Hexagonal Architecture (Clean Architecture)
- **API Layer**: 외부 요청 처리, 컨트롤러
- **Application Layer**: 비즈니스 로직, 유스케이스
- **Domain Layer**: 핵심 비즈니스 규칙, 엔티티
- **Infrastructure Layer**: 데이터베이스, 외부 API 연동

### 의존성 방향
```
API → Application → Domain ← Infrastructure
```

## 🚀 실행 방법

### 1. 사전 요구사항
- Java 21 이상
- Gradle 8.12.1 이상

### 2. 프로젝트 클론
```bash
git clone <repository-url>
cd music-sale-platform-be
```

### 3. 애플리케이션 실행
```bash
# 올바른 실행 방법 (API 모듈만 실행)
./gradlew :music-api:bootRun
```

### 4. 접속 확인
- **애플리케이션**: http://localhost:8081
- **API 문서**: http://localhost:8081/swagger-ui.html
- **H2 콘솔**: http://localhost:8081/h2-console

## 📚 API 문서

### 주요 엔드포인트

#### 상품 관련
- `GET /product` - 상품 목록 조회
- `GET /product/{id}` - 상품 상세 조회
- `POST /product` - 상품 등록
- `PUT /product/{id}` - 상품 수정
- `DELETE /product/{id}` - 상품 삭제

#### 사용자 관련
- `POST /auth/register` - 회원가입
- `POST /auth/login` - 로그인
- `GET /user/profile` - 사용자 프로필 조회
- `PUT /user/profile` - 사용자 프로필 수정

#### 장바구니 관련
- `GET /cart` - 장바구니 조회
- `POST /cart` - 장바구니에 상품 추가
- `PUT /cart/{id}` - 장바구니 상품 수량 수정
- `DELETE /cart/{id}` - 장바구니에서 상품 제거

#### 위시리스트 관련
- `GET /wishlist` - 위시리스트 조회
- `POST /wishlist` - 위시리스트에 상품 추가
- `DELETE /wishlist/{id}` - 위시리스트에서 상품 제거

## 🗄️ 데이터베이스

### 주요 테이블
- `users` - 사용자 정보
- `auth_users` - 인증 정보
- `product_catalog` - 상품 카탈로그
- `product_item` - 개별 상품
- `category` - 카테고리
- `cart` - 장바구니
- `wishlist` - 위시리스트
- `orders` - 주문
- `order_items` - 주문 상품
- `store` - 매장 정보

### 초기 데이터
- 기본 카테고리 (기타, 베이스, 드럼, 건반악기, 관악기 등)
- 샘플 상품 (펜더 기타, 테일러 기타, 롤랜드 신디사이저 등)
- 테스트 사용자 (판매자, 구매자)

## 🔧 설정

### application.yml 주요 설정
```yaml
server:
  port: 8081

spring:
  datasource:
    url: jdbc:h2:mem:music_sale_db
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
  h2:
    console:
      enabled: true
```

## 🛠️ 개발 가이드

### 멀티모듈 구조 원칙
1. **단일 진입점**: `music-api` 모듈에만 메인 클래스 존재
2. **관심사 분리**: 각 모듈은 명확한 책임을 가짐
3. **의존성 방향**: API → Application → Infrastructure

### 코드 컨벤션
- **Clean Code 원칙** 준수
- **함수는 단일 책임** 원칙
- **try-catch는 필요한 곳에만** 사용
- **한국어 주석** 사용

### 빌드 및 테스트
```bash
# 전체 빌드
./gradlew build

# 테스트 실행
./gradlew test

# 특정 모듈 빌드
./gradlew :music-api:build
```

## 🔒 보안

### 현재 설정 (개발용)
- Spring Security 완전 비활성화 (테스트 목적)
- 모든 엔드포인트 인증 없이 접근 가능

### 운영 환경 설정
- JWT 기반 인증
- Spring Security 활성화
- CORS 설정
- Rate Limiting

## 📝 주요 기능

### 사용자 관리
- 회원가입/로그인
- 소셜 로그인 (Google, Kakao, Naver)
- 프로필 관리
- 판매자 인증

### 상품 관리
- 상품 등록/수정/삭제
- 카테고리별 분류
- 검색 및 필터링
- 상품 상태 관리 (판매중, 판매완료, 취소)

### 거래 기능
- 장바구니
- 위시리스트
- 주문/결제
- 배송 관리

### 기타 기능
- 상품 조회수 관리
- 실시간 알림 (WebSocket)
- 파일 업로드

## 🐛 문제 해결

### 자주 발생하는 문제

1. **포트 충돌**
   ```bash
   lsof -ti:8081 | xargs kill -9
   ```

2. **Redis 연결 오류**
   - 개발 환경에서는 Redis 없이도 동작
   - 운영 환경에서 Redis 설정 필요

3. **멀티모듈 실행 오류**
   - `./gradlew bootRun` 대신 `./gradlew :music-api:bootRun` 사용
