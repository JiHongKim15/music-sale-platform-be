package com.music.sale.application.image.dto;

/**
 * DB 저장 결과 (내부 확인용)
 * - Adapter → Service 전달용
 * - 생성된 ID와 URL 포함
 */
public record ImageSaveResult(
        Long id,
        String url
) {}
