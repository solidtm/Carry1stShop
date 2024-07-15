package com.solid.carry1stshop.data.remote.source

import com.solid.carry1stshop.data.remote.api.ProductService
import com.solid.carry1stshop.data.model.ProductDto

class ProductDataSourceImpl(
    private val service: ProductService
): ProductDataSource {
    override suspend fun getProducts(): List<ProductDto.Product>  = service.getProducts()
}