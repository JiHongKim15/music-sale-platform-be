package com.music.sale.web.auth;

import com.music.sale.common.ApiResponse;
import com.music.sale.web.auth.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "OAuth2", description = "OAuth2 소셜 로그인 API")
@RestController
@RequestMapping("/api/v1/oauth2")
public class OAuth2Controller {

  @Operation(
      summary = "OAuth2 로그인 콜백",
      description =
          "OAuth2 로그인 성공 후 토큰을 반환합니다.\n\n"
              + "## 사용 방법\n"
              + "1. 아래 링크 중 하나를 클릭하여 소셜 로그인\n"
              + "   - [Google 로그인](/oauth2/authorization/google)\n"
              + "   - [Kakao 로그인](/oauth2/authorization/kakao)\n"
              + "   - [Naver 로그인](/oauth2/authorization/naver)\n"
              + "2. 로그인 성공 후 이 API로 리다이렉트됨\n"
              + "3. 응답의 accessToken을 복사\n"
              + "4. Swagger 상단 Authorize 버튼 클릭 후 토큰 입력")
  @GetMapping("/callback")
  public ApiResponse<TokenResponse> oauth2Callback(
      @Parameter(description = "액세스 토큰") @RequestParam String accessToken,
      @Parameter(description = "리프레시 토큰") @RequestParam String refreshToken,
      @Parameter(description = "토큰 타입", hidden = true) @RequestParam(defaultValue = "Bearer")
          String tokenType,
      @Parameter(description = "만료 시간(ms)") @RequestParam(defaultValue = "3600000")
          Long expiresIn) {

    return ApiResponse.success(new TokenResponse(accessToken, refreshToken, expiresIn));
  }
}
