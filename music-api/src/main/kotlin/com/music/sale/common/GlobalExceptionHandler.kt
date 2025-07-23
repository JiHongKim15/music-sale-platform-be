package com.music.sale.common

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationCredentialsNotFoundException::class)
    fun handleAuthException(e: AuthenticationCredentialsNotFoundException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(
                ApiResponse.error(
                    message = e.message ?: "로그인이 필요합니다.",
                    code = "UNAUTHORIZED",
                ),
            )

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(e: AccessDeniedException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(
                ApiResponse.error(
                    message = e.message ?: "접근 권한이 없습니다.",
                    code = "FORBIDDEN",
                ),
            )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(e: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.error(
                    message =
                        e.bindingResult.allErrors.firstOrNull()
                            ?.defaultMessage
                            ?: "잘못된 요청입니다.",
                    code = "BAD_REQUEST",
                ),
            )

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingParam(e: MissingServletRequestParameterException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.error(
                    message = "필수 파라미터(${e.parameterName})가 누락되었습니다.",
                    code = "BAD_REQUEST",
                ),
            )

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgument(e: IllegalArgumentException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                ApiResponse.error(
                    message = e.message ?: "잘못된 요청입니다.",
                    code = "BAD_REQUEST",
                ),
            )

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ApiResponse.error(
                    message = "서버 내부 오류가 발생했습니다.",
                    code = "INTERNAL_ERROR",
                ),
            )
}
