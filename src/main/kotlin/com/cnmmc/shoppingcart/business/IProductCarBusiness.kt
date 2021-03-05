package com.cnmmc.shoppingcart.business


import com.cnmmc.shoppingcart.model.ProductCart

interface IProductCarBusiness {

    fun list(): List<ProductCart>
    fun load(idProductCart: Long): ProductCart
    fun save(productCart: ProductCart): ProductCart
    fun remove(idProductCart: Long)
}