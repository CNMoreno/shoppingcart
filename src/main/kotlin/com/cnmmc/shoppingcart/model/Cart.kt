package com.cnmmc.shoppingcart.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "cart")
data class Cart(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    val status: String = "",

    @OneToMany(
        mappedBy = "product",
        cascade = arrayOf(CascadeType.ALL),
        fetch = FetchType.EAGER
    )
    @Column(nullable = true)
    @JsonIgnoreProperties("product_car", "product", "cart", "id", "quality")

    var product_car: List<ProductCart> = emptyList(),
){
    override fun toString(): String {
        return "Cart(id=$id, status='$status', product_car=$product_car)"
    }
}




