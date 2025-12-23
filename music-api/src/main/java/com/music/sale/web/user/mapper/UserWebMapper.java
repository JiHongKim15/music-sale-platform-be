package com.music.sale.web.user.mapper;

import com.music.sale.application.user.dto.output.UserOutput;
import com.music.sale.application.user.dto.output.UserSocialOutput;
import com.music.sale.application.user.dto.output.UserTermsOutput;
import com.music.sale.web.user.response.GetUserResponse;
import com.music.sale.web.user.response.GetUserSocialResponse;
import com.music.sale.web.user.response.GetUserTermsResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {

  public GetUserResponse toGetUserResponse(UserOutput output) {
    return new GetUserResponse(
        output.id(),
        output.nickname(),
        output.profileImageUrl(),
        output.email(),
        output.role(),
        output.status(),
        output.isVerified(),
        output.createdAt());
  }

  public List<GetUserSocialResponse> toGetUserSocialResponses(List<UserSocialOutput> outputs) {
    return outputs.stream().map(this::toGetUserSocialResponse).toList();
  }

  public GetUserSocialResponse toGetUserSocialResponse(UserSocialOutput output) {
    return new GetUserSocialResponse(output.provider(), output.providerId());
  }

  public List<GetUserTermsResponse> toGetUserTermsResponses(List<UserTermsOutput> outputs) {
    return outputs.stream().map(this::toGetUserTermsResponse).toList();
  }

  public GetUserTermsResponse toGetUserTermsResponse(UserTermsOutput output) {
    return new GetUserTermsResponse(
        output.title(), output.version(), output.isAgreed(), output.agreedAt());
  }
}
