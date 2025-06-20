package com.music.sale.application.product.mapper

import com.music.sale.adapter.persistence.product.dto.SaveProductItemCondition
import com.music.sale.adapter.persistence.product.dto.SearchProductCondition
import com.music.sale.application.product.dto.CreateProductInput
import com.music.sale.application.product.dto.ProductOutput
import com.music.sale.application.product.dto.SearchProductInput
import com.music.sale.domain.product.Product
import com.music.sale.domain.store.model.Store
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toOutput(product: Product): ProductOutput {
        return ProductOutput(
            id = product.id,
            catalog = ProductOutput.ProductCatalog(
                id = product.catalogId,
                category = product.category,
            ),
            name = product.name(), // Product 객체의 name() 메소드를 사용하여 최종 이름 가져오기
            price = product.price,
            seller = product.seller,
            store = product.store,
            condition = product.condition,
            conditionGrade = product.conditionGrade,
            stockQuantity = product.stockQuantity,
            status = product.status,
            attributes = product.attributes(), // Product 객체의 attributes() 메소드를 사용하여 최종 속성 가져오기
        )
    }

    fun toSearchProductCondition(input: SearchProductInput): SearchProductCondition {
        return SearchProductCondition(
            keyword = input.keyword,
            keywordType = input.keywordType,
            categoryId = input.categoryId,
            location = input.location,
            condition = input.condition,
            conditionGrade = input.conditionGrade,
            minPrice = input.minPrice,
            maxPrice = input.maxPrice,
            status = input.status
        )
    }

    fun toSaveProductCondition(input: CreateProductInput, seller: User, store: Store): SaveProductItemCondition {
        return SaveProductItemCondition(
            name = input.name,
            catalogId = input.catalogId,
            price = input.price,
            seller = seller,
            store = store,
            condition = input.condition,
            conditionGrade = input.conditionGrade,
            stockQuantity = input.stockQuantity,
            status = input.status,
            attributes = input.attributes
        )
    }

}