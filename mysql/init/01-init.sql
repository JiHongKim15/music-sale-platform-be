USE music_sale_db;

-- music_sale_db 스키마

-- 기존 테이블 삭제 (초기화 시)
DROP TABLE IF EXISTS `user_interested_categories`;
DROP TABLE IF EXISTS `user_social_connections`;
DROP TABLE IF EXISTS `auth_users`;
DROP TABLE IF EXISTS `phone_verifications`;
DROP TABLE IF EXISTS `wishlist`;
DROP TABLE IF EXISTS `product_view_count`;
DROP TABLE IF EXISTS `product_image`;
DROP TABLE IF EXISTS `product_item`;
DROP TABLE IF EXISTS `product_catalog`;
DROP TABLE IF EXISTS `category`;
DROP TABLE IF EXISTS `order_items`;
DROP TABLE IF EXISTS `orders`;
DROP TABLE IF EXISTS `shipping_policies`;
DROP TABLE IF EXISTS `store`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `carts`;

-- users 테이블
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `provider` varchar(255) DEFAULT NULL,
  `provider_id` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `phone_verified` bit(1) NOT NULL,
  `phone_verified_at` datetime(6) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  `base_address` varchar(255) DEFAULT NULL,
  `detail_address` varchar(255) DEFAULT NULL,
  `profile_image_url` varchar(255) DEFAULT NULL,
  `bio` varchar(500) DEFAULT NULL,
  `user_type` varchar(255) DEFAULT NULL,
  `preferred_categories` text,
  `preferred_price_range_min` int DEFAULT NULL,
  `preferred_price_range_max` int DEFAULT NULL,
  `total_purchases` int NOT NULL,
  `total_sales` int NOT NULL,
  `rating` double NOT NULL,
  `review_count` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `is_verified` bit(1) NOT NULL,
  `last_login_at` datetime(6) DEFAULT NULL,
  `marketing_agreed` bit(1) NOT NULL,
  `marketing_agreed_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UK_f2ksd6h8hsjtd5bypolw9juaq` (`nickname`),
  UNIQUE KEY `UK_23n2j63hergt3khj28t6h36v1` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- store 테이블
CREATE TABLE `store` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `base_address` varchar(255) NOT NULL,
  `detail_address` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `contact_number` varchar(255) NOT NULL,
  `business_number` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `seller_id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- shipping_policies 테이블
CREATE TABLE `shipping_policies` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `shipping_fee` decimal(10,2) NOT NULL,
  `free_shipping_threshold` decimal(10,2) DEFAULT NULL,
  `min_delivery_days` int NOT NULL,
  `max_delivery_days` int NOT NULL,
  `seller_id` bigint NOT NULL,
  `is_default` bit(1) NOT NULL,
  `can_pickup` bit(1) NOT NULL,
  `international_shipping` bit(1) NOT NULL,
  `restricted_areas` varchar(1000) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- orders 테이블
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) NOT NULL,
  `user_id` bigint NOT NULL,
  `total_amount` int NOT NULL,
  `status` varchar(255) NOT NULL,
  `order_date` datetime(6) NOT NULL,
  `recipient_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `base_address` varchar(255) NOT NULL,
  `detail_address` varchar(255) NOT NULL,
  `delivery_message` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `payment_amount` int DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_f56i6p3j42a38582vof9c337a` (`order_number`),
  KEY `FK32ql8ubntj5uh44ph9659tiih` (`user_id`),
  CONSTRAINT `FK32ql8ubntj5uh44ph9659tiih` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- order_items 테이블
CREATE TABLE `order_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `seller_id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbioxgbv59vetrxe0ejfubep1w` (`order_id`),
  CONSTRAINT `FKbioxgbv59vetrxe0ejfubep1w` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- category 테이블
CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `parent_id` bigint DEFAULT NULL,
  `path` varchar(255) NOT NULL,
  `depth` int NOT NULL,
  `is_active` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2jws5i6p04l0n0xjl90hfvaca` (`parent_id`),
  CONSTRAINT `FK2jws5i6p04l0n0xjl90hfvaca` FOREIGN KEY (`parent_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- product_catalog 테이블
CREATE TABLE `product_catalog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category_id` bigint NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `attributes` json NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq5n6xrs2wns5c9w08awm2r2a2` (`category_id`),
  CONSTRAINT `FKq5n6xrs2wns5c9w08awm2r2a2` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- product_item 테이블
CREATE TABLE `product_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `catalog_id` bigint NOT NULL,
  `seller_id` bigint DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  `price` int NOT NULL,
  `condition` varchar(255) NOT NULL,
  `condition_grade` varchar(255) DEFAULT NULL,
  `stock_quantity` int NOT NULL,
  `status` varchar(255) NOT NULL,
  `custom_name` varchar(255) DEFAULT NULL,
  `custom_attributes` text,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi6s8d9k8x9y1x2w3z4v5u6t7` (`catalog_id`),
  KEY `FKg5kdj0brdmd2b0g67oudgifj2` (`store_id`),
  KEY `FK7lcsnss1f96tf908hemtcbi2r` (`seller_id`),
  CONSTRAINT `FKi6s8d9k8x9y1x2w3z4v5u6t7` FOREIGN KEY (`catalog_id`) REFERENCES `product_catalog` (`id`),
  CONSTRAINT `FKg5kdj0brdmd2b0g67oudgifj2` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`),
  CONSTRAINT `FK7lcsnss1f96tf908hemtcbi2r` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- product_image 테이블
CREATE TABLE `product_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_item_id` bigint NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `is_thumbnail` bit(1) NOT NULL,
  `image_order` int NOT NULL,
  `alt_text` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd7w4t7v0g5f9y2x1w3z4v5u6` (`product_item_id`),
  CONSTRAINT `FKd7w4t7v0g5f9y2x1w3z4v5u6` FOREIGN KEY (`product_item_id`) REFERENCES `product_item` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- product_view_count 테이블
CREATE TABLE `product_view_count` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `current_viewers` int NOT NULL,
  `total_views` int NOT NULL,
  `last_updated` datetime(6) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- wishlist 테이블
CREATE TABLE `wishlist` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- phone_verifications 테이블
CREATE TABLE `phone_verifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(255) NOT NULL,
  `verification_type` varchar(255) NOT NULL,
  `verification_code` varchar(255) NOT NULL,
  `expires_at` datetime(6) NOT NULL,
  `attempt_count` int NOT NULL,
  `is_used` bit(1) NOT NULL,
  `used_at` datetime(6) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1y2o3p4q5r6s7t8u9v0w1x2y` (`phone_number`, `verification_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- auth_users 테이블
CREATE TABLE `auth_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `password_salt` varchar(255) DEFAULT NULL,
  `failed_login_attempts` int NOT NULL,
  `locked_until` datetime(6) DEFAULT NULL,
  `password_changed_at` datetime(6) NOT NULL,
  `must_change_password` bit(1) NOT NULL,
  `two_factor_enabled` bit(1) NOT NULL,
  `two_factor_secret` varchar(255) DEFAULT NULL,
  `backup_codes` text,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_k8v4j3y5x6w7e8r9t0u1v2w3` (`user_id`),
  CONSTRAINT `FKg8v4j3y5x6w7e8r9t0u1v2w3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- user_social_connections 테이블
CREATE TABLE `user_social_connections` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `provider` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `provider_email` varchar(255) DEFAULT NULL,
  `provider_name` varchar(255) DEFAULT NULL,
  `provider_profile_image` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `token_expires_at` datetime(6) DEFAULT NULL,
  `connected_at` datetime(6) NOT NULL,
  `last_used_at` datetime(6) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_a1b2c3d4e5f6g7h8i9j0k1l2m` (`user_id`, `provider`),
  CONSTRAINT `FKs1t2u3v4w5x6y7z8a9b0c1d2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- user_interested_categories 테이블
CREATE TABLE `user_interested_categories` (
  `user_id` bigint NOT NULL,
  `category_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`, `category_id`),
  KEY `FK_category` (`category_id`),
  CONSTRAINT `FK_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- carts 테이블
CREATE TABLE `carts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
