package com.music.sale.application.category.port.inport;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0007H&J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&\u00a8\u0006\u000e"}, d2 = {"Lcom/music/sale/application/category/port/inport/CategoryUseCase;", "", "getAllCategories", "", "Lcom/music/sale/domain/category/Category;", "getCategoriesByParentId", "parentId", "", "getCategoriesByType", "type", "Lcom/music/sale/domain/category/CategoryType;", "getCategoryById", "id", "getRootCategories", "music"})
public abstract interface CategoryUseCase {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.category.Category getCategoryById(long id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.domain.category.Category> getAllCategories();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.domain.category.Category> getCategoriesByType(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.domain.category.Category> getRootCategories();
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.domain.category.Category> getCategoriesByParentId(long parentId);
}