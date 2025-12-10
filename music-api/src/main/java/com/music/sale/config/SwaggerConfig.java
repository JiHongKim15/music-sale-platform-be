package com.music.sale.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        Contact contact = new Contact();
        contact.setName("Music Sale Team");
        contact.setEmail("contact@musicsale.com");

        String description =
                """
                        악기 판매 플랫폼 API 문서
                        
                        ## 인증 방법 (OAuth2 소셜 로그인)
                        
                        1. 아래 링크 중 하나를 **새 탭에서** 열어 로그인하세요:
                           - [Google 로그인](http://localhost:8080/oauth2/authorization/google)
                           - [Kakao 로그인](http://localhost:8080/oauth2/authorization/kakao)
                           - [Naver 로그인](http://localhost:8080/oauth2/authorization/naver)
                        
                        2. 로그인 성공 후 표시되는 **Access Token**을 복사하세요
                        
                        3. 이 페이지에서 우측 상단 **Authorize** 버튼을 클릭하세요
                        
                        4. `bearerAuth` 입력창에 토큰을 붙여넣고 **Authorize** 클릭
                        """;

        Info info =
                new Info()
                        .title("Music Sale Platform API")
                        .version("1.0.0")
                        .description(description)
                        .contact(contact);

        // JWT Bearer 인증 설정
        SecurityScheme bearerAuthScheme =
                new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .description(
                                "OAuth2 로그인 후 발급받은 JWT 토큰을 입력하세요. "
                                        + "[Google 로그인](http://localhost:8080/oauth2/authorization/google)")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization");

        // SecurityScheme를 Components에 추가하고, 전역 SecurityRequirement 설정
        return new OpenAPI()
                .info(info)
                .servers(List.of(localServer))
                .components(new Components().addSecuritySchemes("bearerAuth", bearerAuthScheme))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
}
