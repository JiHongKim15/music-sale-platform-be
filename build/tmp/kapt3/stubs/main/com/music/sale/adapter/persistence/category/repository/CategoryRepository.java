package com.music.sale.adapter.persistence.category.repository;

@org.springframework.stereotype.Repository()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\bg\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\b\b\u0001\u0010\b\u001a\u00020\tH\'J\u001a\u0010\f\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u0003H&J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0007H&J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH&J\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u000b2\b\b\u0001\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0014"}, d2 = {"Lcom/music/sale/adapter/persistence/category/repository/CategoryRepository;", "Lorg/springframework/data/jpa/repository/JpaRepository;", "Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;", "", "existsByNameAndType", "", "name", "", "type", "Lcom/music/sale/domain/category/CategoryType;", "findActiveRootCategoriesByType", "", "findByNameAndType", "findByParentId", "parentId", "findByPathStartingWith", "path", "findByType", "findByTypeAndIsActiveTrue", "findRootCategoriesByType", "music"})
public abstract interface CategoryRepository extends org.springframework.data.jpa.repository.JpaRepository<com.music.sale.adapter.persistence.category.entity.CategoryEntity, java.lang.Long> {
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findByType(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findByTypeAndIsActiveTrue(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findByParentId(long parentId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findByPathStartingWith(@org.jetbrains.annotations.NotNull()
    java.lang.String path);
    
    @org.springframework.data.jpa.repository.Query(value = "SELECT c FROM CategoryEntity c WHERE c.type = :type AND c.depth = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findRootCategoriesByType(@org.springframework.data.repository.query.Param(value = "type")
    @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    @org.springframework.data.jpa.repository.Query(value = "SELECT c FROM CategoryEntity c WHERE c.type = :type AND c.isActive = true AND c.depth = 0")
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.adapter.persistence.category.entity.CategoryEntity> findActiveRootCategoriesByType(@org.springframework.data.repository.query.Param(value = "type")
    @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    public abstract boolean existsByNameAndType(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.adapter.persistence.category.entity.CategoryEntity findByNameAndType(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
}