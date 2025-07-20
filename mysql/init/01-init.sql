-- MySQL 초기화 스크립트
-- 데이터베이스 생성 및 사용자 권한 설정

-- 데이터베이스 생성 (이미 환경변수로 설정됨)
-- CREATE DATABASE IF NOT EXISTS music_sale_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- root 사용자 권한 설정 (MySQL 8.0 호환)
-- 모든 호스트에서 접근 가능하도록 설정
CREATE USER IF NOT EXISTS 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'password';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'localhost' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

-- music_user 사용자 권한 설정
CREATE USER IF NOT EXISTS 'music_user'@'%' IDENTIFIED WITH mysql_native_password BY 'music_password';
GRANT ALL PRIVILEGES ON music_sale_db.* TO 'music_user'@'%' WITH GRANT OPTION;

-- 권한 적용
FLUSH PRIVILEGES;

-- 데이터베이스 선택
USE music_sale_db;

-- 테이블 생성 확인을 위한 간단한 쿼리
SELECT 'MySQL initialization completed successfully!' as status; 