// Copyright (C) 2024 Your Name or Company
package com.music.sale.web.common

data class ApiResponse<T>(
        val success: Boolean,
        val data: T? = null,
        val message: String? = null,
        val code: String? = null,
) {
    companion object {
        fun <T> success(
                data: T,
                code: String? = null,
        ): ApiResponse<T> {
            return ApiResponse(success = true, data = data, code = code)
        }

        fun <T> error(
                message: String,
                code: String? = null,
                data: T? = null,
        ): ApiResponse<T> {
            return ApiResponse(success = false, message = message, code = code, data = data)
        }
    }
}
