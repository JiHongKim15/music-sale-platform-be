package com.music.sale.application.user.service

import com.music.sale.application.user.dto.input.AgreeTermsInput
import com.music.sale.application.user.dto.input.CreateSocialUserInput
import com.music.sale.application.user.dto.input.UpdateUserInput
import com.music.sale.application.user.dto.output.UserOutput
import com.music.sale.application.user.exception.UserErrorCode
import com.music.sale.application.user.mapper.UserMapper
import com.music.sale.application.user.port.inport.UserCommandUseCase
import com.music.sale.application.user.port.outport.UserCommandPort
import com.music.sale.application.user.port.outport.UserQueryPort
import com.music.sale.common.BusinessException
import com.music.sale.domain.user.User
import com.music.sale.domain.user.UserSocial
import com.music.sale.domain.user.UserTerms
import com.music.sale.domain.user.enums.UserRole
import com.music.sale.domain.user.enums.UserStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserCommandService(
    private val userCommandPort: UserCommandPort,
    private val userQueryPort: UserQueryPort,
    private val userMapper: UserMapper,
) : UserCommandUseCase {
    override fun createSocialUser(input: CreateSocialUserInput): User {
        val newUser =
            User(
                nickname = input.nickname,
                profileImageUrl = input.profileImageUrl,
                email = input.email,
                role = UserRole.USER,
                status = UserStatus.ACTIVE,
            )

        val savedUser = userCommandPort.save(newUser)

        val socialAccount =
            UserSocial(
                userId = savedUser.id,
                provider = input.provider,
                providerId = input.providerId,
            )

        userCommandPort.saveSocialAccount(socialAccount)

        return savedUser
    }

    override fun updateUser(
        userId: Long,
        input: UpdateUserInput,
        currentUserId: Long,
    ): UserOutput {
        if (userId != currentUserId) {
            throw BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신의 프로필만 수정할 수 있습니다.")
        }

        val user =
            userQueryPort.findById(userId).orElseThrow {
                BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: $userId")
            }

        val updatedUser = user.updateProfile(input.nickname, input.profileImageUrl ?: "")
        val savedUser = userCommandPort.save(updatedUser)

        return userMapper.toOutput(savedUser)
    }

    override fun withdrawUser(
        userId: Long,
        currentUserId: Long,
    ) {
        if (userId != currentUserId) {
            throw BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신만 탈퇴할 수 있습니다.")
        }

        val user =
            userQueryPort.findById(userId).orElseThrow {
                BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: $userId")
            }

        user.withdraw()
        userCommandPort.save(user)
    }

    override fun agreeToTerms(
        input: AgreeTermsInput,
        currentUserId: Long,
    ) {
        if (input.userId != currentUserId) {
            throw BusinessException(UserErrorCode.USER_PERMISSION_DENIED, "자신의 약관만 동의할 수 있습니다.")
        }

        userQueryPort.findById(input.userId).orElseThrow {
            BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: ${input.userId}")
        }

        val userTerms = UserTerms.of(input.userId, input.title, input.version, input.isAgreed)
        userCommandPort.saveTerms(userTerms)
    }

    override fun verifyIdentity(
        userId: Long,
        ci: String,
        realName: String,
        phoneNumber: String,
        birthDate: String,
    ): User {
        val currentUser =
            userQueryPort.findById(userId).orElseThrow {
                BusinessException(UserErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다: $userId")
            }

        val existingUser = userQueryPort.findByCi(ci)

        if (existingUser.isPresent && existingUser.get().id != currentUser.id) {
            val targetUser = existingUser.get()
            val currentUserId = currentUser.id!!
            val targetUserId = targetUser.id!!
            userCommandPort.updateSocialAccountUserId(currentUserId, targetUserId)
            userCommandPort.deleteUser(currentUserId)
            val verifiedUser = targetUser.verifyIdentity(ci, realName, phoneNumber, birthDate)
            return userCommandPort.save(verifiedUser)
        }

        val verifiedUser = currentUser.verifyIdentity(ci, realName, phoneNumber, birthDate)
        return userCommandPort.save(verifiedUser)
    }
}
