package com.music.sale.adapter.web.user;

@org.springframework.web.bind.annotation.RestController()
@org.springframework.web.bind.annotation.RequestMapping(value = {"/users"})
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u0017J\u001e\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u000b\u001a\u00020\u000eH\u0017J\u001e\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\b\b\u0001\u0010\u0010\u001a\u00020\u0011H\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0092\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0092\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/music/sale/adapter/web/user/UserController;", "", "useCase", "Lcom/music/sale/application/user/port/in/UserUseCase;", "userWebMapper", "Lcom/music/sale/adapter/web/user/mapper/UserWebMapper;", "(Lcom/music/sale/application/user/port/in/UserUseCase;Lcom/music/sale/adapter/web/user/mapper/UserWebMapper;)V", "createUserByEmail", "Lorg/springframework/http/ResponseEntity;", "Lcom/music/sale/adapter/web/common/ApiResponse;", "Lcom/music/sale/adapter/web/user/response/UserResponse;", "request", "Lcom/music/sale/adapter/web/user/reqeust/CreateUserByEmailRequest;", "createUserByProvider", "Lcom/music/sale/adapter/web/user/reqeust/CreateUserByProviderRequest;", "getUser", "id", "", "music"})
public class UserController {
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.application.user.port.in.UserUseCase useCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.music.sale.adapter.web.user.mapper.UserWebMapper userWebMapper = null;
    
    public UserController(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.port.in.UserUseCase useCase, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.mapper.UserWebMapper userWebMapper) {
        super();
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/email"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.adapter.web.user.response.UserResponse>> createUserByEmail(@org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.CreateUserByEmailRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.PostMapping(value = {"/provider"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.adapter.web.user.response.UserResponse>> createUserByProvider(@org.springframework.web.bind.annotation.RequestBody()
    @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.CreateUserByProviderRequest request) {
        return null;
    }
    
    @org.springframework.web.bind.annotation.GetMapping(value = {"/{id}"})
    @org.jetbrains.annotations.NotNull()
    public org.springframework.http.ResponseEntity<com.music.sale.adapter.web.common.ApiResponse<com.music.sale.adapter.web.user.response.UserResponse>> getUser(@org.springframework.web.bind.annotation.PathVariable()
    long id) {
        return null;
    }
}