package com.music.sale.common;

import com.music.sale.config.ErrorProperties;
import com.music.sale.domain.exception.DomainException;
import java.util.Locale;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;
  private final ErrorProperties errorProperties;

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ApiResponse<Void>> handleBusinessException(
      BusinessException ex, Locale locale) {
    String key = ex.getErrorDefinition().getKey();
    ErrorProperties.ErrorDetail errorDetail =
        errorProperties.getCodes().get(key.replace("error.", ""));

    int statusCode =
        Optional.ofNullable(errorDetail).map(ErrorProperties.ErrorDetail::status).orElse(500);

    String message =
        Optional.ofNullable(ex.getMessage())
            .filter(msg -> !msg.equals(key))
            .orElse(
                messageSource.getMessage(
                    key, ex.getArgs(), "An unexpected error occurred.", locale));

    HttpStatus status = HttpStatus.valueOf(statusCode);
    return new ResponseEntity<>(ApiResponse.error(message), status);
  }

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ApiResponse<Void>> handleDomainException(
      DomainException ex, Locale locale) {
    String key = ex.getErrorKey();
    ErrorProperties.ErrorDetail errorDetail =
        errorProperties.getCodes().get(key.replace("error.", ""));

    int statusCode =
        Optional.ofNullable(errorDetail).map(ErrorProperties.ErrorDetail::status).orElse(400);

    String message = messageSource.getMessage(key, null, ex.getMessage(), locale);

    HttpStatus status = HttpStatus.valueOf(statusCode);
    return new ResponseEntity<>(ApiResponse.error(message), status);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleAllExceptions(Exception ex) {
    return new ResponseEntity<>(
        ApiResponse.error("서버 오류가 발생했습니다: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
