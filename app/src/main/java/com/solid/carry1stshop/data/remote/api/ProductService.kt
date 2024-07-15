package com.solid.carry1stshop.data.remote.api

import com.solid.carry1stshop.data.model.ProductDto
import retrofit2.http.GET

interface ProductService {
    @GET(HttpRoutes.PRODUCTS)
    suspend fun getProducts(): List<ProductDto.Product>

}