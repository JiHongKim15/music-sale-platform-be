-- 기존 데이터 삭제 (중복 방지)
DELETE FROM product_image;
DELETE FROM product_item;
DELETE FROM product_catalog;
DELETE FROM store;
DELETE FROM category;
DELETE FROM users;

-- 기본 사용자 데이터 (확장된 User 정보) - ID 자동 생성
INSERT INTO users (email, name, nickname, role, phone_number, phone_verified, user_type, zipcode, base_address, detail_address, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, updated_at, created_by, updated_by) VALUES ('seller@test.com', '판매자1', 'seller1', 'SELLER', '010-1234-5678', true, 'BOTH', '06292', '서울특별시 강남구 테헤란로', '123번길 45', 0, 15, 4.8, 25, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO users (email, name, nickname, role, phone_number, phone_verified, user_type, zipcode, base_address, detail_address, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, updated_at, created_by, updated_by) VALUES ('user@test.com', '구매자1', 'user1', 'USER', '010-9876-5432', true, 'BUYER', '03181', '서울특별시 종로구 종로', '1번지', 8, 0, 4.5, 12, true, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 루트 카테고리 데이터 (depth=0)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (1, '기타', 'PRODUCT', NULL, '/1', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (2, '베이스', 'PRODUCT', NULL, '/2', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (3, '드럼', 'PRODUCT', NULL, '/3', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (4, '건반악기', 'PRODUCT', NULL, '/4', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (5, '관악기', 'PRODUCT', NULL, '/5', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (6, '악세서리', 'PRODUCT', NULL, '/6', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (7, '앰프/스피커', 'PRODUCT', NULL, '/7', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (8, '녹음장비', 'PRODUCT', NULL, '/8', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (9, '악보/교재', 'PRODUCT', NULL, '/9', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (10, '부품/소모품', 'PRODUCT', NULL, '/10', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기타 하위 카테고리 (depth=1, parent_id=1)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (11, '일렉기타', 'PRODUCT', 1, '/1/6', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (12, '어쿠스틱기타', 'PRODUCT', 1, '/1/7', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (13, '클래식기타', 'PRODUCT', 1, '/1/8', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 베이스 하위 카테고리 (depth=1, parent_id=2)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (14, '일렉베이스', 'PRODUCT', 2, '/2/9', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (15, '어쿠스틱베이스', 'PRODUCT', 2, '/2/10', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 드럼 하위 카테고리 (depth=1, parent_id=3)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (16, '어쿠스틱드럼', 'PRODUCT', 3, '/3/11', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (17, '전자드럼', 'PRODUCT', 3, '/3/12', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 건반악기 하위 카테고리 (depth=1, parent_id=4)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (18, '피아노', 'PRODUCT', 4, '/4/13', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (19, '신디사이저', 'PRODUCT', 4, '/4/14', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 관악기 하위 카테고리 (depth=1, parent_id=5)
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (20, '색소폰', 'PRODUCT', 5, '/5/15', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (id, name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES (21, '트럼펫', 'PRODUCT', 5, '/5/16', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기본 스토어 데이터
INSERT INTO store (id, name, description, zipcode, base_address, detail_address, contact_number, business_number, status, seller_id, created_at, updated_at, created_by, updated_by) VALUES (1, '뮤직 스토어', '악기 전문점', '12345', '서울시 강남구', '테헤란로 123', '02-1234-5678', '123-45-67890', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 카탈로그 데이터 (기존 + 새로운 카테고리)
INSERT INTO product_catalog (id, name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES (1, '펜더 스트라토캐스터', 11, '{"brand": "Fender", "type": "일렉기타"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (id, name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES (2, '펜더 재즈 베이스', 14, '{"brand": "Fender", "type": "일렉베이스"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (id, name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES (3, '테일러 어쿠스틱기타', 12, '{"brand": "Taylor", "type": "어쿠스틱기타"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (id, name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES (4, '펄 드럼세트', 16, '{"brand": "Pearl", "type": "어쿠스틱드럼"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (id, name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES (5, '롤랜드 신디사이저', 19, '{"brand": "Roland", "type": "신디사이저"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 아이템 데이터 (ProductItemEntity)
INSERT INTO product_item (id, product_catalog_id, seller_id, price, condition, status, description, created_at, updated_at, created_by, updated_by) VALUES (1, 1, 1, 1500000, 'EXCELLENT', 'ACTIVE', '펜더 스트라토캐스터 중고 판매', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (id, product_catalog_id, seller_id, price, condition, status, description, created_at, updated_at, created_by, updated_by) VALUES (2, 2, 1, 1200000, 'GOOD', 'ACTIVE', '펜더 재즈 베이스 중고 판매', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (id, product_catalog_id, seller_id, price, condition, status, description, created_at, updated_at, created_by, updated_by) VALUES (3, 3, 1, 800000, 'EXCELLENT', 'ACTIVE', '테일러 어쿠스틱기타 중고 판매', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 이미지 데이터 (ProductImageEntity - product_item_id 참조)
INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, alt_text, created_at, updated_at, created_by, updated_by) VALUES (1, 'http://example.com/fender_strat_main.jpg', true, 0, '펜더 스트라토캐스터 메인 이미지', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, alt_text, created_at, updated_at, created_by, updated_by) VALUES (1, 'http://example.com/fender_strat_detail1.jpg', false, 1, '펜더 스트라토캐스터 상세 이미지 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, alt_text, created_at, updated_at, created_by, updated_by) VALUES (1, 'http://example.com/fender_strat_detail2.jpg', false, 2, '펜더 스트라토캐스터 상세 이미지 2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, alt_text, created_at, updated_at, created_by, updated_by) VALUES (2, 'http://example.com/fender_jazz_main.jpg', true, 0, '펜더 재즈 베이스 메인 이미지', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_image (product_item_id, image_url, is_thumbnail, image_order, alt_text, created_at, updated_at, created_by, updated_by) VALUES (2, 'http://example.com/fender_jazz_detail1.jpg', false, 1, '펜더 재즈 베이스 상세 이미지 1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 아이템 데이터는 애플리케이션에서 생성하도록 제거
-- 테스트용 찜 목록 데이터는 애플리케이션에서 생성하도록 제거  
-- 테스트용 장바구니 데이터는 애플리케이션에서 생성하도록 제거
-- 테스트용 조회수 데이터는 애플리케이션에서 생성하도록 제거
-- 테스트용 인증 데이터는 애플리케이션에서 생성하도록 제거
