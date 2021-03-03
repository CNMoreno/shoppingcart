package com.cnmmc.shoppingcart.business

import com.cnmmc.shoppingcart.model.Product

interface IProductBusiness {

    fun list(): List<Product>
    fun load(idProduct: Long): Product
    fun save(product: Product): Product
    fun remove(idProduct: Long)
}