package com.music.sale.adapter.web.product;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/products"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0017J\u001e\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u0017J$\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00110\t0\b2\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u0017J.\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00110\t0\b2\b\b\u0001\u0010\u000b\u001a\u00020\u00152\b\b\u0001\u0010\u0012\u001a\u00020\u0013H\u0017J(\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\u000b\u001a\u00020\u0017H\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/music/sale/adapter/web/product/ProductController;", "", "useCase", "Lcom/music/sale/application/product/port/in/ProductUseCase;", "mapper", "Lcom/music/sale/adapter/web/product/mapper/ProductWebMapper;", "(Lcom/music/sale/application/product/port/in/ProductUseCase;Lcom/music/sale/adapter/web/product/mapper/ProductWebMapper;)V", "createProduct", "Lorg/springframework/http/ResponseEntity;", "Lcom/music/sale/adapter/web/common/ApiResponse;", "Lcom/music/sale/application/product/dto/ProductOutput;", "request", "Lcom/music/sale/adapter/web/product/request/CreateProductRequest;", "deleteProduct", "id", "", "getProducts", "Lorg/springframework/data/domain/Page;", "pageRequest", "Lcom/music/sale/common/Pageable;", "searchProducts", "Lcom/music/sale/adapter/web/product/request/SearchProductRequest;", "updateProduct", "Lcom/music/sale/adapter/web/product/request/UpdateProductRequest;", "music"})
public class ProductController {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.product.port.in.ProductUseCase useCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.web.product.mapper.ProductWebMapper mapper = null;
    
    public ProductController(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.product.port.in.ProductUseCase useCase, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.mapper.ProductWebMapper mapper) {
        super();
    }
    
    @org.springframework.web.bind.annotation.GetMapping()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput>>> getProducts(@org.springframework.web.bind.annotation.ModelAttribute()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageRequest) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.GetMapping(value = {"/search"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<org.springframework.data.domain.Page<com.music.sale.application.product.dto.ProductOutput>>> searchProducts(@org.springframework.web.bind.annotation.ModelAttribute()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.SearchProductRequest request, @org.springframework.web.bind.annotation.ModelAttribute()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.common.Pageable pageRequest) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping()
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.application.product.dto.ProductOutput>> createProduct(@org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.CreateProductRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PutMapping(value = {"/{id}"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.application.product.dto.ProductOutput>> updateProduct(@org.springframework.web.bind.annotation.PathVariable()
    long id, @org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.product.request.UpdateProductRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.DeleteMapping(value = {"/{id}"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.application.product.dto.ProductOutput>> deleteProduct(@org.springframework.web.bind.annotation.PathVariable()
    long id) {
        return null;
    }
}