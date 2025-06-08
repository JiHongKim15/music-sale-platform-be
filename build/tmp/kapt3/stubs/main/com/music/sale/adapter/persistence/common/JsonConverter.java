package com.music.sale.adapter.persistence.common;

@jakarta.persistence.Converter()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005\u00a2\u0006\u0002\u0010\u0005J \u0010\b\u001a\u0004\u0018\u00010\u00032\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0002H\u0016J \u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/music/sale/adapter/persistence/common/JsonConverter;", "Ljakarta/persistence/AttributeConverter;", "", "", "", "()V", "objectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "convertToDatabaseColumn", "attribute", "convertToEntityAttribute", "dbData", "music"})
public final class JsonConverter implements jakarta.persistence.AttributeConverter<java.util.Map<java.lang.String, ? extends java.lang.Object>, java.lang.String> {
    @org.jetbrains.annotations.NotNull()
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper = null;
    
    public JsonConverter() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.String convertToDatabaseColumn(@org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, ? extends java.lang.Object> attribute) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.util.Map<java.lang.String, java.lang.Object> convertToEntityAttribute(@org.jetbrains.annotations.Nullable()
    java.lang.String dbData) {
        return null;
    }
}