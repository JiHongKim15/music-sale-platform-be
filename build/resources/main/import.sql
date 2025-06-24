-- 기본 사용자 데이터
INSERT INTO users (email, name, role, created_at, updated_at, created_by, updated_by) VALUES ('seller@test.com', '판매자1', 'SELLER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO users (email, name, role, created_at, updated_at, created_by, updated_by) VALUES ('user@test.com', '구매자1', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 루트 카테고리 데이터 (depth=0)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('기타', 'PRODUCT', NULL, '/1', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('베이스', 'PRODUCT', NULL, '/2', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('드럼', 'PRODUCT', NULL, '/3', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('건반악기', 'PRODUCT', NULL, '/4', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('관악기', 'PRODUCT', NULL, '/5', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기타 하위 카테고리 (depth=1, parent_id=1)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('일렉기타', 'PRODUCT', 1, '/1/6', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('어쿠스틱기타', 'PRODUCT', 1, '/1/7', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('클래식기타', 'PRODUCT', 1, '/1/8', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 베이스 하위 카테고리 (depth=1, parent_id=2)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('일렉베이스', 'PRODUCT', 2, '/2/9', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('어쿠스틱베이스', 'PRODUCT', 2, '/2/10', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 드럼 하위 카테고리 (depth=1, parent_id=3)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('어쿠스틱드럼', 'PRODUCT', 3, '/3/11', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('전자드럼', 'PRODUCT', 3, '/3/12', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 건반악기 하위 카테고리 (depth=1, parent_id=4)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('피아노', 'PRODUCT', 4, '/4/13', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('신디사이저', 'PRODUCT', 4, '/4/14', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 관악기 하위 카테고리 (depth=1, parent_id=5)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('색소폰', 'PRODUCT', 5, '/5/15', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('트럼펫', 'PRODUCT', 5, '/5/16', 1, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 기본 스토어 데이터
INSERT INTO store (name, description, zipcode, base_address, detail_address, contact_number, business_number, status, seller_id, created_at, updated_at, created_by, updated_by) VALUES ('뮤직 스토어', '악기 전문점', '12345', '서울시 강남구', '테헤란로 123', '02-1234-5678', '123-45-67890', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 카탈로그 데이터 (기존 + 새로운 카테고리)
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('펜더 스트라토캐스터', 6, '{"brand": "Fender", "type": "일렉기타"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('펜더 재즈 베이스', 9, '{"brand": "Fender", "type": "일렉베이스"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('테일러 어쿠스틱기타', 7, '{"brand": "Taylor", "type": "어쿠스틱기타"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('펄 드럼세트', 11, '{"brand": "Pearl", "type": "어쿠스틱드럼"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_catalog (name, category_id, attributes, created_at, updated_at, created_by, updated_by) VALUES ('롤랜드 신디사이저', 14, '{"brand": "Roland", "type": "신디사이저"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 아이템 데이터 (실제 판매 상품)
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (1, 1, 1, 1200000, 'USED', 'S', 1, 'SELLING', '펜더 스트라토캐스터 미국산', '{"color": "Sunburst", "year": "2020"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (2, 1, 1, 900000, 'USED', 'A', 1, 'SELLING', '펜더 재즈 베이스 4현', '{"color": "White", "year": "2019"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (1, 1, 1, 800000, 'USED', 'B', 1, 'SELLING', '펜더 스트라토캐스터 멕시코산', '{"color": "Black", "year": "2018"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (3, 1, 1, 1500000, 'NEW', 'S', 2, 'SELLING', '테일러 814ce 어쿠스틱', '{"color": "Natural", "year": "2023"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (4, 1, 1, 2500000, 'NEW', 'S', 1, 'SELLING', '펄 익스포트 드럼세트', '{"color": "Jet Black", "pieces": "5pc"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_item (catalog_id, seller_id, store_id, price, condition, condition_grade, stock_quantity, status, custom_name, custom_attributes, created_at, updated_at, created_by, updated_by) VALUES (5, 1, 1, 1800000, 'USED', 'A', 1, 'SELLING', '롤랜드 JUNO-DS88', '{"keys": "88", "year": "2021"}', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
