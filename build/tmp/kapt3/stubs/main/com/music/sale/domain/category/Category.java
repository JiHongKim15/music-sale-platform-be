package com.music.sale.domain.category;

/**
 * 카테고리 도메인 모델
 * 계층 구조를 가진 카테고리를 표현
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u0000 22\u00020\u0001:\u00012BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010%\u001a\u00020&J\u000e\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u0000J\u0006\u0010)\u001a\u00020&J\u000e\u0010*\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0000J\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020\rJ\u0010\u0010.\u001a\u00020&2\b\u0010/\u001a\u0004\u0018\u00010\u0000J\u000e\u00100\u001a\u00020&2\u0006\u0010(\u001a\u00020\u0000J\b\u00101\u001a\u00020&H\u0002R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00000\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0000X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001b\"\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u00063"}, d2 = {"Lcom/music/sale/domain/category/Category;", "", "id", "", "name", "", "type", "Lcom/music/sale/domain/category/CategoryType;", "parent", "path", "depth", "", "isActive", "", "(JLjava/lang/String;Lcom/music/sale/domain/category/CategoryType;Lcom/music/sale/domain/category/Category;Ljava/lang/String;IZ)V", "children", "", "getDepth", "()I", "setDepth", "(I)V", "getId", "()J", "()Z", "setActive", "(Z)V", "getName", "()Ljava/lang/String;", "getParent", "()Lcom/music/sale/domain/category/Category;", "setParent", "(Lcom/music/sale/domain/category/Category;)V", "getPath", "setPath", "(Ljava/lang/String;)V", "getType", "()Lcom/music/sale/domain/category/CategoryType;", "activate", "", "addChild", "child", "deactivate", "isDescendantOf", "category", "isLeaf", "isRoot", "moveTo", "newParent", "removeChild", "updatePath", "Companion", "music"})
public final class Category {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.domain.category.CategoryType type = null;
    @org.jetbrains.annotations.Nullable()
    private com.music.sale.domain.category.Category parent;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String path;
    private int depth;
    private boolean isActive;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.music.sale.domain.category.Category> children = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.music.sale.domain.category.Category.Companion Companion = null;
    
    public Category(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.CategoryType type, @org.jetbrains.annotations.Nullable()
    com.music.sale.domain.category.Category parent, @org.jetbrains.annotations.NotNull()
    java.lang.String path, int depth, boolean isActive) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.music.sale.domain.category.CategoryType getType() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.music.sale.domain.category.Category getParent() {
        return null;
    }
    
    public final void setParent(@org.jetbrains.annotations.Nullable()
    com.music.sale.domain.category.Category p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPath() {
        return null;
    }
    
    public final void setPath(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getDepth() {
        return 0;
    }
    
    public final void setDepth(int p0) {
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final void setActive(boolean p0) {
    }
    
    public final boolean isRoot() {
        return false;
    }
    
    public final boolean isLeaf() {
        return false;
    }
    
    public final void addChild(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.Category child) {
    }
    
    public final void removeChild(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.Category child) {
    }
    
    public final void moveTo(@org.jetbrains.annotations.Nullable()
    com.music.sale.domain.category.Category newParent) {
    }
    
    private final void updatePath() {
    }
    
    public final boolean isDescendantOf(@org.jetbrains.annotations.NotNull()
    com.music.sale.domain.category.Category category) {
        return false;
    }
    
    public final void deactivate() {
    }
    
    public final void activate() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/music/sale/domain/category/Category$Companion;", "", "()V", "createRoot", "Lcom/music/sale/domain/category/Category;", "name", "", "type", "Lcom/music/sale/domain/category/CategoryType;", "music"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.music.sale.domain.category.Category createRoot(@org.jetbrains.annotations.NotNull()
        java.lang.String name, @org.jetbrains.annotations.NotNull()
        com.music.sale.domain.category.CategoryType type) {
            return null;
        }
    }
}