package com.music.sale;

import com.music.sale.config.ErrorProperties;
import io.github.cdimascio.dotenv.Dotenv;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableConfigurationProperties(ErrorProperties.class)
@EnableJpaAuditing
@SpringBootApplication
public class MusicSaleApplication {

  private static final String[] ENV_FILES = {".env.local", ".env"};

  public static void main(String[] args) {
    loadDotenvIfExists();
    SpringApplication.run(MusicSaleApplication.class, args);
  }

  /**
   * 로컬 개발 환경에서 .env 파일을 로드합니다.
   * 우선순위: .env.local > .env
   * 서버 환경에서는 실제 환경 변수를 사용하므로 파일이 없어도 정상 동작합니다.
   */
  private static void loadDotenvIfExists() {
    String projectRoot = findProjectRoot();

    for (String filename : ENV_FILES) {
      File envFile = new File(projectRoot, filename);
      if (envFile.exists()) {
        try {
          Dotenv.configure()
              .directory(projectRoot)
              .filename(filename)
              .systemProperties()
              .load();
          System.out.println("[ENV] Loaded: " + projectRoot + "/" + filename);
          printLoadedEnvInfo();
          return;
        } catch (Exception e) {
          System.out.println("[ENV] Failed to load " + filename + ": " + e.getMessage());
        }
      }
    }

    System.out.println("[ENV] No .env file found, using system environment variables");
  }

  private static String findProjectRoot() {
    String projectRoot = System.getProperty("user.dir");
    if (projectRoot.endsWith("music-api")) {
      projectRoot = new File(projectRoot).getParent();
    }
    return projectRoot;
  }

  private static void printLoadedEnvInfo() {
    String dbUrl = System.getProperty("DB_URL");
    String dbUsername = System.getProperty("DB_USERNAME");
    String dbPassword = System.getProperty("DB_PASSWORD");

    System.out.println("[ENV] DB_URL=" + (dbUrl != null ? dbUrl : "(default)"));
    System.out.println("[ENV] DB_USERNAME=" + (dbUsername != null ? dbUsername : "(default)"));
    System.out.println("[ENV] DB_PASSWORD=" + (dbPassword != null ? "***SET***" : "(default)"));
  }
}
