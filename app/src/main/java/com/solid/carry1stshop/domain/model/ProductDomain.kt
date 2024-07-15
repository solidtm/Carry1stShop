package com.solid.carry1stshop.domain.model

object ProductDomain {
    data class Product(
        val id: Int,
        val logo: String,
        val name: String,
        val price: Int,
        val currencySymbol: String,
        val description: String,
    )
}