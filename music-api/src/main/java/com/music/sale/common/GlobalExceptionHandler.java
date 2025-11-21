package com.music.sale.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.music.sale.application.like.exception.LikeAlreadyExistsException;
import com.music.sale.application.like.exception.LikeNotFoundException;
import com.music.sale.application.like.exception.TargetNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

        private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        @Value("${spring.profiles.active:prod}")
        private String activeProfile = "prod";

        @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
        @ResponseStatus(HttpStatus.UNAUTHORIZED)
        public ApiResponse<Void> handleAuthException(
                        AuthenticationCredentialsNotFoundException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "로그인이 필요합니다."),
                                "UNAUTHORIZED");
        }

        @ExceptionHandler(AccessDeniedException.class)
        @ResponseStatus(HttpStatus.FORBIDDEN)
        public ApiResponse<Void> handleAccessDeniedException(
                        AccessDeniedException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "접근 권한이 없습니다."),
                                "FORBIDDEN");
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiResponse<Void> handleValidationException(
                        MethodArgumentNotValidException exception) {
                String message = exception.getBindingResult().getAllErrors().stream()
                                .findFirst()
                                .map(error -> error.getDefaultMessage())
                                .orElse("잘못된 요청입니다.");

                return ApiResponse.error(message, "BAD_REQUEST");
        }

        @ExceptionHandler(MissingServletRequestParameterException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiResponse<Void> handleMissingParam(
                        MissingServletRequestParameterException exception) {
                String message = "필수 파라미터(" + exception.getParameterName() + ")가 누락되었습니다.";
                return ApiResponse.error(message, "BAD_REQUEST");
        }

        @ExceptionHandler(IllegalArgumentException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiResponse<Void> handleIllegalArgument(
                        IllegalArgumentException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "잘못된 요청입니다."),
                                "BAD_REQUEST");
        }

        @ExceptionHandler(LikeAlreadyExistsException.class)
        @ResponseStatus(HttpStatus.CONFLICT)
        public ApiResponse<Void> handleLikeAlreadyExists(
                        LikeAlreadyExistsException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "이미 좋아요한 대상입니다."),
                                "LIKE_ALREADY_EXISTS");
        }

        @ExceptionHandler(LikeNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ApiResponse<Void> handleLikeNotFound(
                        LikeNotFoundException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "좋아요 기록을 찾을 수 없습니다."),
                                "LIKE_NOT_FOUND");
        }

        @ExceptionHandler(TargetNotFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public ApiResponse<Void> handleTargetNotFound(
                        TargetNotFoundException exception) {
                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "대상을 찾을 수 없습니다."),
                                "TARGET_NOT_FOUND");
        }

        @ExceptionHandler(Exception.class)
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public ApiResponse<Void> handleException(Exception exception) {
                log.error("=== EXCEPTION OCCURRED ===", exception);
                log.error("Exception Type: {}", exception.getClass().getName());
                log.error("Exception Message: {}", exception.getMessage());

                boolean isDevelopment = "local".equals(activeProfile) || "dev".equals(activeProfile);
                String message = isDevelopment
                                ? "서버 내부 오류: " + exception.getMessage()
                                                + " (" + exception.getClass().getSimpleName() + ")"
                                : "서버 내부 오류가 발생했습니다.";

                return ApiResponse.error(message, "INTERNAL_ERROR");
        }

        @ExceptionHandler(BusinessException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ApiResponse<Void> handleBusinessException(
                        BusinessException exception) {
                log.warn(
                                "비즈니스 예외 발생: {} - {}",
                                exception.getErrorCode().name(),
                                exception.getMessage());

                return ApiResponse.error(
                                messageOrDefault(
                                                exception.getMessage(),
                                                "오류가 발생했습니다."),
                                exception.getErrorCode().name());
        }

        private String messageOrDefault(String message, String defaultMessage) {
                return message != null ? message : defaultMessage;
        }
}
