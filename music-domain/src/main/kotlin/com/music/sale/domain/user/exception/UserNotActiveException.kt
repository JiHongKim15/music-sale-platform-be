package com.music.sale.domain.user.exception

import com.music.sale.domain.exception.DomainException

class UserNotActiveException : DomainException(ERROR_KEY) {
    companion object {
        private const val ERROR_KEY = "error.USER_NOT_ACTIVE"
    }
}
