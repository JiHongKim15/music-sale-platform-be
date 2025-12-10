# 인증 시스템 가이드

## 개요

본 프로젝트는 **JWT + OAuth2 소셜 로그인** 기반의 인증 시스템을 사용합니다.
`AuthenticatedUser`는 인증된 사용자 정보를 담는 객체로, JWT 토큰에서 파싱되어 요청 동안 유지됩니다.

---

## 인증 흐름

```
┌─────────────────────────────────────────────────────────────────────────┐
│  1. OAuth2 소셜 로그인                                                   │
│     User → Google/Kakao/Naver → OAuth2LoginSuccessHandler               │
│                                        ↓                                │
│                                 JWT 토큰 발급                            │
└─────────────────────────────────────────────────────────────────────────┘
                                        ↓
┌─────────────────────────────────────────────────────────────────────────┐
│  2. API 요청 시                                                          │
│     Header: Authorization: Bearer {JWT_TOKEN}                           │
│                                        ↓                                │
│                           JwtAuthenticationFilter                       │
│                                        ↓                                │
│                      JWT 파싱 → AuthenticatedUser 생성                   │
│                                        ↓                                │
│                         SecurityContextHolder에 저장                     │
└─────────────────────────────────────────────────────────────────────────┘
                                        ↓
┌─────────────────────────────────────────────────────────────────────────┐
│  3. Controller에서 사용                                                  │
│     @CurrentUser AuthenticatedUser user                                 │
│                                        ↓                                │
│                    user.userId(), user.email() 등 사용                   │
└─────────────────────────────────────────────────────────────────────────┘
```

---

## AuthenticatedUser 구조

```java
// 위치: music-application/.../auth/security/AuthenticatedUser.java

public record AuthenticatedUser(
    Long userId,      // 사용자 ID
    String email,     // 이메일
    String nickname,  // 닉네임
    UserRole role     // 권한 (USER, SELLER, ADMIN)
) implements UserDetails {
    // ...
}
```

### JWT 토큰에 포함된 정보

| 필드 | 설명 | 예시 |
|------|------|------|
| `userId` | 사용자 고유 ID | `123` |
| `email` | 이메일 주소 | `user@gmail.com` |
| `nickname` | 닉네임 | `홍길동` |
| `role` | 사용자 권한 | `USER`, `SELLER`, `ADMIN` |

> **중요**: JWT에 이미 사용자 정보가 포함되어 있으므로, **DB 조회 없이** `AuthenticatedUser`를 사용할 수 있습니다.

---

## 사용 방법

### 1. Controller에서 현재 사용자 정보 사용하기

```java
import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.application.auth.security.CurrentUser;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping("/api/v1/products")
public class ProductCommandController {

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @Parameter(hidden = true) @CurrentUser AuthenticatedUser user,  // 인증된 사용자
            @RequestBody @Valid CreateProductInput input) {

        // user.userId()로 현재 로그인한 사용자 ID 사용
        ProductOutput output = productCommandUseCase.createProduct(input, user.userId());
        return ApiResponse.success(productWebMapper.toProductResponse(output));
    }
}
```

### 2. 사용 가능한 메서드

```java
@GetMapping("/example")
public ApiResponse<?> example(@CurrentUser AuthenticatedUser user) {

    Long userId = user.userId();           // 사용자 ID
    String email = user.email();           // 이메일
    String nickname = user.nickname();     // 닉네임
    UserRole role = user.role();           // 권한

    // 권한 확인
    if (user.role() == UserRole.ADMIN) {
        // 관리자 전용 로직
    }

    return ApiResponse.success(...);
}
```

### 3. Swagger 문서에서 파라미터 숨기기

`@CurrentUser` 파라미터는 JWT에서 자동으로 주입되므로, Swagger UI에 표시할 필요가 없습니다.

```java
@Parameter(hidden = true) @CurrentUser AuthenticatedUser user
```

---

## 언제 DB 조회가 필요한가?

| 상황 | DB 조회 | 방법 |
|------|---------|------|
| `userId`만 필요 | **불필요** | `user.userId()` |
| `email`, `nickname` 필요 | **불필요** | `user.email()`, `user.nickname()` |
| 프로필 이미지, 상태 등 상세 정보 필요 | **필요** | `userQueryUseCase.getUserById(user.userId())` |

### 예시: 상세 정보가 필요한 경우

```java
@GetMapping("/me")
public ApiResponse<GetUserResponse> getCurrentUser(@CurrentUser AuthenticatedUser user) {
    // JWT에 없는 상세 정보(프로필 이미지 등)가 필요하므로 DB 조회
    GetUserResponse response = mapper.toGetUserResponse(
        userQueryUseCase.getUserById(user.userId())
    );
    return ApiResponse.success(response);
}
```

### 예시: userId만 필요한 경우 (DB 조회 불필요)

```java
@PostMapping("/{targetId}")
public ApiResponse<CreateLikeResponse> likeTarget(
        @CurrentUser AuthenticatedUser user,
        @PathVariable Long targetId) {
    // userId만 필요하므로 DB 조회 없이 바로 사용
    CreateLikeInput input = mapper.toCreateLikeInput(user.userId(), targetId, likeableType);
    return ApiResponse.success(mapper.toCreateLikeResponse(likeCommandUseCase.createLike(input)));
}
```

---

## 내부 동작 원리

### 1단계: JwtAuthenticationFilter

모든 요청은 `JwtAuthenticationFilter`를 거칩니다.

```java
// 위치: music-infrastructure/.../jwt/JwtAuthenticationFilter.java

@Override
protected void doFilterInternal(HttpServletRequest request, ...) {
    // 1. Header에서 토큰 추출
    String token = extractToken(request);  // "Bearer xxx" → "xxx"

    if (token != null) {
        // 2. 토큰 검증 & 파싱
        Map<String, Object> claims = jwtTokenProvider.validateToken(token);

        // 3. AuthenticatedUser 생성 (DB 조회 없음!)
        AuthenticatedUser user = AuthenticatedUser.of(
            claims.get("userId"),
            claims.get("email"),
            claims.get("nickname"),
            claims.get("role")
        );

        // 4. SecurityContext에 저장
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities())
        );
    }
}
```

### 2단계: @CurrentUser 어노테이션

```java
// 위치: music-application/.../auth/security/CurrentUser.java

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@AuthenticationPrincipal  // Spring Security의 Principal 주입
public @interface CurrentUser {}
```

`@CurrentUser`는 `@AuthenticationPrincipal`의 커스텀 래퍼로, `SecurityContextHolder`에 저장된 `AuthenticatedUser`를 Controller 파라미터로 주입합니다.

---

## Swagger UI에서 테스트하기

### 1. OAuth2 로그인으로 토큰 발급

브라우저에서 아래 링크 중 하나로 접속:
- Google: `http://localhost:8080/oauth2/authorization/google`
- Kakao: `http://localhost:8080/oauth2/authorization/kakao`
- Naver: `http://localhost:8080/oauth2/authorization/naver`

### 2. 토큰 복사

로그인 성공 후 표시되는 Access Token을 복사합니다.

### 3. Swagger UI에서 인증

1. `http://localhost:8080/swagger-ui/index.html` 접속
2. 우측 상단 **Authorize** 버튼 클릭
3. `bearerAuth` 입력창에 토큰 붙여넣기
4. **Authorize** → **Close**

### 4. API 테스트

이제 인증이 필요한 API를 테스트할 수 있습니다.
예: `GET /api/v1/users/me` → 현재 로그인한 사용자 정보 반환

---

## 체크리스트

Controller 개발 시 확인사항:

- [ ] `@CurrentUser AuthenticatedUser user` 파라미터 추가
- [ ] `@Parameter(hidden = true)` 추가 (Swagger에서 숨김)
- [ ] `user.userId()` 사용 (하드코딩 `1L` 금지)
- [ ] DB 조회가 정말 필요한지 검토 (대부분 불필요)

---

## 관련 파일

| 파일 | 위치 | 설명 |
|------|------|------|
| `AuthenticatedUser.java` | `music-application/.../auth/security/` | 인증 사용자 DTO |
| `CurrentUser.java` | `music-application/.../auth/security/` | 커스텀 어노테이션 |
| `JwtAuthenticationFilter.java` | `music-infrastructure/.../jwt/` | JWT 필터 |
| `JwtTokenProvider.java` | `music-infrastructure/.../jwt/` | JWT 생성/검증 |
| `OAuth2LoginSuccessHandler.java` | `music-infrastructure/.../oauth2/handler/` | OAuth2 성공 핸들러 |
