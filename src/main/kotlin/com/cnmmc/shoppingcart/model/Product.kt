package com.cnmmc.shoppingcart.model

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(val name: String = "", val sku: Long = 0, val description: String = "") {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}