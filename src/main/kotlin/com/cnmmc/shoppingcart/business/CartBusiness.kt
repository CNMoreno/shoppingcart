package com.cnmmc.shoppingcart.business

import com.cnmmc.shoppingcart.dao.CartRepository
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Cart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.*
import kotlin.jvm.Throws

@Service
class CartBusiness : ICartBusiness {

    @Autowired
    val cartRepository: CartRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Cart> {
        try {
            return cartRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idCart: Long): Cart {
        val op: Optional<Cart>
        try {
            op = cartRepository!!.findById(idCart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idCart")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(cart: Cart): Cart {

        try {
            return cartRepository!!.save(cart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class)
    override fun remove(idCart: Long) {
        val op: Optional<Cart>
        try {
            op = cartRepository!!.findById(idCart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idCart")
        } else {
            try {
                cartRepository!!.deleteById(idCart)
            } catch (e: java.lang.Exception) {
                throw BusinessException(e.message)
            }
        }
    }

    @Throws(BusinessException::class)
    override fun updateById(cart: Cart, idCart: Long) {

        cartRepository!!.findById(idCart).map { existingCart ->
            val cartUpdate: Cart = existingCart
                .copy(status = cart.status)
            cartRepository!!.save(cartUpdate)
        }.orElse(throw NotFoundException("No se encontro el producto con el id $idCart"))

        /*if (op.isPresent) {
            try {
                val cartUpdate: Cart = op.get();

            } catch (e: Exception) {
                throw BusinessException(e.message)
            }

        } else {
            throw NotFoundException("No se encontro el producto con el id $idCart")
        }
        */

    }
}
