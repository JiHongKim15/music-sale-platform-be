// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.seller

import com.music.sale.application.seller.port.outport.SellerPort
import com.music.sale.application.user.port.outport.UserPort
import com.music.sale.domain.seller.Seller
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class SellerPersistenceAdapter(private val userPort: UserPort) : SellerPort {
    override fun findById(id: Long): User? {
        // 판매자 ID를 통해 User 정보를 조회
        // 현재 아키텍처에서는 판매자도 사용자이므로 UserPort를 통해 조회
        return userPort.findById(id)
    }

    override fun save(seller: Seller): User {
        // 판매자 정보를 저장하고 해당 User 정보를 반환
        // 현재는 기본 사용자 정보만 저장하는 방식으로 구현
        return userPort.save(seller.user)
    }
}
