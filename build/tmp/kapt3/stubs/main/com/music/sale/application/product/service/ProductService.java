package com.music.sale.application.product.service;

@org.springframework.stereotype.Service()
@org.springframework.transaction.annotation.Transactional()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\r\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0019H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/music/sale/application/product/service/ProductService;", "Lcom/music/sale/application/product/port/inport/ProductUseCase;", "port", "Lcom/music/sale/application/product/port/outport/ProductPort;", "mapper", "Lcom/music/sale/application/product/mapper/ProductMapper;", "categoryService", "Lcom/music/sale/application/category/service/CategoryService;", "sellerPort", "Lcom/music/sale/application/seller/port/outport/SellerPort;", "(Lcom/music/sale/application/product/port/outport/ProductPort;Lcom/music/sale/application/product/mapper/ProductMapper;Lcom/music/sale/application/category/service/CategoryService;Lcom/music/sale/application/seller/port/outport/SellerPort;)V", "createProduct", "Lcom/music/sale/application/product/dto/ProductOutput;", "input", "Lcom/music/sale/application/product/dto/CreateProductInput;", "deleteProduct", "id", "", "getProducts", "Lorg/springframework/data/domain/Page;", "pageable", "Lcom/music/sale/common/Pageable;", "searchProducts", "Lcom/music/sale/application/product/dto/SearchProductInput;", "updateProduct", "Lcom/music/sale/application/product/dto/UpdateProductInput;", "music"})
public class ProductService implements com.music.sale.application.product.port.inport.ProductUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.product.port.outport.ProductPort port = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.product.mapper.ProductMapper mapper = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.category.service.CategoryService categoryService = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.seller.port.outport.SellerPort sellerPort = null;
    
    public ProductService(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.port.outport.ProductPort port, @org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.mapper.ProductMapper mapper, @org.jetbrains.annotations.NotNull()
    com.music.sale.application.category.service.CategoryService categoryService, @org.jetbrains.annotations.NotNull()
    com.music.sale.application.seller.port.outport.SellerPort sellerPort) {
        super();
    }
    
    /**
     * 상품 관련 비즈니스 로직을 처리하는 서비스 클래스 ProductPort를 통해 영속성 계층과 상호작용
     */
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput> getProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput> searchProducts(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.SearchProductInput input, @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageable) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.ProductOutput createProduct(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.CreateProductInput input) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.ProductOutput updateProduct(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.dto.UpdateProductInput input) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.product.dto.ProductOutput deleteProduct(long id) {
        return null;
    }
}