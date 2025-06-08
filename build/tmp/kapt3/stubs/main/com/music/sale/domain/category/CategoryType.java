package com.music.sale.domain.category;

/**
 * 카테고리 타입을 정의하는 enum
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/music/sale/domain/category/CategoryType;", "", "(Ljava/lang/String;I)V", "PRODUCT", "STORE", "BOARD", "SEARCH", "STATISTICS", "music"})
public enum CategoryType {
    /*public static final*/ PRODUCT /* = new PRODUCT() */,
    /*public static final*/ STORE /* = new STORE() */,
    /*public static final*/ BOARD /* = new BOARD() */,
    /*public static final*/ SEARCH /* = new SEARCH() */,
    /*public static final*/ STATISTICS /* = new STATISTICS() */;
    
    CategoryType() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.music.sale.domain.category.CategoryType> getEntries() {
        return null;
    }
}