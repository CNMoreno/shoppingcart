package com.cnmmc.shoppingcart.business

import com.cnmmc.shoppingcart.model.Cart


interface ICartBusiness {
    fun list(): List<Cart>
    fun load(idCart: Long): Cart
    fun save(cart: Cart): Cart
    fun remove(idCart: Long)
    fun updateById(cart: Cart, idCart: Long)
}