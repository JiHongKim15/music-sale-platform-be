# Docker 로컬 개발 환경 가이드

이 문서는 Docker를 사용하여 로컬 개발 환경을 설정하는 방법을 안내합니다.

## 📋 사전 요구사항

- Docker Desktop 설치 필요
  - Mac: https://docs.docker.com/desktop/install/mac-install/
  - Windows: https://docs.docker.com/desktop/install/windows-install/
  - Linux: https://docs.docker.com/desktop/install/linux-install/

## 🚀 빠른 시작

### 1. Docker 컨테이너 시작

```bash
# MySQL + Redis 모두 시작
docker-compose up -d

# MySQL만 시작
docker-compose up -d mysql

# Redis만 시작
docker-compose up -d redis
```

### 2. 컨테이너 상태 확인

```bash
docker-compose ps
```

**정상 출력 예시:**
```
NAME                  COMMAND                  SERVICE             STATUS              PORTS
mysql-music-sale      "docker-entrypoint.s…"   mysql               running (healthy)   0.0.0.0:3306->3306/tcp
redis-music-sale      "docker-entrypoint.s…"   redis               running (healthy)   0.0.0.0:6379->6379/tcp
```

### 3. 애플리케이션 실행

```bash
# .env.local 파일이 MySQL 설정으로 되어있는지 확인
cat .env.local | grep DB_

# 애플리케이션 실행
./gradlew :music-api:bootRun
```

## 🗄️ 데이터베이스 정보

### MySQL

- **Host**: `localhost`
- **Port**: `3306`
- **Database**: `music_sale_db`
- **Username**: `music_user`
- **Password**: `root`
- **Root Password**: `password`

### Redis

- **Host**: `localhost`
- **Port**: `6379`
- **Password**: 없음
- **Database**: `0` (기본값)

## 📂 초기 데이터

Docker 컨테이너를 시작하면 다음 초기 데이터가 자동으로 로드됩니다:

### 카테고리
- 기타 (어쿠스틱, 일렉트릭, 클래식)
- 베이스 (일렉트릭, 어쿠스틱)
- 드럼 (드럼 세트, 심벌즈)
- 키보드/피아노 (디지털 피아노, 신디사이저)
- 악기 액세서리 (앰프, 케이블, 스트링)

### 사용자
- **관리자**: `admin@musicsale.com` / 비밀번호: `password123`
- **판매자**: `seller1@musicsale.com` / 비밀번호: `password123`
- **구매자**: `user1@musicsale.com` / 비밀번호: `password123`

### 상품
- Fender Stratocaster (중고, 1,200,000원)
- Gibson Les Paul (신품, 2,500,000원)
- Martin D-28 (중고, 1,800,000원)
- Fender Precision Bass (중고, 800,000원)
- Pearl Export (중고, 1,200,000원)
- Yamaha P-125 (신품, 800,000원)

## 🔧 자주 사용하는 Docker 명령어

### 컨테이너 관리

```bash
# 모든 컨테이너 시작
docker-compose up -d

# 컨테이너 중지
docker-compose down

# 컨테이너 재시작
docker-compose restart

# 특정 컨테이너만 재시작
docker-compose restart mysql
docker-compose restart redis
```

### 로그 확인

```bash
# 모든 컨테이너 로그 확인
docker-compose logs

# 특정 컨테이너 로그 확인
docker-compose logs mysql
docker-compose logs redis

# 실시간 로그 보기 (-f)
docker-compose logs -f mysql

# 최근 100줄만 보기
docker-compose logs --tail=100 mysql
```

### MySQL 접속

```bash
# MySQL CLI 접속 (root 계정)
docker exec -it mysql-music-sale mysql -u root -ppassword

# MySQL CLI 접속 (music_user 계정)
docker exec -it mysql-music-sale mysql -u music_user -proot music_sale_db
```

**MySQL 접속 후 자주 사용하는 명령어:**
```sql
-- 데이터베이스 선택
USE music_sale_db;

-- 테이블 목록 확인
SHOW TABLES;

-- 상품 목록 확인
SELECT id, name, price, status FROM product_item;

-- 사용자 목록 확인
SELECT id, email, name, role FROM users;

-- 카테고리 목록 확인
SELECT id, name, type, parent_id FROM category;
```

### Redis 접속

```bash
# Redis CLI 접속
docker exec -it redis-music-sale redis-cli

# Redis 연결 테스트
docker exec -it redis-music-sale redis-cli ping
# 응답: PONG
```

**Redis CLI 명령어:**
```bash
# 모든 키 확인
KEYS *

# 특정 키 값 확인
GET key_name

# Redis 정보 확인
INFO

# 종료
EXIT
```

## 🗑️ 데이터 초기화

### 데이터만 초기화 (컨테이너 유지)

```bash
# 컨테이너 중지 및 제거
docker-compose down

# 다시 시작 (초기 데이터 자동 로드)
docker-compose up -d
```

### 완전 초기화 (볼륨까지 삭제)

```bash
# 컨테이너와 볼륨 모두 삭제
docker-compose down -v

# 다시 시작
docker-compose up -d
```

**주의**: `docker-compose down -v`를 실행하면 모든 데이터가 삭제됩니다!

## 🔍 트러블슈팅

### 1. 포트 충돌 오류

**증상**: `Bind for 0.0.0.0:3306 failed: port is already allocated`

**해결 방법**:
```bash
# 3306 포트를 사용 중인 프로세스 확인
lsof -i :3306

# 기존 MySQL 서비스 중지 (Mac)
brew services stop mysql

# 또는 docker-compose.yml에서 포트 변경
# ports:
#   - "3307:3306"  # 호스트 포트를 3307로 변경
```

### 2. 컨테이너가 시작되지 않음

**해결 방법**:
```bash
# 로그 확인
docker-compose logs mysql

# 컨테이너 완전 재시작
docker-compose down
docker-compose up -d

# Docker Desktop 재시작
```

### 3. 데이터베이스 연결 오류

**증상**: `Communications link failure`

**해결 방법**:
```bash
# 컨테이너 상태 확인
docker-compose ps

# MySQL이 완전히 시작될 때까지 대기 (약 10-30초)
docker-compose logs -f mysql | grep "ready for connections"

# 연결 테스트
docker exec -it mysql-music-sale mysql -u root -ppassword -e "SELECT 1"
```

### 4. 초기 데이터가 로드되지 않음

**해결 방법**:
```bash
# 볼륨 삭제 후 재시작
docker-compose down -v
docker-compose up -d

# 수동으로 초기 데이터 실행
docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/init/01-init.sql
docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/init/02-import.sql
```

## 📊 데이터 백업 및 복원

### 데이터 백업

```bash
# 전체 데이터베이스 백업
docker exec mysql-music-sale mysqldump -u root -ppassword music_sale_db > backup.sql

# 특정 테이블만 백업
docker exec mysql-music-sale mysqldump -u root -ppassword music_sale_db users product_item > backup_users_products.sql
```

### 데이터 복원

```bash
# 백업 파일로 복원
docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < backup.sql
```

## 🌐 GUI 도구 접속

### MySQL Workbench

- **Connection Name**: Music Sale Local
- **Hostname**: `localhost` 또는 `127.0.0.1`
- **Port**: `3306`
- **Username**: `music_user` (또는 `root`)
- **Password**: `root` (또는 `password`)
- **Default Schema**: `music_sale_db`

### DBeaver

1. 새 연결 생성
2. MySQL 선택
3. 다음 정보 입력:
   - Host: `localhost`
   - Port: `3306`
   - Database: `music_sale_db`
   - Username: `music_user`
   - Password: `root`

### RedisInsight (Redis GUI)

1. RedisInsight 다운로드: https://redis.io/insight/
2. 새 데이터베이스 추가
3. 다음 정보 입력:
   - Host: `localhost`
   - Port: `6379`

## 🔄 프로덕션 환경과의 차이

| 설정 | 로컬 (Docker) | 프로덕션 |
|------|--------------|----------|
| DB Host | localhost:3306 | [프로덕션 DB 주소] |
| 초기 데이터 | 자동 로드 | 마이그레이션 |
| 볼륨 | 로컬 Docker 볼륨 | 영구 스토리지 |
| 백업 | 수동 | 자동 (정기적) |
| SSL/TLS | 비활성화 | 활성화 (필수) |

## ❓ 자주 묻는 질문

### Q1: Docker를 사용해야 하나요?

로컬 개발 시 Docker를 사용하면:
- ✅ 일관된 개발 환경 제공
- ✅ MySQL 설치 불필요
- ✅ 프로덕션 환경과 유사한 환경
- ✅ 팀원들과 동일한 환경 공유

하지만 H2 인메모리 DB도 여전히 사용 가능합니다.

### Q2: Docker 없이 개발할 수 있나요?

네, H2 인메모리 DB를 사용하면 Docker 없이도 개발 가능합니다.

`.env.local` 파일을 다음과 같이 수정:
```bash
DB_URL=jdbc:h2:mem:testdb
DB_DRIVER=org.h2.Driver
DB_USERNAME=sa
DB_PASSWORD=
JPA_DIALECT=org.hibernate.dialect.H2Dialect
```

### Q3: 데이터가 자꾸 사라져요

`docker-compose down`을 실행하면 컨테이너는 삭제되지만 볼륨은 유지됩니다.
`docker-compose down -v`를 실행하면 볼륨까지 삭제되어 모든 데이터가 사라집니다.

**데이터 유지**: `docker-compose down` → `docker-compose up -d`
**데이터 초기화**: `docker-compose down -v` → `docker-compose up -d`

## 📚 추가 리소스

- [Docker 공식 문서](https://docs.docker.com/)
- [Docker Compose 공식 문서](https://docs.docker.com/compose/)
- [MySQL Docker 이미지](https://hub.docker.com/_/mysql)
- [Redis Docker 이미지](https://hub.docker.com/_/redis)
