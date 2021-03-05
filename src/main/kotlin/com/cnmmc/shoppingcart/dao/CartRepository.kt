package com.cnmmc.shoppingcart.dao

import com.cnmmc.shoppingcart.model.Cart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CartRepository : JpaRepository<Cart, Long> {
}