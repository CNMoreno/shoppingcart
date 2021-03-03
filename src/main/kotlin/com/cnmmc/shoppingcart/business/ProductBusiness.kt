package com.cnmmc.shoppingcart.business

import com.cnmmc.shoppingcart.dao.ProductRepository
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.Throws

@Service
class ProductBusiness : IProductBusiness {

    @Autowired
    val productRepository: ProductRepository? = null

    @Throws(BusinessException::class)
    override fun list(): List<Product> {
        try {
            return productRepository!!.findAll()
        } catch (e: Exception) {
            throw  BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class, NotFoundException::class)
    override fun load(idProduct: Long): Product {
        val op: Optional<Product>
        try {
            op = productRepository!!.findById(idProduct)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idProduct")
        }
        return op.get()
    }

    @Throws(BusinessException::class)
    override fun save(product: Product): Product {

        try {
            return productRepository!!.save(product)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }


    @Throws(BusinessException::class)
    override fun remove(idProduct: Long) {

        val op: Optional<Product>
        try {
            op = productRepository!!.findById(idProduct)
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
        if (!op.isPresent) {
            throw NotFoundException("No se encontro el producto con el id $idProduct")
        } else {
            try {
                productRepository!!.deleteById(idProduct)
            } catch (e: Exception) {
                throw BusinessException(e.message)
            }
        }
    }
}