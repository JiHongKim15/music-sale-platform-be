// Copyright (C) 2024 Your Name or Company
package com.music.sale.application.seller.port.outport

import com.music.sale.domain.seller.Seller
import com.music.sale.domain.user.User

interface SellerPort {
    fun findById(id: Long): User?

    fun save(seller: Seller): User
}
