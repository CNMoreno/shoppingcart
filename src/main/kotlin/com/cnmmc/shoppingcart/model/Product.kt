package com.cnmmc.shoppingcart.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,

    var name: String = "",
    var sku: Long = 0,
    var description: String = "",

    @OneToMany(
        mappedBy = "product",
        cascade = arrayOf(CascadeType.ALL),
        fetch = FetchType.EAGER
    )
    @Column(nullable = true)
    @JsonIgnoreProperties("product_car", "product", "cart", "id", "quality")

    var product_car: List<ProductCart> = emptyList(),


    )
