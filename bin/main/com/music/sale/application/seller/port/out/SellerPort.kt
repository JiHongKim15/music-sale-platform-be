package com.music.sale.application.seller.port.out

import com.music.sale.domain.user.User

interface SellerPort {
    fun findSellerById(id: Long): User
} 