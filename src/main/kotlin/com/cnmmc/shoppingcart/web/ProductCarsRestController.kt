package com.cnmmc.shoppingcart.web

import com.cnmmc.shoppingcart.business.ProductCarBusiness
import com.cnmmc.shoppingcart.dao.CartRepository
import com.cnmmc.shoppingcart.dao.ProductCarsRepository
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Cart
import com.cnmmc.shoppingcart.model.Product
import com.cnmmc.shoppingcart.model.ProductCart
import com.cnmmc.shoppingcart.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCTCARS)
class ProductCarsRestController(private val productCarsRepository: ProductCarsRepository) {

    @Autowired
    val productCartBusiness: ProductCarBusiness? = null


    @GetMapping("")
    fun list(): ResponseEntity<List<ProductCart>> {
        return try {
            ResponseEntity(productCartBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idProductCart: Long): ResponseEntity<ProductCart> {
        return try {
            ResponseEntity(productCartBusiness!!.load(idProductCart), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody productCart: ProductCart): ResponseEntity<ProductCart> {
        return try {
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTCARS + "/" + productCart.id)
            ResponseEntity(productCartBusiness!!.save(productCart), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/")
    fun update(@RequestBody productCart: ProductCart): ResponseEntity<ProductCart> {
        return try {
            ResponseEntity(productCartBusiness!!.save(productCart), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idProductCart: Long): ResponseEntity<Any> {
        return try {
            productCartBusiness!!.remove(idProductCart)
            ResponseEntity("El producto con id $idProductCart ha sido eliminado", HttpStatus.OK)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PutMapping("/{id}")
    fun updateStatus(
        @PathVariable(value = "id") idCart: Long,
        @RequestBody productCart: ProductCart
    ): ResponseEntity<ProductCart> {
        println(productCart.product!!.name)
        return productCarsRepository.findById(idCart).map { existingCart ->
            val cartUpdate: ProductCart = existingCart
                .copy(
                    id = idCart,
                    quality = productCart.quality,
                    Product(
                        productCart.product!!.id,
                        productCart.product!!.name,
                        productCart.product!!.sku,
                        productCart.product!!.description
                    ),
                    Cart(id= productCart.cart!!.id,status = "completed")
                )
            ResponseEntity.ok().body(productCarsRepository.save(cartUpdate))
        }.orElse(ResponseEntity.notFound().build())

    }
}