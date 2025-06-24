package com.music.sale.application.category.service;

@org.springframework.stereotype.Service()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\nH\u0016J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/music/sale/application/category/service/CategoryService;", "Lcom/music/sale/application/category/port/inport/CategoryUseCase;", "categoryPort", "Lcom/music/sale/application/category/port/outport/CategoryPort;", "(Lcom/music/sale/application/category/port/outport/CategoryPort;)V", "getAllCategories", "", "Lcom/music/sale/domain/category/Category;", "getCategoriesByParentId", "parentId", "", "getCategoriesByType", "type", "Lcom/music/sale/domain/category/CategoryType;", "getCategoryById", "id", "getRootCategories", "music"})
public class CategoryService implements com.music.sale.application.category.port.inport.CategoryUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.category.port.outport.CategoryPort categoryPort = null;
    
    public CategoryService(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.category.port.outport.CategoryPort categoryPort) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.category.Category getCategoryById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.music.sale.domain.category.Category> getAllCategories() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.music.sale.domain.category.Category> getCategoriesByType(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.music.sale.domain.category.Category> getRootCategories() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.music.sale.domain.category.Category> getCategoriesByParentId(long parentId) {
        return null;
    }
}