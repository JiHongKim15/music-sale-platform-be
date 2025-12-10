package com.music.sale.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    Info info =
        new Info()
            .title("Music Sale Platform API")
            .version("1.0.0")
            .description("악기 판매 플랫폼 API 문서")
            .contact(contact);

    // JWT Bearer 인증 설정
    SecurityScheme bearerAuthScheme =
        new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
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
