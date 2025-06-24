-- 기본 사용자 데이터
INSERT INTO users (email, name, role, created_at, updated_at, created_by, updated_by) VALUES ('seller@test.com', '판매자1', 'SELLER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO users (email, name, role, created_at, updated_at, created_by, updated_by) VALUES ('user@test.com', '구매자1', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기본 카테고리 데이터
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('기타', 'PRODUCT', NULL, '/1', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('베이스', 'PRODUCT', NULL, '/2', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기본 스토어 데이터
INSERT INTO store (name, description, zipcode, base_address, detail_address, contact_number, business_number, status, seller_id, created_at, updated_at, created_by, updated_by) VALUES ('뮤직 스토어', '악기 전문점', '12345', '서울시 강남구', '테헤란로 123', '02-1234-5678', '123-45-67890', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 카탈로그 데이터
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('펜더 스트라토캐스터', 1, '{"brand": "Fender"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('펜더 재즈 베이스', 2, '{"brand": "Fender"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 아이템 데이터 (실제 판매 상품)
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (1, 1, 1, 1200000, 'USED', 'S', 1, 'SELLING', '펜더 스트라토캐스터 미국산', '{"color": "Sunburst", "year": "2020"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (2, 1, 1, 900000, 'USED', 'A', 1, 'SELLING', '펜더 재즈 베이스 4현', '{"color": "White", "year": "2019"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (1, 1, 1, 800000, 'USED', 'B', 1, 'SELLING', '펜더 스트라토캐스터 멕시코산', '{"color": "Black", "year": "2018"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
