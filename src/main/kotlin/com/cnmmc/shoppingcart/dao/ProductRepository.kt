package com.cnmmc.shoppingcart.dao

import com.cnmmc.shoppingcart.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

}