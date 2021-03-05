package com.cnmmc.shoppingcart.business

import com.cnmmc.shoppingcart.dao.CartRepository
import com.cnmmc.shoppingcart.dao.ProductCarsRepository
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Cart
import com.cnmmc.shoppingcart.model.Product
import com.cnmmc.shoppingcart.model.ProductCart
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*
import kotlin.jvm.Throws

@Service
class ProductCarBusiness : IProductCarBusiness {

    @Autowired
    val productCarsRepository: ProductCarsRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<ProductCart> {
        try {
            return productCarsRepository!!.findAll()
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idProductCart: Long): ProductCart {
        val op: Optional<ProductCart>
        try {
            op = productCarsRepository!!.findById(idProductCart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idProductCart")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(productCart:ProductCart): ProductCart {
        try {
            println(productCart)
            return productCarsRepository!!.save(productCart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }

    }

    @Throws(BusinessException::class)
    override fun remove(idProductCart: Long) {

        val op: Optional<ProductCart>
        try {
            op = productCarsRepository!!.findById(idProductCart)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idProductCart")
        } else {
            try {
                productCarsRepository!!.deleteById(idProductCart)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }

    }
}