package com.music.sale.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException ex) { // Changed method name and type
        HttpStatus status = HttpStatus.valueOf(ex.getErrorCode().getStatusCode());
        return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>(ApiResponse.error("서버 오류가 발생했습니다: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
