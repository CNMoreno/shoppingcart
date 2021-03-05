package com.cnmmc.shoppingcart.utils

class Constants {
    companion object {
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION

        //Base API endpoint para productos
        const val URL_BASE_PRODUCTS = "$URL_BASE/products"

        //Base API endpoint para carts
        const val URL_BASE_CARTS = "$URL_BASE/carts"

        //Base API endpoint para product_cars
        const val URL_BASE_PRODUCTCARS = "$URL_BASE/product-cars"


    }
}