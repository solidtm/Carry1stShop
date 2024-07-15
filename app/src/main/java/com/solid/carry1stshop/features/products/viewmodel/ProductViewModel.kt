package com.solid.carry1stshop.features.products.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.domain.repository.ProductRepository
import com.solid.carry1stshop.domain.util.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
): ViewModel() {

    private val _products = MutableStateFlow<Result<List<ProductDomain.Product>>>(Result.Loading())
    val products = _products.asStateFlow()

    init {
       fetchProducts()
    }

    fun fetchProducts(){
        println("ProductViewModel fetchProducts called")
        viewModelScope.launch {
            repository.getProducts().collectLatest { result ->
                when(result) {
                    is Result.Error -> {
                        _products.update { Result.Error(message = result.message ?: "An error occurred, please try again.") }
                    }
                    is Result.Success -> {
                        _products.update { result }
                    }
                    is Result.Loading -> {_products.update { Result.Loading() }}
                }
            }
        }
    }
}