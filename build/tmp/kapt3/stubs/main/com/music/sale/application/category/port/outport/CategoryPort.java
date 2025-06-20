package com.music.sale.application.category.port.outport;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0012\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH&\u00a8\u0006\u000e"}, d2 = {"Lcom/music/sale/application/category/port/outport/CategoryPort;", "", "delete", "", "id", "", "findAll", "", "Lcom/music/sale/domain/category/Category;", "findById", "getCategoryById", "getReferenceById", "save", "category", "music"})
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
    
    public abstract void delete(long id);
}