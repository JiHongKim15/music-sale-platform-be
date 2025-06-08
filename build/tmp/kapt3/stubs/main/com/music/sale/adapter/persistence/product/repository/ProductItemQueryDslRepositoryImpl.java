package com.music.sale.adapter.persistence.product.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J!\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010\u0010J!\u0010\u0011\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u000f\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010\u0014J3\u0010\u0015\u001a\u0004\u0018\u00010\u00062\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\u0004\u0018\u00010\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001e\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010\u001fJ+\u0010 \u001a\u0004\u0018\u00010\u00062\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000f\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010$J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J!\u0010,\u001a\u0004\u0018\u00010\u00062\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\u000f\u001a\u00020\nH\u0012\u00a2\u0006\u0002\u0010/R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/music/sale/adapter/persistence/product/repository/ProductItemQueryDslRepositoryImpl;", "Lcom/music/sale/adapter/persistence/product/repository/ProductItemQueryDslRepository;", "queryFactory", "Lcom/querydsl/jpa/impl/JPAQueryFactory;", "(Lcom/querydsl/jpa/impl/JPAQueryFactory;)V", "categoryIdCondition", "Lcom/querydsl/core/types/dsl/BooleanExpression;", "categoryId", "", "category", "error/NonExistentClass", "(Ljava/lang/Long;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "conditionCondition", "condition", "Lcom/music/sale/domain/product/enum/ProductCondition;", "productItem", "(Lcom/music/sale/domain/product/enum/ProductCondition;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "conditionGradeCondition", "conditionGrade", "Lcom/music/sale/domain/product/enum/ProductConditionGrade;", "(Lcom/music/sale/domain/product/enum/ProductConditionGrade;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "keywordCondition", "keyword", "", "keywordType", "Lcom/music/sale/adapter/web/product/request/SearchProductKeywordType;", "catalog", "(Ljava/lang/String;Lcom/music/sale/adapter/web/product/request/SearchProductKeywordType;Lerror/NonExistentClass;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "locationCondition", "location", "store", "(Ljava/lang/String;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "priceRangeCondition", "minPrice", "", "maxPrice", "(Ljava/lang/Integer;Ljava/lang/Integer;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "searchProducts", "Lorg/springframework/data/domain/Page;", "Lcom/music/sale/adapter/persistence/product/entity/ProductItemEntity;", "searchCriteria", "Lcom/music/sale/adapter/persistence/product/dto/SearchProductCondition;", "pageable", "Lorg/springframework/data/domain/Pageable;", "statusCondition", "status", "Lcom/music/sale/domain/product/enum/ProductStatus;", "(Lcom/music/sale/domain/product/enum/ProductStatus;Lerror/NonExistentClass;)Lcom/querydsl/core/types/dsl/BooleanExpression;", "music"})
public class ProductItemQueryDslRepositoryImpl implements com.music.sale.adapter.persistence.product.repository.ProductItemQueryDslRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.querydsl.jpa.impl.JPAQueryFactory queryFactory = null;
    
    public ProductItemQueryDslRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.querydsl.jpa.impl.JPAQueryFactory queryFactory) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.data.domain.Page<com.music.sale.adapter.persistence.product.entity.ProductItemEntity> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.product.dto.SearchProductCondition searchCriteria, @org.jetbrains.annotations.NotNull()
    org.springframework.data.domain.Pageable pageable) {
        return null;
    }
    
    private com.querydsl.core.types.dsl.BooleanExpression keywordCondition(java.lang.String keyword, com.music.sale.adapter.web.product.request.SearchProductKeywordType keywordType, error.NonExistentClass productItem, error.NonExistentClass catalog) {
        return null;
    }
    
    private com.querydsl.core.types.dsl.BooleanExpression categoryIdCondition(java.lang.Long categoryId, error.NonExistentClass category) {
        return null;
    }
    
    private com.querydsl.core.types.dsl.BooleanExpression locationCondition(java.lang.String location, error.NonExistentClass store) {
        return null;
    }
    
    private com.querydsl.core.types.dsl.BooleanExpression priceRangeCondition(java.lang.Integer minPrice, java.lang.Integer maxPrice, error.NonExistentClass productItem) {
        return null;
    }
}