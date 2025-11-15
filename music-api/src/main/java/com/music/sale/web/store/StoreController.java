package com.music.sale.web.store;

import com.music.sale.application.store.dto.CreateStoreInput;
import com.music.sale.application.store.dto.StoreOutput;
import com.music.sale.application.store.dto.UpdateStoreInput;
import com.music.sale.application.store.port.inport.StoreUseCase;
import com.music.sale.common.ApiResponse;
import com.music.sale.web.store.mapper.StoreWebMapper;
import com.music.sale.web.store.request.CreateStoreRequest;
import com.music.sale.web.store.request.GetStoreRequest;
import com.music.sale.web.store.request.UpdateStoreRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

    private static final Logger log = LoggerFactory.getLogger(StoreController.class);
    private final StoreUseCase storeUseCase;
    private final StoreWebMapper mapper;

    public StoreController(StoreUseCase storeUseCase, StoreWebMapper mapper) {
        this.storeUseCase = storeUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StoreOutput>>> getStores(@ModelAttribute GetStoreRequest request) {
        log.info("Get stores with request: {}", request);
        Page<StoreOutput> stores = storeUseCase.getStores(request.toPageRequest());
        return ResponseEntity.ok(
                ApiResponse.success(stores, "Store list fetched successfully")
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreOutput>> getStore(@PathVariable Long id) {
        log.info("Get store with id: {}", id);
        StoreOutput store = storeUseCase.getStoreById(id);
        return ResponseEntity.ok(
                ApiResponse.success(store, "Store fetched successfully")
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StoreOutput>> createStore(@RequestBody CreateStoreRequest request) {
        log.info("Create store with request: {}", request);
        CreateStoreInput input = mapper.toCreateStoreInput(request);
        StoreOutput store = storeUseCase.createStore(input);
        return ResponseEntity.ok(
                ApiResponse.success(store, "Store created successfully")
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StoreOutput>> updateStore(
            @PathVariable Long id,
            @RequestBody UpdateStoreRequest request) {
        log.info("Update store with id: {} and request: {}", id, request);
        UpdateStoreInput input = mapper.toUpdateStoreInput(id, request);
        StoreOutput store = storeUseCase.updateStore(input);
        return ResponseEntity.ok(
                ApiResponse.success(store, "Store updated successfully")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteStore(@PathVariable Long id) {
        log.info("Delete store with id: {}", id);
        storeUseCase.deleteStore(id);
        return ResponseEntity.ok(
                ApiResponse.success(null, "Store deleted successfully")
        );
    }
}
