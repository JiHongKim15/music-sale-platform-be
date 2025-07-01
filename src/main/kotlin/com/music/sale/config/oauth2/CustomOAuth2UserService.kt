package com.music.sale.config.oauth2

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User

class CustomOAuth2UserService : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val delegate = DefaultOAuth2UserService()

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        // provider별로 필요한 정보 추출 및 가공
        val oAuth2User = delegate.loadUser(userRequest)
        // 여기서 provider, userNameAttributeName, attributes 등 가공 가능
        return oAuth2User
    }
} 
