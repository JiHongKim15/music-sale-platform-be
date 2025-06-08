package com.music.sale.adapter.web.user.mapper;

@org.springframework.stereotype.Component()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016J\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\u0016\u00a2\u0006\u0002\u0010\rJ\u001d\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0011H\u0016\u00a2\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/music/sale/adapter/web/user/mapper/UserWebMapper;", "", "()V", "toCreateUserByEmailInput", "Lcom/music/sale/application/user/dto/CreateUserByEmailInput;", "request", "Lcom/music/sale/adapter/web/user/reqeust/CreateUserByEmailRequest;", "toCreateUserByProviderInput", "Lcom/music/sale/application/user/dto/CreateUserByProviderInput;", "Lcom/music/sale/adapter/web/user/reqeust/CreateUserByProviderRequest;", "toCreateUserInput", "error/NonExistentClass", "Lcom/music/sale/adapter/web/user/reqeust/CreateUserRequest;", "(Lcom/music/sale/adapter/web/user/reqeust/CreateUserRequest;)Lerror/NonExistentClass;", "toUpdateUserInput", "id", "", "Lcom/music/sale/adapter/web/user/reqeust/UpdateUserRequest;", "(JLcom/music/sale/adapter/web/user/reqeust/UpdateUserRequest;)Lerror/NonExistentClass;", "toUserResponse", "Lcom/music/sale/adapter/web/user/response/UserResponse;", "output", "Lcom/music/sale/application/user/dto/UserOutput;", "music"})
public class UserWebMapper {
    
    public UserWebMapper() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public error.NonExistentClass toCreateUserInput(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.CreateUserRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public error.NonExistentClass toUpdateUserInput(long id, @org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.UpdateUserRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.user.dto.CreateUserByEmailInput toCreateUserByEmailInput(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.CreateUserByEmailRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.application.user.dto.CreateUserByProviderInput toCreateUserByProviderInput(@org.jetbrains.annotations.NotNull()
    com.music.sale.adapter.web.user.reqeust.CreateUserByProviderRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public com.music.sale.adapter.web.user.response.UserResponse toUserResponse(@org.jetbrains.annotations.NotNull()
    com.music.sale.application.user.dto.UserOutput output) {
        return null;
    }
}