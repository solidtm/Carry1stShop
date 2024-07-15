package com.solid.carry1stshop.domain.repository

import com.solid.carry1stshop.domain.model.ProductDomain
import kotlinx.coroutines.flow.Flow
import com.solid.carry1stshop.domain.util.Result

interface ProductRepository {
    suspend fun getProducts() : Flow<Result<List<ProductDomain.Product>>>
}