package com.music.sale.adapter.persistence.category;

@org.springframework.stereotype.Component()
@org.springframework.transaction.annotation.Transactional()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/music/sale/adapter/persistence/category/CategoryPersistenceAdapter;", "Lcom/music/sale/application/category/port/outport/CategoryPort;", "repository", "Lcom/music/sale/adapter/persistence/category/repository/CategoryRepository;", "mapper", "Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;", "(Lcom/music/sale/adapter/persistence/category/repository/CategoryRepository;Lcom/music/sale/adapter/persistence/category/mapper/CategoryPersistenceMapper;)V", "delete", "", "id", "", "findAll", "", "Lcom/music/sale/domain/category/Category;", "findById", "getCategoryById", "getReferenceById", "save", "category", "music"})
public class CategoryPersistenceAdapter implements com.music.sale.application.category.port.outport.CategoryPort {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.category.repository.CategoryRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper mapper = null;
    
    public CategoryPersistenceAdapter(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.category.repository.CategoryRepository repository, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.persistence.category.mapper.CategoryPersistenceMapper mapper) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.category.Category getCategoryById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.util.List<com.music.sale.domain.category.Category> findAll() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.domain.category.Category findById(long id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.category.Category save(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.Category category) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.category.Category getReferenceById(long id) {
        return null;
    }
    
    @java.lang.Override()
    public void delete(long id) {
    }
}