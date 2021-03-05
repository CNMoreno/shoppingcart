package com.cnmmc.shoppingcart.web

import com.cnmmc.shoppingcart.business.ICartBusiness
import com.cnmmc.shoppingcart.dao.CartRepository
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Cart
import com.cnmmc.shoppingcart.model.ProductCart
import com.cnmmc.shoppingcart.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CARTS)
class CartRestController(private val cartRepository: CartRepository) {

    @Autowired
    val cartBusiness: ICartBusiness? = null


    @GetMapping("")
    fun list(): ResponseEntity<List<Cart>> {
        return try {
            ResponseEntity(cartBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idCart: Long): ResponseEntity<Cart> {
        return try {
            ResponseEntity(cartBusiness!!.load(idCart), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody cart: Cart): ResponseEntity<Any> {
        return try {
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_CARTS + "/" + cart.id)
            ResponseEntity.ok().body(cartBusiness!!.save(cart))
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody cart: Cart): ResponseEntity<Cart> {
        return try {
            ResponseEntity(cartBusiness!!.save(cart), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idCart: Long): ResponseEntity<Any> {
        return try {
            cartBusiness!!.remove(idCart)
            ResponseEntity({ "El Car con id $idCart fue eliminado" },HttpStatus.OK)
        } catch (e: NotFoundException) {
            ResponseEntity("El Car con el id $idCart no existe",HttpStatus.NOT_FOUND)
        }
    }
}