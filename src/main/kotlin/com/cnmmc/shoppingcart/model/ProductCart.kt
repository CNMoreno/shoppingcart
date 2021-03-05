package com.cnmmc.shoppingcart.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "product_car")
data class ProductCart(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var quality: Long = 0,

    //Relacion tabla PRODUCTOS
    @ManyToOne(
        cascade = arrayOf(CascadeType.ALL),
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("product_car")
    var product: Product? = null,

    //Relacion tabla CARTS
    @ManyToOne(
        cascade = arrayOf(CascadeType.ALL),
        fetch = FetchType.EAGER
    )
    @JoinColumn(name = "cart_id")
    @JsonIgnoreProperties("product_car")
    var cart: Cart? = null,

    )