package com.music.sale.adapter.persistence.category.entity;

@jakarta.persistence.Entity()
@jakarta.persistence.Table(name = "category")
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000e\b\u0017\u0018\u00002\u00020\u0001B=\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fR\u0016\u0010\n\u001a\u00020\u000b8\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016X\u0097\u0004\u00a2\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0004\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\u00008\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0016\u0010\t\u001a\u00020\u00058\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0016\u0010\u0006\u001a\u00020\u00078\u0016X\u0097\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;", "Lcom/music/sale/adapter/persistence/common/BaseEntity;", "id", "", "name", "", "type", "Lcom/music/sale/domain/category/CategoryType;", "parent", "path", "depth", "", "(Ljava/lang/Long;Ljava/lang/String;Lcom/music/sale/domain/category/CategoryType;Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;Ljava/lang/String;I)V", "getDepth", "()I", "getId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "()Ljava/lang/String;", "getParent", "()Lcom/music/sale/adapter/persistence/category/entity/CategoryEntity;", "getPath", "getType", "()Lcom/music/sale/domain/category/CategoryType;", "music"})
public class CategoryEntity extends com.music.sale.adapter.persistence.common.BaseEntity {
    @jakarta.persistence.Id()
    @jakarta.persistence.GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long id = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @jakarta.persistence.Enumerated(value = jakarta.persistence.EnumType.STRING)
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.category.CategoryType type = null;
    @jakarta.persistence.ManyToOne(fetch = jakarta.persistence.FetchType.LAZY)
    @jakarta.persistence.JoinColumn(name = "parent_id")
    @org.jetbrains.annotations.Nullable()
    private final com.music.sale.adapter.persistence.category.entity.CategoryEntity parent = null;
    @jakarta.persistence.Column(nullable = false)
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String path = null;
    @jakarta.persistence.Column(nullable = false)
    private final int depth = 0;
    
    public CategoryEntity(@org.jetbrains.annotations.Nullable()
    java.lang.Long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type, @org.jetbrains.annotations.Nullable()
    com.music.sale.adapter.persistence.category.entity.CategoryEntity parent, @org.jetbrains.annotations.NotNull()
    java.lang.String path, int depth) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public java.lang.Long getId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.domain.category.CategoryType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public com.music.sale.adapter.persistence.category.entity.CategoryEntity getParent() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public java.lang.String getPath() {
        return null;
    }
    
    public int getDepth() {
        return 0;
    }
}