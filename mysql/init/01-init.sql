-- MySQL 초기 설정 (테이블은 Hibernate가 Entity 기준으로 생성)
USE music_sale_db;

-- 기존 테이블이 있으면 삭제 (Hibernate가 새로 생성하도록)
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `likes`;
DROP TABLE IF EXISTS `user_terms`;
DROP TABLE IF EXISTS `user_socials`;
DROP TABLE IF EXISTS `product_image`;
DROP TABLE IF EXISTS `product_item`;
DROP TABLE IF EXISTS `product_catalog`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `shipping_policies`;
DROP TABLE IF EXISTS `store`;
DROP TABLE IF EXISTS `refresh_token`;
DROP TABLE IF EXISTS `users`;
SET FOREIGN_KEY_CHECKS = 1;

SELECT 'MySQL 초기화 완료 - 테이블은 Hibernate가 생성합니다' as message;
