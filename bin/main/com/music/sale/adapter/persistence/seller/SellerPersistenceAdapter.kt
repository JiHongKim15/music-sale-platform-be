package com.music.sale.adapter.persistence.seller

import com.music.sale.application.seller.port.out.SellerPort
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class SellerPersistenceAdapter : SellerPort {
    override fun findSellerById(id: Long): User {
        TODO("Not yet implemented")
    }
}