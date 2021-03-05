package com.cnmmc.shoppingcart


import com.cnmmc.shoppingcart.dao.ProductCarsRepository
import com.cnmmc.shoppingcart.model.Cart
import com.cnmmc.shoppingcart.model.Product
import com.cnmmc.shoppingcart.model.ProductCart
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DatabaseLoader(


    private val productCarsRepository: ProductCarsRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {

        saveProductCart()
    }

    fun saveProductCart() {
        val insertProductCar = ProductCart(
            id = -1,
            quality = 1,
            Product(
                name = "Play 4",
                sku = 2,
                description = "Es la cuarta videoconsola del modelo PlayStation. Es la segunda consola de Sony en ser diseñada por Mark Cerny y forma parte de las videoconsolas de octava generación"
            ),
            Cart(status = "pending"),
        )

        productCarsRepository.save(insertProductCar)
    }

}