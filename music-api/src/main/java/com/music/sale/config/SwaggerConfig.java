package com.music.sale.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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

    return new OpenAPI().info(info).servers(List.of(localServer));
  }
}
