package com.solid.carry1stshop.features.products.viewmodel

import androidx.lifecycle.ViewModel
import com.solid.carry1stshop.domain.model.ProductDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DataPersistenceViewModel: ViewModel() {
    private val _product = MutableStateFlow<ProductDomain.Product?>(null)
    val product = _product.asStateFlow()

    fun setProduct(product: ProductDomain.Product){
        _product.value = product
    }
}