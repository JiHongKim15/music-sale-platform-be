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
      // Find project root (go up from music-api to project root)
      String projectRoot = System.getProperty("user.dir");
      if (projectRoot.endsWith("music-api")) {
        projectRoot = new java.io.File(projectRoot).getParent();
      }

      Dotenv dotenv = Dotenv.configure()
          .directory(projectRoot)
          .filename(".env.local")
          .systemProperties()
          .load();
      System.out.println("Loaded environment from: " + projectRoot + "/.env.local");
      System.out.println("DB_URL=" + System.getProperty("DB_URL"));
      System.out.println("DB_USERNAME=" + System.getProperty("DB_USERNAME"));
      System.out.println("DB_PASSWORD=" + (System.getProperty("DB_PASSWORD") != null ? "***SET***" : "NOT SET"));
    } catch (Exception ex) {
      System.out.println("Warning: .env.local file not found, using default configuration");
    }

    SpringApplication.run(MusicSaleApplication.class, args);
  }
}
