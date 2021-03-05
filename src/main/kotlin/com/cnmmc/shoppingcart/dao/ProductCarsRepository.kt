package com.cnmmc.shoppingcart.dao

import com.cnmmc.shoppingcart.model.ProductCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCarsRepository: JpaRepository<ProductCart, Long>{


}