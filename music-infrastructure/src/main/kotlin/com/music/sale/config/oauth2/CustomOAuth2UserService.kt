package com.music.sale.config.oauth2

import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enum.SocialProvider
import com.music.sale.domain.user.enum.UserRole
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class CustomOAuth2UserService(
    private val userPort: UserPort,
) : DefaultOAuth2UserService() {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val oAuth2User = super.loadUser(userRequest)

        val provider =
            SocialProvider.valueOf(userRequest.clientRegistration.registrationId.uppercase())
        val providerId =
            oAuth2User.name ?: throw IllegalArgumentException("Provider ID cannot be null")
        val email = oAuth2User.attributes["email"] as String
        val name = oAuth2User.attributes["name"] as String

        val user =
            userPort.findByProviderAndProviderId(provider, providerId)
                ?: run {
                    val newUser =
                        User(
                            id = null,
                            email = User.Email(email),
                            name = User.Name(name),
                            role = UserRole.USER,
                            provider = provider.name,
                            providerId = providerId,
                        )
                    userPort.save(newUser)
                }

        return oAuth2User
    }
}
