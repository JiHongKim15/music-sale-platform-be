package com.music.sale.application.user.service

import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.application.user.dto.output.UserSocialOutput
import com.music.sale.application.user.dto.output.UserTermsOutput
import com.music.sale.application.user.exception.UserErrorCode
import com.music.sale.application.user.mapper.UserMapper
import com.music.sale.application.user.port.inport.UserQueryUseCase
import com.music.sale.application.user.port.outport.UserQueryPort
import com.music.sale.common.BusinessException
import com.music.sale.domain.user.User
import com.music.sale.domain.user.enums.SocialProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional(readOnly = true)
class UserQueryService(
    private val userQueryPort: UserQueryPort,
    private val userMapper: UserMapper,
) : UserQueryUseCase {
    override fun getUserById(userId: Long): UserOutput =
        userQueryPort
            .findById(userId)
            .map { userMapper.toOutput(it) }
            .orElseThrow { BusinessException(UserErrorCode.USER_NOT_FOUND) }

    override fun findById(userId: Long): Optional<User> = userQueryPort.findById(userId)

    override fun findBySocialAccount(
        provider: SocialProvider,
        providerId: String,
    ): Optional<User> = userQueryPort.findBySocialAccount(provider, providerId)

    override fun findByCi(ci: String): Optional<User> = userQueryPort.findByCi(ci)

    override fun getSocialsByUserId(userId: Long): List<UserSocialOutput> {
        if (userQueryPort.findById(userId).isEmpty) {
            throw BusinessException(UserErrorCode.USER_NOT_FOUND)
        }
        return userQueryPort.findSocialsByUserId(userId).map { userMapper.toOutput(it) }
    }

    override fun getTermsByUserId(userId: Long): List<UserTermsOutput> {
        if (userQueryPort.findById(userId).isEmpty) {
            throw BusinessException(UserErrorCode.USER_NOT_FOUND)
        }
        return userQueryPort.findTermsByUserId(userId).map { userMapper.toOutput(it) }
    }
}
