package com.music.sale.domain.user

data class UserTerms(
    val id: Long? = null,
    val userId: Long? = null,
    val title: String? = null,
    val version: String? = null,
    val isAgreed: Boolean? = null,
) {
    companion object {
        @JvmStatic
        fun of(
            userId: Long,
            title: String,
            version: String,
            isAgreed: Boolean,
        ): UserTerms =
            UserTerms(
                userId = userId,
                title = title,
                version = version,
                isAgreed = isAgreed,
            )
    }
}
