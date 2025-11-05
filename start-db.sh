#!/bin/bash

echo "🚀 MySQL 데이터베이스 시작..."

# Docker가 설치되어 있는지 확인
if ! command -v docker &> /dev/null; then
    echo "❌ Docker가 설치되어 있지 않습니다."
    echo "👉 Docker Desktop 설치: https://www.docker.com/products/docker-desktop"
    exit 1
fi

# Docker Compose로 MySQL 시작
echo "📦 MySQL 컨테이너 시작 중..."
if command -v docker-compose &> /dev/null; then
    docker-compose up -d mysql
else
    docker compose up -d mysql
fi

# MySQL이 준비될 때까지 대기
echo "⏳ MySQL이 준비될 때까지 대기 중..."
sleep 10

# 테이블 생성
echo "📋 likes 테이블 생성 중..."
if command -v docker-compose &> /dev/null; then
    docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/V1__create_likes_table.sql
else
    docker exec -i mysql-music-sale mysql -u root -ppassword music_sale_db < mysql/V1__create_likes_table.sql
fi

# 테이블 확인
echo "✅ 생성된 테이블 확인:"
docker exec mysql-music-sale mysql -u root -ppassword -e "USE music_sale_db; SHOW TABLES;"

echo ""
echo "✨ MySQL 준비 완료!"
echo "📍 접속 정보:"
echo "   Host: localhost"
echo "   Port: 3306"
echo "   Database: music_sale_db"
echo "   Username: root"
echo "   Password: password"
echo ""
echo "🎯 다음 명령어로 애플리케이션 실행:"
echo "   ./gradlew bootRun"

