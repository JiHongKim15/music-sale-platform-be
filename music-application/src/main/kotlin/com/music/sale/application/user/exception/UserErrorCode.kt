package com.music.sale.application.user.exception

import com.music.sale.common.ErrorDefinition

enum class UserErrorCode(private val key: String) : ErrorDefinition {
    USER_NOT_FOUND("error.USER_NOT_FOUND"),
    USER_PERMISSION_DENIED("error.USER_PERMISSION_DENIED"),
    ;

    override fun getKey(): String = key
}
