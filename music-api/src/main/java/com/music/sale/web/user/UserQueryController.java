package com.music.sale.web.user;

import com.music.sale.application.auth.security.AuthenticatedUser;
import com.music.sale.application.auth.security.CurrentUser;
import com.music.sale.application.user.port.inport.UserQueryUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.user.mapper.UserWebMapper;
import com.music.sale.web.user.response.GetUserResponse;
import com.music.sale.web.user.response.GetUserSocialResponse;
import com.music.sale.web.user.response.GetUserTermsResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserQueryController {

  private final UserQueryUseCase userQueryUseCase;
  private final UserWebMapper mapper;

  @GetMapping("/me")
  public ApiResponse<GetUserResponse> getCurrentUser(@CurrentUser AuthenticatedUser user) {
    GetUserResponse response =
        mapper.toGetUserResponse(userQueryUseCase.getUserById(user.getUserId()));
    return ApiResponse.success(response);
  }

  @GetMapping("/me/socials")
  public ApiResponse<List<GetUserSocialResponse>> getMySocials(@CurrentUser AuthenticatedUser user) {
    List<GetUserSocialResponse> response =
        mapper.toGetUserSocialResponses(userQueryUseCase.getSocialsByUserId(user.getUserId()));
    return ApiResponse.success(response);
  }

  @GetMapping("/me/terms")
  public ApiResponse<List<GetUserTermsResponse>> getMyTerms(@CurrentUser AuthenticatedUser user) {
    List<GetUserTermsResponse> response =
        mapper.toGetUserTermsResponses(userQueryUseCase.getTermsByUserId(user.getUserId()));
    return ApiResponse.success(response);
  }
}
