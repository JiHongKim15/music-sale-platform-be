// Copyright (C) 2024 Your Name or Company
package com.music.sale.adapter.persistence.seller

import com.music.sale.application.seller.port.outport.SellerPort
import com.music.sale.domain.seller.Seller
import com.music.sale.domain.user.User
import org.springframework.stereotype.Component

@Component
class SellerPersistenceAdapter : SellerPort {
    override fun findById(id: Long): User? {
        // reference로 실행해서 간접적으로 조회
        TODO("Not yet implemented")
    }

    override fun save(seller: Seller): User {
        TODO("Not yet implemented")
    }
}
