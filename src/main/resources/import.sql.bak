-- 기본 사용자 데이터 (확장된 User 정보)
INSERT INTO users (email, name, nickname, role, phone_number, phone_verified, user_type, zipcode, base_address, detail_address, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, updated_at, created_by, updated_by) 
VALUES ('seller@test.com', '판매자1', 'seller1', 'SELLER', '010-1234-5678', true, 'BOTH', '06292', '서울특별시 강남구 테헤란로', '123번길 45', 0, 15, 4.8, 25, true, true, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

INSERT INTO users (email, name, nickname, role, phone_number, phone_verified, user_type, zipcode, base_address, detail_address, total_purchases, total_sales, rating, review_count, is_active, is_verified, marketing_agreed, created_at, updated_at, created_by, updated_by) 
VALUES ('user@test.com', '구매자1', 'user1', 'USER', '010-9876-5432', true, 'BUYER', '03181', '서울특별시 종로구 종로', '1번지', 8, 0, 4.5, 12, true, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 루트 카테고리 데이터 (depth=0)
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('기타', 'PRODUCT', NULL, '/1', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('베이스', 'PRODUCT', NULL, '/2', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('드럼', 'PRODUCT', NULL, '/3', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('건반악기', 'PRODUCT', NULL, '/4', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('관악기', 'PRODUCT', NULL, '/5', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('악세서리', 'PRODUCT', NULL, '/6', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('앰프/스피커', 'PRODUCT', NULL, '/7', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('녹음장비', 'PRODUCT', NULL, '/8', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('악보/교재', 'PRODUCT', NULL, '/9', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO category (name, type, parent_id, path, depth, is_active, created_at, updated_at, created_by, updated_by) VALUES ('부품/소모품', 'PRODUCT', NULL, '/10', 0, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

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

-- 찜 목록 테이블 생성
CREATE TABLE IF NOT EXISTS wishlist (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT uk_wishlist_user_product UNIQUE (user_id, product_id),
    CONSTRAINT fk_wishlist_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_wishlist_product FOREIGN KEY (product_id) REFERENCES product_item(id)
);

-- 장바구니 테이블 생성
CREATE TABLE IF NOT EXISTS cart (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT uk_cart_user_product UNIQUE (user_id, product_id),
    CONSTRAINT fk_cart_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_cart_product FOREIGN KEY (product_id) REFERENCES product_item(id),
    CONSTRAINT chk_cart_quantity CHECK (quantity > 0)
);

-- 테스트용 찜 목록 데이터
INSERT INTO wishlist (user_id, product_id, created_at, updated_at, created_by, updated_by) VALUES (2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO wishlist (user_id, product_id, created_at, updated_at, created_by, updated_by) VALUES (2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 테스트용 장바구니 데이터
INSERT INTO cart (user_id, product_id, quantity, created_at, updated_at, created_by, updated_by) VALUES (2, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO cart (user_id, product_id, quantity, created_at, updated_at, created_by, updated_by) VALUES (2, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 상품 조회수 테이블 생성
CREATE TABLE IF NOT EXISTS product_view_count (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT NOT NULL,
    current_viewers INT NOT NULL DEFAULT 0,
    total_views INT NOT NULL DEFAULT 0,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT uk_product_view_count_product UNIQUE (product_id),
    CONSTRAINT fk_product_view_count_product FOREIGN KEY (product_id) REFERENCES product_item(id),
    CONSTRAINT chk_current_viewers CHECK (current_viewers >= 0),
    CONSTRAINT chk_total_views CHECK (total_views >= 0)
);

-- 테스트용 조회수 데이터
INSERT INTO product_view_count (product_id, current_viewers, total_views, last_updated, created_at, updated_at, created_by, updated_by) VALUES (1, 0, 25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_view_count (product_id, current_viewers, total_views, last_updated, created_at, updated_at, created_by, updated_by) VALUES (2, 1, 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_view_count (product_id, current_viewers, total_views, last_updated, created_at, updated_at, created_by, updated_by) VALUES (3, 0, 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');
INSERT INTO product_view_count (product_id, current_viewers, total_views, last_updated, created_at, updated_at, created_by, updated_by) VALUES (4, 2, 42, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 사용자 인증 정보 테이블 생성
CREATE TABLE IF NOT EXISTS auth_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    password_salt VARCHAR(255),
    failed_login_attempts INT NOT NULL DEFAULT 0,
    locked_until TIMESTAMP NULL,
    password_changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    must_change_password BOOLEAN NOT NULL DEFAULT false,
    two_factor_enabled BOOLEAN NOT NULL DEFAULT false,
    two_factor_secret VARCHAR(255),
    backup_codes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT uk_auth_users_user_id UNIQUE (user_id),
    CONSTRAINT fk_auth_users_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 소셜 로그인 연동 테이블 생성
CREATE TABLE IF NOT EXISTS user_social_connections (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    provider VARCHAR(20) NOT NULL,
    provider_id VARCHAR(255) NOT NULL,
    access_token TEXT,
    refresh_token TEXT,
    token_expires_at TIMESTAMP,
    connected_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_used_at TIMESTAMP,
    provider_email VARCHAR(255),
    provider_name VARCHAR(255),
    provider_profile_image VARCHAR(500),
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT uk_user_social_connections_user_provider UNIQUE (user_id, provider),
    CONSTRAINT uk_user_social_connections_provider_id UNIQUE (provider, provider_id),
    CONSTRAINT fk_user_social_connections_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 휴대폰 인증 테이블 생성
CREATE TABLE IF NOT EXISTS phone_verifications (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20) NOT NULL,
    verification_code VARCHAR(10) NOT NULL,
    verification_type VARCHAR(20) NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    verified_at TIMESTAMP,
    attempt_count INT NOT NULL DEFAULT 0,
    is_used BOOLEAN NOT NULL DEFAULT false,
    user_id BIGINT,
    ip_address VARCHAR(45),
    user_agent TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    created_by VARCHAR(100) DEFAULT 'system',
    updated_by VARCHAR(100) DEFAULT 'system',
    CONSTRAINT fk_phone_verifications_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 사용자 관심 카테고리 매핑 테이블 생성
CREATE TABLE IF NOT EXISTS user_interested_categories (
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, category_id),
    CONSTRAINT fk_user_interested_categories_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_interested_categories_category FOREIGN KEY (category_id) REFERENCES category(id)
);

-- 테스트용 인증 데이터 (비밀번호: "password123")
INSERT INTO auth_users (user_id, password_hash, password_changed_at, created_at, updated_at, created_by, updated_by) 
VALUES (1, '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfFGjO6XSUN5RGLDx8Pz4n8C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

INSERT INTO auth_users (user_id, password_hash, password_changed_at, created_at, updated_at, created_by, updated_by) 
VALUES (2, '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iYqiSfFGjO6XSUN5RGLDx8Pz4n8C', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'system', 'system');

-- 테스트용 사용자 관심 카테고리 데이터 (존재하는 사용자 ID만 사용)
INSERT INTO user_interested_categories (user_id, category_id) VALUES (1, 6);
INSERT INTO user_interested_categories (user_id, category_id) VALUES (1, 9);
INSERT INTO user_interested_categories (user_id, category_id) VALUES (2, 7);
INSERT INTO user_interested_categories (user_id, category_id) VALUES (2, 13);
