package com.music.sale.web.store.mapper;

import com.music.sale.application.store.dto.CreateStoreInput;
import com.music.sale.application.store.dto.UpdateStoreInput;
import com.music.sale.web.store.request.CreateStoreRequest;
import com.music.sale.web.store.request.UpdateStoreRequest;
import org.springframework.stereotype.Component;

/**
 * Store Web Mapper
 * Web Layer의 Request DTO를 Application Layer의 Input DTO로 변환
 */
@Component
public class StoreWebMapper {

    public CreateStoreInput toCreateStoreInput(CreateStoreRequest request) {
        return new CreateStoreInput();
    }

    public UpdateStoreInput toUpdateStoreInput(Long id, UpdateStoreRequest request) {
        return new UpdateStoreInput();
    }
}
