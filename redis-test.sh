#!/bin/bash

echo "🚀 Redis 테스트 시작..."

# Redis 연결 테스트
echo "1. Redis 연결 테스트..."
docker exec redis-music-sale redis-cli ping

# 기본 키-값 테스트
echo -e "\n2. 기본 키-값 테스트..."
docker exec redis-music-sale redis-cli set test:key "Hello Redis!"
docker exec redis-music-sale redis-cli get test:key

# 만료 시간 테스트
echo -e "\n3. 만료 시간 테스트..."
docker exec redis-music-sale redis-cli setex test:expire 10 "10초 후 만료"
docker exec redis-music-sale redis-cli ttl test:expire

# Hash 테스트
echo -e "\n4. Hash 테스트..."
docker exec redis-music-sale redis-cli hset user:1 name "김지홍" age "30" email "jihong@example.com"
docker exec redis-music-sale redis-cli hgetall user:1

# List 테스트
echo -e "\n5. List 테스트..."
docker exec redis-music-sale redis-cli lpush music:list "피아노" "기타" "드럼" "베이스"
docker exec redis-music-sale redis-cli lrange music:list 0 -1

# Set 테스트
echo -e "\n6. Set 테스트..."
docker exec redis-music-sale redis-cli sadd category:set "악기" "음악장비" "악보" "악기부속품"
docker exec redis-music-sale redis-cli smembers category:set

# 데이터베이스 정보
echo -e "\n7. Redis 정보..."
docker exec redis-music-sale redis-cli info keyspace

echo -e "\n✅ Redis 테스트 완료!" 
