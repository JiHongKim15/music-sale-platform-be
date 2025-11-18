package com.music.sale.common

import com.music.sale.application.common.BusinessException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
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
    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)
    
    @Value("\${spring.profiles.active:prod}")
    private lateinit var activeProfile: String
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

    @ExceptionHandler(com.music.sale.application.like.exception.LikeAlreadyExistsException::class)
    fun handleLikeAlreadyExists(e: com.music.sale.application.like.exception.LikeAlreadyExistsException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(
                ApiResponse.error(
                    message = e.message ?: "이미 좋아요한 대상입니다.",
                    code = "LIKE_ALREADY_EXISTS",
                ),
            )

    @ExceptionHandler(com.music.sale.application.like.exception.LikeNotFoundException::class)
    fun handleLikeNotFound(e: com.music.sale.application.like.exception.LikeNotFoundException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponse.error(
                    message = e.message ?: "좋아요 기록을 찾을 수 없습니다.",
                    code = "LIKE_NOT_FOUND",
                ),
            )

    @ExceptionHandler(com.music.sale.application.like.exception.TargetNotFoundException::class)
    fun handleTargetNotFound(e: com.music.sale.application.like.exception.TargetNotFoundException): ResponseEntity<ApiResponse<Unit>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(
                ApiResponse.error(
                    message = e.message ?: "대상을 찾을 수 없습니다.",
                    code = "TARGET_NOT_FOUND",
                ),
            )

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ApiResponse<Unit>> {
        // 로그에는 항상 상세 에러 기록 (개발자용)
        logger.error("=== EXCEPTION OCCURRED ===", e)
        logger.error("Exception Type: ${e.javaClass.name}")
        logger.error("Exception Message: ${e.message}")
        
        // 개발 환경에서만 상세 에러 메시지 반환
        val isDevelopment = activeProfile in listOf("local", "dev")
        val errorMessage = if (isDevelopment) {
            "서버 내부 오류: ${e.message} (${e.javaClass.simpleName})"
        } else {
            "서버 내부 오류가 발생했습니다."
        }
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                ApiResponse.error(
                    message = errorMessage,
                    code = "INTERNAL_ERROR",
                ),
            )
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ApiResponse<Unit> {
        logger.warn("비즈니스 예외 발생: ${e.errorCode.name} - ${e.message}")

        return ApiResponse.error(
            message = e.message ?: "오류가 발생했습니다.",
            code = e.errorCode.name,
        )
    }
}
