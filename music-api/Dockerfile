# Multi-stage build for Spring Boot application

# Stage 1: Build stage (not used in CD as we build via GitHub Actions)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /workspace/app
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts settings.gradle.kts ./
COPY music-api/build.gradle.kts music-api/
COPY music-application/build.gradle.kts music-application/
COPY music-domain/build.gradle.kts music-domain/
COPY music-infrastructure/build.gradle.kts music-infrastructure/
RUN ./gradlew dependencies --no-daemon
COPY . .
RUN ./gradlew :music-api:bootJar -x test --no-daemon

# Stage 2: Runtime stage
FROM eclipse-temurin:21-jre-alpine

# 보안 및 성능을 위한 non-root 유저 생성
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# JAR 파일 복사 (GitHub Actions에서 빌드된 파일 사용)
ARG JAR_FILE=music-api/build/libs/*.jar
COPY ${JAR_FILE} app.jar

# 헬스체크
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# 환경변수 설정
ENV JAVA_OPTS="-Xms512m -Xmx1024m -XX:+UseG1GC -XX:MaxGCPauseMillis=200"
ENV SPRING_PROFILES_ACTIVE=prod

# 포트 노출
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
