package com.music.sale.application.user.port.outport

import com.music.sale.domain.user.User
import com.music.sale.domain.user.UserSocial
import com.music.sale.domain.user.UserTerms

interface UserCommandPort {
    fun save(user: User): User

    fun saveSocialAccount(userSocial: UserSocial): UserSocial

    fun saveTerms(userTerms: UserTerms): UserTerms

    fun updateSocialAccountUserId(
        fromUserId: Long,
        toUserId: Long,
    )

    fun deleteUser(userId: Long)
}
