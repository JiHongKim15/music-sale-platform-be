-- ============================================
-- Music Sale Platform - DDL Schema
-- audit 컬럼(created_at, created_by, updated_at, updated_by)은 항상 맨 뒤에 위치
-- ============================================

CREATE TABLE IF NOT EXISTS users (
    user_id           BIGINT NOT NULL AUTO_INCREMENT,
    nickname          VARCHAR(50),
    profile_image_url VARCHAR(300),
    email             VARCHAR(100),
    role              ENUM('ADMIN','GUEST','USER') NOT NULL,
    status            ENUM('ACTIVE','BANNED','DORMANT','WITHDRAWAL') NOT NULL,
    ci                VARCHAR(100),
    real_name         VARCHAR(50),
    phone_number      VARCHAR(20),
    birth_date        VARCHAR(8),
    is_verified       BIT(1) NOT NULL,
    created_at        DATETIME(6) NOT NULL,
    created_by        VARCHAR(255) NOT NULL,
    updated_at        DATETIME(6) NOT NULL,
    updated_by        VARCHAR(255) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_user_ci (ci),
    INDEX idx_user_ci (ci),
    INDEX idx_user_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS user_socials (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    user_id     BIGINT NOT NULL,
    provider    VARCHAR(255) NOT NULL,
    provider_id VARCHAR(255) NOT NULL,
    created_at  DATETIME(6) NOT NULL,
    created_by  VARCHAR(255) NOT NULL,
    updated_at  DATETIME(6) NOT NULL,
    updated_by  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_provider_provider_id (provider, provider_id),
    CONSTRAINT fk_user_socials_user FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS user_terms (
    id         BIGINT NOT NULL AUTO_INCREMENT,
    user_id    BIGINT NOT NULL,
    title      VARCHAR(50) NOT NULL,
    version    VARCHAR(20) NOT NULL,
    is_agreed  BIT(1) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(6) NOT NULL,
    updated_by VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_terms_user FOREIGN KEY (user_id) REFERENCES users (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS product_item (
    id                BIGINT NOT NULL AUTO_INCREMENT,
    catalog_id        BIGINT NOT NULL,
    seller_id         BIGINT NOT NULL,
    store_id          BIGINT NOT NULL,
    name              VARCHAR(255) NOT NULL,
    brand             VARCHAR(255),
    price             BIGINT NOT NULL,
    `condition`       ENUM('NEW','USED') NOT NULL,
    condition_grade   ENUM('S','A','B','C','D') NOT NULL,
    stock_quantity    INT NOT NULL,
    status            ENUM('AVAILABLE','SOLD_OUT','RESERVED','DISCONTINUED') NOT NULL,
    custom_attributes TEXT,
    description       TINYTEXT,
    view_count        BIGINT NOT NULL,
    created_at        DATETIME(6) NOT NULL,
    created_by        VARCHAR(255) NOT NULL,
    updated_at        DATETIME(6) NOT NULL,
    updated_by        VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS product_image (
    id              BIGINT NOT NULL AUTO_INCREMENT,
    product_item_id BIGINT NOT NULL,
    url             VARCHAR(500) NOT NULL,
    is_thumbnail    BIT(1) NOT NULL,
    image_order     INT NOT NULL,
    file_size       BIGINT NOT NULL,
    file_name       VARCHAR(255) NOT NULL,
    file_type       VARCHAR(100) NOT NULL,
    created_at      DATETIME(6) NOT NULL,
    created_by      VARCHAR(255) NOT NULL,
    updated_at      DATETIME(6) NOT NULL,
    updated_by      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS likes (
    id            BIGINT NOT NULL AUTO_INCREMENT,
    user_id       BIGINT NOT NULL,
    likeable_id   BIGINT NOT NULL,
    likeable_type VARCHAR(20) NOT NULL,
    created_at    DATETIME(6) NOT NULL,
    created_by    VARCHAR(255) NOT NULL,
    updated_at    DATETIME(6) NOT NULL,
    updated_by    VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uix_user_likeable (user_id, likeable_type, likeable_id),
    INDEX idx_user_type_created (user_id, likeable_type, created_at DESC),
    INDEX idx_likeable (likeable_type, likeable_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
