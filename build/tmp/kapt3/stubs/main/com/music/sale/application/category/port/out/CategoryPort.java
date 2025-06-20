package com.music.sale.application.category.port.out;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0004H&\u00a8\u0006\f"}, d2 = {"Lcom/music/sale/application/category/port/out/CategoryPort;", "", "findAll", "", "Lcom/music/sale/domain/category/Category;", "findById", "id", "", "getCategoryById", "getReferenceById", "save", "category", "music"})
public abstract interface CategoryPort {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.category.Category getCategoryById(long id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract java.util.List<com.music.sale.domain.category.Category> findAll();
    
    @org.jetbrains.annotations.Nullable()
    public abstract com.music.sale.domain.category.Category findById(long id);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.category.Category save(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.Category category);
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.music.sale.domain.category.Category getReferenceById(long id);
}