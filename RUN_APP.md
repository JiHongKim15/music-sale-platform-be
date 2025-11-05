# 🚀 가장 빠른 실행 방법

## IntelliJ에서 실행 (3단계)

### 1️⃣ 파일 열기
`music-api/src/main/java/com/music/sale/MusicSaleApplication.java`

또는 

`music-api/src/main/kotlin/com/music/sale/MusicSaleApplication.kt`

### 2️⃣ 실행
- 파일 안에서 우클릭
- "Run 'MusicSaleApplication'" 클릭

또는

- 파일 상단의 녹색 ▶️ 버튼 클릭

### 3️⃣ 확인
콘솔에서 다음 로그 확인:

```sql
Hibernate: 
    create table if not exists likes (
        id bigint not null auto_increment,
        user_id bigint not null,
        likeable_id bigint not null,
        likeable_type varchar(20) not null,
        created_at datetime(6) not null,
        primary key (id)
    ) engine=InnoDB

Hibernate: 
    create unique index uix_user_likeable 
    on likes (user_id, likeable_type, likeable_id)
```

✅ **테이블이 자동으로 생성됩니다!**

---

## Swagger UI에서 테스트

애플리케이션 실행 후:

```
http://localhost:8080/swagger-ui/index.html
```

여기서 **Like Controller**를 찾아서 API 테스트!

---

## Gradle로 실행 (Terminal)

```bash
cd /Users/hydro/Desktop/java1/back_end/music-sale-platform-be

# 방법 1: bootRun
./gradlew clean bootRun

# 방법 2: jar 빌드 후 실행
./gradlew clean bootJar -x test
java -jar music-api/build/libs/*.jar
```

---

## 🎯 중요!

**JPA 설정이 이미 되어 있어서**:

```yaml
jpa:
  hibernate:
    ddl-auto: update  # 테이블 자동 생성!
  show-sql: true      # SQL 로그 출력!
```

**→ 애플리케이션만 실행하면 테이블이 자동으로 생성됩니다!** 🎉

Docker, MySQL 수동 설치 필요 없음!

