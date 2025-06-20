// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.web.common

import com.music.sale.common.ResponseCode

data class ApiResponse<T>(
    val success: Boolean,
    val code: String,
    val message: String? = null,
    val data: T? = null,
) {
    companion object {
        fun <T> success(data: T) =
            ApiResponse(
                success = true,
                code = ResponseCode.SUCCESS.code,
                data = data,
            )

        fun <T> success(
            data: T,
            code: String,
        ) = ApiResponse(
            success = true,
            code = code,
            data = data,
        )

        fun <T> error(
            code: String,
            message: String? = null,
        ) = ApiResponse<T>(
            success = false,
            code = code,
            message = message,
        )
    }
}
