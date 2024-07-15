package com.solid.carry1stshop.domain.mapper

import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.data.model.ProductDto

fun List<ProductDto.Product>.map(): List<ProductDomain.Product> {
    val products = map { product ->
        ProductDomain.Product(
            id = product.id,
            name = product.name,
            description = product.description,
            currencySymbol = product.currencySymbol,
            logo = product.imageLocation,
            price = product.price
        )
    }

    return products
}