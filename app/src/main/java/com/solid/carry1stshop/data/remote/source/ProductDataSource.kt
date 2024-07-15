package com.solid.carry1stshop.data.remote.source

import com.solid.carry1stshop.data.model.ProductDto

interface ProductDataSource {
    suspend fun getProducts() : List<ProductDto.Product>
}