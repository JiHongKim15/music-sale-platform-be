-- ============================================================
-- 좋아요(Likes) 테이블 생성 스크립트
-- ============================================================

CREATE TABLE IF NOT EXISTS `likes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '좋아요 고유 ID',
  `user_id` BIGINT NOT NULL COMMENT '사용자 ID',
  `likeable_id` BIGINT NOT NULL COMMENT '대상 ID',
  `likeable_type` VARCHAR(20) NOT NULL COMMENT '대상 종류 (PRODUCT, STORE, SELLER)',
  `created_at` DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) COMMENT '생성 시각',
  
  PRIMARY KEY (`id`),
  
  -- Unique 인덱스: 중복 좋아요 방지
  UNIQUE INDEX `uix_user_likeable` (`user_id`, `likeable_type`, `likeable_id`),
  
  -- 복합 인덱스: 내 찜 목록 조회 최적화
  INDEX `idx_user_type_created` (`user_id`, `likeable_type`, `created_at` DESC),
  
  -- 복합 인덱스: 좋아요 개수 집계 최적화
  INDEX `idx_likeable` (`likeable_type`, `likeable_id`)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COMMENT='통합 좋아요 테이블 (다형적 관계)';

