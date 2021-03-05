package com.cnmmc.shoppingcart.web

import com.cnmmc.shoppingcart.business.IProductBusiness
import com.cnmmc.shoppingcart.exception.BusinessException
import com.cnmmc.shoppingcart.exception.NotFoundException
import com.cnmmc.shoppingcart.model.Product
import com.cnmmc.shoppingcart.utils.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PRODUCTS)
class ProductRestContoller {

    @Autowired
    val productBusiness: IProductBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Product>> {
        return try {
            ResponseEntity(productBusiness!!.list(), HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idProduct: Long): ResponseEntity<Product> {
        return try {
            ResponseEntity(productBusiness!!.load(idProduct), HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("")
    fun insert(@RequestBody product: Product): ResponseEntity<Any> {
        return try {

            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_PRODUCTS + "/" + product.id)
            ResponseEntity.ok().body(productBusiness!!.save(product))
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/")
    fun update(@RequestBody product: Product): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(productBusiness!!.save(product))
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idProduct: Long): ResponseEntity<Any> {
        return try {
            productBusiness!!.remove(idProduct)
            ResponseEntity(HttpStatus.OK)
        } catch (e: BusinessException) {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e: NotFoundException) {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
