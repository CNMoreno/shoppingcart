package com.cnmmc.shoppingcart

import com.cnmmc.shoppingcart.dao.CartRepository
import com.cnmmc.shoppingcart.dao.ProductRepository
import com.cnmmc.shoppingcart.model.Cart
import com.cnmmc.shoppingcart.model.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ShoppingcartApplication : CommandLineRunner {
    @Autowired
    //val productRepository: ProductRepository? = null
    val productRepository: ProductRepository? = null
    override fun run(vararg args: String?) {


    }


}

fun main(args: Array<String>) {
    runApplication<ShoppingcartApplication>(*args)
}
