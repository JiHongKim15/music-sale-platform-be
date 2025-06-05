package com.music.sale.adapter.web.common

data class ApiResponse<T>(
    val success: Boolean,
    val code: String,
    val message: String? = null,
    val data: T? = null
) {
    companion object {
        fun <T> success(data: T, code: String = "SUCCESS") = ApiResponse(
            success = true,
            code = code,
            data = data
        )

        fun <T> error(code: String, message: String? = null) = ApiResponse<T>(
            success = false,
            code = code,
            message = message
        )
    }
} 