-- Initial data import file
-- 악기 판매 플랫폼 초기 데이터

-- 기존 데이터 삭제 (초기화) - 외래키 제약조건을 고려한 순서
DELETE FROM product_item;
DELETE FROM product_catalog;
DELETE FROM store;
DELETE FROM auth_users;
DELETE FROM users;
DELETE FROM category WHERE parent_id IS NOT NULL;
DELETE FROM category WHERE parent_id IS NULL;

-- ========================================
-- 카테고리 데이터 (악기 분류)
-- ========================================

-- 1. 기타 카테고리
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('기타', 'PRODUCT', NULL, '/1', 0, 1, NOW(), 'system', NOW(), 'system');

SET @guitar_id = LAST_INSERT_ID();

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('어쿠스틱 기타', 'PRODUCT', @guitar_id, CONCAT('/1/', @guitar_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('일렉트릭 기타', 'PRODUCT', @guitar_id, CONCAT('/1/', @guitar_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('클래식 기타', 'PRODUCT', @guitar_id, CONCAT('/1/', @guitar_id), 1, 1, NOW(), 'system', NOW(), 'system');

-- 2. 베이스 카테고리
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('베이스', 'PRODUCT', NULL, '/2', 0, 1, NOW(), 'system', NOW(), 'system');

SET @bass_id = LAST_INSERT_ID();

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('일렉트릭 베이스', 'PRODUCT', @bass_id, CONCAT('/2/', @bass_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('어쿠스틱 베이스', 'PRODUCT', @bass_id, CONCAT('/2/', @bass_id), 1, 1, NOW(), 'system', NOW(), 'system');

-- 3. 드럼 카테고리
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('드럼', 'PRODUCT', NULL, '/3', 0, 1, NOW(), 'system', NOW(), 'system');

SET @drum_id = LAST_INSERT_ID();

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('드럼 세트', 'PRODUCT', @drum_id, CONCAT('/3/', @drum_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('심벌즈', 'PRODUCT', @drum_id, CONCAT('/3/', @drum_id), 1, 1, NOW(), 'system', NOW(), 'system');

-- 4. 키보드/피아노 카테고리
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('키보드/피아노', 'PRODUCT', NULL, '/4', 0, 1, NOW(), 'system', NOW(), 'system');

SET @keyboard_id = LAST_INSERT_ID();

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('디지털 피아노', 'PRODUCT', @keyboard_id, CONCAT('/4/', @keyboard_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('신디사이저', 'PRODUCT', @keyboard_id, CONCAT('/4/', @keyboard_id), 1, 1, NOW(), 'system', NOW(), 'system');

-- 5. 악기 액세서리 카테고리
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('악기 액세서리', 'PRODUCT', NULL, '/5', 0, 1, NOW(), 'system', NOW(), 'system');

SET @accessory_id = LAST_INSERT_ID();

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('앰프', 'PRODUCT', @accessory_id, CONCAT('/5/', @accessory_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('케이블', 'PRODUCT', @accessory_id, CONCAT('/5/', @accessory_id), 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, created_by, updated_at, updated_by) 
VALUES ('스트링', 'PRODUCT', @accessory_id, CONCAT('/5/', @accessory_id), 1, 1, NOW(), 'system', NOW(), 'system');

-- ========================================
-- 사용자 데이터 (테스트용)
-- ========================================

INSERT INTO users (email, name, nickname, role, phone_verified, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, created_by, updated_at, updated_by) 
VALUES ('admin@musicsale.com', '관리자', 'admin', 'ADMIN', 1, 0, 0, 0.0, 0, 1, 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO users (email, name, nickname, role, phone_verified, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, created_by, updated_at, updated_by) 
VALUES ('seller1@musicsale.com', '판매자1', 'seller1', 'SELLER', 1, 0, 5, 4.5, 3, 1, 1, 1, NOW(), 'system', NOW(), 'system');

INSERT INTO users (email, name, nickname, role, phone_verified, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, created_by, updated_at, updated_by) 
VALUES ('user1@musicsale.com', '구매자1', 'user1', 'USER', 1, 3, 0, 0.0, 0, 1, 1, 1, NOW(), 'system', NOW(), 'system');

-- ========================================
-- 상점 데이터
-- ========================================

INSERT INTO store (name, description, zipcode, base_address, detail_address, contact_number, business_number, status, seller_id, created_at, created_by, updated_at, updated_by) 
VALUES ('음악의 정원', '다양한 악기를 취급하는 전문 상점입니다.', '12345', '서울특별시 강남구', '테헤란로 123', '02-1234-5678', '123-45-67890', 'ACTIVE', 2, NOW(), 'system', NOW(), 'system');

-- ========================================
-- 제품 카탈로그 데이터
-- ========================================

-- 기타 카탈로그
INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Fender Stratocaster', id, 'Fender', '{"color": "Sunburst", "pickups": "3 Single-coil", "neck": "Maple"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '일렉트릭 기타';

INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Gibson Les Paul', id, 'Gibson', '{"color": "Cherry Sunburst", "pickups": "2 Humbucker", "neck": "Mahogany"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '일렉트릭 기타';

INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Martin D-28', id, 'Martin', '{"color": "Natural", "body": "Dreadnought", "wood": "Spruce/Mahogany"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '어쿠스틱 기타';

-- 베이스 카탈로그
INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Fender Precision Bass', id, 'Fender', '{"color": "Black", "pickups": "1 Split-coil", "neck": "Maple"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '일렉트릭 베이스';

-- 드럼 카탈로그
INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Pearl Export', id, 'Pearl', '{"configuration": "5-piece", "finish": "Black", "shell": "Poplar"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '드럼 세트';

-- 키보드 카탈로그
INSERT INTO product_catalog (name, category_id, brand, attributes, created_at, created_by, updated_at, updated_by) 
SELECT 'Yamaha P-125', id, 'Yamaha', '{"keys": "88", "weight": "GHS", "polyphony": "192"}', NOW(), 'system', NOW(), 'system' 
FROM category WHERE name = '디지털 피아노';

-- ========================================
-- 제품 아이템 데이터 (실제 판매 상품)
-- ========================================

-- 기타 상품
INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 1200000, 'USED', 'A', 1, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Fender Stratocaster' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 2500000, 'NEW', 'S', 2, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Gibson Les Paul' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 1800000, 'USED', 'B', 1, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Martin D-28' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

-- 베이스 상품
INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 800000, 'USED', 'A', 1, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Fender Precision Bass' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

-- 드럼 상품
INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 1200000, 'USED', 'B', 1, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Pearl Export' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

-- 키보드 상품
INSERT INTO product_item (catalog_id, seller_id, store_id, price, `condition`, condition_grade, stock_quantity, status, created_at, created_by, updated_at, updated_by) 
SELECT pc.id, u.id, s.id, 800000, 'NEW', 'S', 2, 'SELLING', NOW(), 'system', NOW(), 'system' 
FROM product_catalog pc, store s, users u WHERE pc.name = 'Yamaha P-125' AND s.name = '음악의 정원' AND u.email = 'seller1@musicsale.com';

-- ========================================
-- 배송 정책 데이터
-- ========================================

INSERT INTO shipping_policies (name, shipping_fee, free_shipping_threshold, min_delivery_days, max_delivery_days, seller_id, is_default, can_pickup, international_shipping, created_at, created_by, updated_at, updated_by) 
VALUES ('기본 배송', 5000, 100000, 1, 3, 2, 1, 1, 0, NOW(), 'system', NOW(), 'system');

-- ========================================
-- 인증 사용자 데이터
-- ========================================

INSERT INTO auth_users (user_id, password_hash, failed_login_attempts, password_changed_at, must_change_password, two_factor_enabled, created_at, created_by, updated_at, updated_by) 
SELECT id, '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 0, NOW(), 0, 0, NOW(), 'system', NOW(), 'system' 
FROM users WHERE email = 'admin@musicsale.com';

INSERT INTO auth_users (user_id, password_hash, failed_login_attempts, password_changed_at, must_change_password, two_factor_enabled, created_at, created_by, updated_at, updated_by) 
SELECT id, '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 0, NOW(), 0, 0, NOW(), 'system', NOW(), 'system' 
FROM users WHERE email = 'seller1@musicsale.com';

INSERT INTO auth_users (user_id, password_hash, failed_login_attempts, password_changed_at, must_change_password, two_factor_enabled, created_at, created_by, updated_at, updated_by) 
SELECT id, '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 0, NOW(), 0, 0, NOW(), 'system', NOW(), 'system' 
FROM users WHERE email = 'user1@musicsale.com';

-- 초기화 완료 메시지
SELECT '악기 판매 플랫폼 초기 데이터가 성공적으로 로드되었습니다!' as message; 
