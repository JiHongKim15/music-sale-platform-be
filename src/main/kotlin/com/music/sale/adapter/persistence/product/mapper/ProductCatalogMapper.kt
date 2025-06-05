package com.music.sale.adapter.persistence.product.mapper

import com.fasterxml.jackson.databind.ObjectMapper
import com.music.sale.adapter.persistence.product.entity.ProductCatalogEntity
import com.music.sale.domain.product.ProductCatalog
import org.springframework.stereotype.Component

/**
 * 제품 카탈로그 도메인 모델과 엔티티 간의 변환을 담당하는 매퍼 클래스
 */
@Component
class ProductCatalogMapper(private val objectMapper: ObjectMapper) {

    // 엔티티를 도메인 모델로 변환
    fun toDomain(entity: ProductCatalogEntity): ProductCatalog {
        // 속성 JSON을 맵으로 변환
        val attributesMap = try {
            objectMapper.readValue(entity.attributes, Map::class.java) as Map<String, Any>
        } catch (e: Exception) {
            emptyMap<String, Any>()
        }

        // 도메인 객체 재구성 (도메인 모델에 맞게 구현 필요)
        return ProductCatalog.reconstitute(
            id = entity.id ?: throw IllegalStateException("엔티티 ID가 null입니다"),
            name = entity.name,
            productCategory = entity.productType,
            attributes = attributesMap
        )
    }

    // 도메인 모델을 엔티티로 변환
    fun toEntity(domain: ProductCatalog): ProductCatalogEntity {
        // 속성 맵을 JSON 문자열로 변환
        val attributesJson = objectMapper.writeValueAsString(domain.getAttributes())

        return ProductCatalogEntity(
            id = domain.getId(),
            name = domain.getName(),
            productType = domain.getProductType(),
            attributes = attributesJson
        )
    }
} 