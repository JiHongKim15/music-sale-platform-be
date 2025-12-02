package com.music.sale;

import com.music.sale.config.ErrorProperties;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(ErrorProperties.class)
@EnableJpaAuditing
@SpringBootApplication
public class MusicSaleApplication {

  public static void main(String[] args) {
    try {
      Dotenv.configure()
          .directory(System.getProperty("user.dir"))
          .filename(".env.local")
          .systemProperties()
          .load();
    } catch (Exception ex) {
      System.out.println("Warning: .env.local file not found, using default configuration");
    }

    SpringApplication.run(MusicSaleApplication.class, args);
  }
}
