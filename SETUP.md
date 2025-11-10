# 음악 판매 플랫폼 백엔드 - 설치 및 실행 가이드

## 📋 사전 요구사항

### 필수 설치
1. **Docker Desktop** (최신 버전)
   - [Docker Desktop 다운로드](https://www.docker.com/products/docker-desktop/)
   
2. **Java 21** (JDK 21)
   - [OpenJDK 21 다운로드](https://adoptium.net/temurin/releases/?version=21)
   - ⚠️ **주의**: Java 17 이하는 지원하지 않습니다!
   
3. **Git**
   - [Git 다운로드](https://git-scm.com/downloads)

### 선택 설치
- IntelliJ IDEA 또는 VS Code (개발 편의성)

---

## 🚀 설치 및 실행

### 1️⃣ 프로젝트 클론
```bash
git clone <repository-url>
cd music-sale-platform-be
```

### 2️⃣ Docker MySQL 실행
```bash
# Docker 컨테이너 시작 (백그라운드)
docker-compose up -d

# 컨테이너 상태 확인
docker ps
```

**출력 예시:**
```
NAMES              STATUS                 PORTS
mysql-music-sale   Up 5 seconds (healthy) 0.0.0.0:3306->3306/tcp
redis-music-sale   Up 5 seconds (healthy) 0.0.0.0:6379->6379/tcp
```

### 3️⃣ 테스트 데이터 삽입
```bash
# Windows PowerShell
docker exec -i mysql-music-sale mysql -uroot -ppassword music_sale_db < test_data.sql

# Linux/Mac
docker exec -i mysql-music-sale mysql -uroot -ppassword music_sale_db < test_data.sql
```

**데이터 확인:**
```bash
# Windows PowerShell
docker exec mysql-music-sale mysql -uroot -ppassword -D music_sale_db -e "SELECT COUNT(*) as product_count FROM product_item;"

# Linux/Mac
docker exec mysql-music-sale mysql -uroot -ppassword -D music_sale_db -e "SELECT COUNT(*) as product_count FROM product_item;"
```

**출력 예시:**
```
product_count
2
```

### 4️⃣ 백엔드 빌드 및 실행

#### Windows
```powershell
# 빌드 (테스트 및 코드 포맷 검사 제외)
.\gradlew build -x test -x spotlessCheck

# 실행
java -jar music-api\build\libs\music-api.jar
```

#### Linux/Mac
```bash
# 빌드 (테스트 및 코드 포맷 검사 제외)
./gradlew build -x test -x spotlessCheck

# 실행
java -jar music-api/build/libs/music-api.jar
```

**백엔드가 정상 실행되면:**
```
Started MusicSaleApplicationKt in X.XXX seconds
```

### 5️⃣ API 테스트

#### Swagger UI
- URL: http://localhost:8080/swagger-ui/index.html
- 모든 API 엔드포인트 확인 및 테스트 가능

#### API 직접 테스트
```bash
# 상품 목록 조회
curl http://localhost:8080/api/v1/products/search

# 카테고리 목록 조회
curl http://localhost:8080/api/v1/categories/all
```

---

## 🎨 프론트엔드 설치 및 실행

### 1️⃣ Node.js 설치
- [Node.js 18+ 다운로드](https://nodejs.org/)

### 2️⃣ 프론트엔드 디렉토리 이동
```bash
cd ../project_Frontend/music-sale-platform
```

### 3️⃣ 의존성 설치 및 실행
```bash
# 의존성 설치
npm install

# 개발 서버 실행
npm run dev
```

### 4️⃣ 브라우저 접속
- URL: http://localhost:5173

---

## 🛠️ 트러블슈팅

### ❌ 문제: "Port 3306 is already in use"
**원인**: 로컬에 MySQL이 이미 실행 중

**해결 (Windows):**
```powershell
# 관리자 권한으로 PowerShell 실행
Stop-Service MySQL80 -Force
```

**해결 (Linux/Mac):**
```bash
sudo service mysql stop
```

---

### ❌ 문제: "Port 8080 is already in use"
**원인**: 다른 Java 프로세스가 8080 포트 사용 중

**해결 (Windows):**
```powershell
# 8080 포트 사용 중인 프로세스 찾기
netstat -ano | findstr :8080

# 프로세스 종료 (PID는 위 명령어 결과에서 확인)
taskkill /PID <PID> /F
```

**해결 (Linux/Mac):**
```bash
# 8080 포트 사용 중인 프로세스 찾기
lsof -i :8080

# 프로세스 종료
kill -9 <PID>
```

---

### ❌ 문제: "UnsupportedClassVersionError: class file version 65.0"
**원인**: Java 21이 아닌 다른 버전 사용 중

**해결:**
```bash
# 현재 Java 버전 확인
java -version

# Java 21이 설치되어 있다면 JAVA_HOME 환경 변수 설정
# Windows: 시스템 환경 변수에서 JAVA_HOME을 Java 21 경로로 설정
# Linux/Mac: ~/.bashrc 또는 ~/.zshrc에 추가
export JAVA_HOME=/path/to/jdk-21
export PATH=$JAVA_HOME/bin:$PATH
```

---

### ❌ 문제: Docker 컨테이너가 시작되지 않음
**해결:**
```bash
# 기존 컨테이너 및 볼륨 삭제 후 재시작
docker-compose down -v
docker-compose up -d
```

---

### ❌ 문제: 프론트엔드에서 이미지가 보이지 않음
**원인**: 테스트 데이터가 삽입되지 않았거나 백엔드가 실행되지 않음

**해결:**
1. 백엔드가 실행 중인지 확인: http://localhost:8080/swagger-ui/index.html
2. 테스트 데이터 재삽입:
   ```bash
   docker exec -i mysql-music-sale mysql -uroot -ppassword music_sale_db < test_data.sql
   ```
3. 브라우저 강력 새로고침: `Ctrl + Shift + R` (Windows/Linux) 또는 `Cmd + Shift + R` (Mac)

---

## 📊 데이터베이스 초기화

기존 데이터를 삭제하고 새로 시작하려면:

```bash
# 테이블 초기화
docker exec mysql-music-sale mysql -uroot -ppassword -D music_sale_db -e "
TRUNCATE TABLE product_image;
TRUNCATE TABLE product_item;
TRUNCATE TABLE product_catalog;
TRUNCATE TABLE category;
ALTER TABLE category AUTO_INCREMENT = 1;
ALTER TABLE product_catalog AUTO_INCREMENT = 1;
ALTER TABLE product_item AUTO_INCREMENT = 1;
ALTER TABLE product_image AUTO_INCREMENT = 1;
"

# 테스트 데이터 재삽입
docker exec -i mysql-music-sale mysql -uroot -ppassword music_sale_db < test_data.sql
```

---

## 🔒 보안 정보

### 기본 계정 정보 (개발 환경 전용)
- **MySQL Root 비밀번호**: `password`
- **MySQL Database**: `music_sale_db`
- **MySQL User**: `music_user` / `music_password`

⚠️ **주의**: 프로덕션 환경에서는 반드시 비밀번호를 변경하고 환경 변수로 관리하세요!

---

## 📁 프로젝트 구조

```
music-sale-platform-be/
├── docker-compose.yml        # Docker 설정
├── test_data.sql             # 테스트 데이터
├── mysql/
│   └── init/
│       └── 01-init.sql       # 초기 스키마
├── music-api/                # API 레이어
├── music-application/        # 애플리케이션 레이어
├── music-domain/             # 도메인 레이어
└── music-infrastructure/     # 인프라 레이어
```

---

## 🌐 주요 URL

| 서비스 | URL | 설명 |
|--------|-----|------|
| 백엔드 API | http://localhost:8080 | REST API 엔드포인트 |
| Swagger UI | http://localhost:8080/swagger-ui/index.html | API 문서 및 테스트 |
| 프론트엔드 | http://localhost:5173 | React 개발 서버 |
| MySQL | localhost:3306 | 데이터베이스 |
| Redis | localhost:6379 | 캐시 서버 |

---

## 💡 개발 팁

### 1. Hot Reload (백엔드)
IntelliJ IDEA에서 자동 재빌드 활성화:
- `Settings` → `Build, Execution, Deployment` → `Compiler` → `Build project automatically` 체크

### 2. 로그 레벨 변경
`music-api/src/main/resources/application.yml`:
```yaml
logging:
  level:
    com.music.sale: DEBUG  # 상세 로그
    org.hibernate.SQL: DEBUG  # SQL 쿼리 로그
```

### 3. 프론트엔드 Hot Reload
Vite가 자동으로 변경 감지하여 브라우저에 반영합니다. 저장만 하면 됩니다!

---

## 🆘 추가 도움

- **이슈 발생 시**: GitHub Issues에 등록
- **질문**: 팀 슬랙 채널에 문의
- **문서**: [위키 페이지](링크)

---

**Happy Coding! 🚀**

