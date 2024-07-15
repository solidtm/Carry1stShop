package com.solid.carry1stshop.domain.di

import com.solid.carry1stshop.BuildConfig
import com.solid.carry1stshop.data.remote.api.ProductService
import com.solid.carry1stshop.data.remote.api.ProductServiceFactory
import com.solid.carry1stshop.data.remote.source.ProductDataSource
import com.solid.carry1stshop.data.remote.source.ProductDataSourceImpl
import com.solid.carry1stshop.domain.repository.ProductRepository
import com.solid.carry1stshop.domain.repository.ProductRepositoryImpl
import com.solid.carry1stshop.features.products.viewmodel.DataPersistenceViewModel
import com.solid.carry1stshop.features.products.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<ProductService> {
        ProductServiceFactory.createApiService(BuildConfig.DEBUG)
    }
    single<ProductDataSource>{
        ProductDataSourceImpl(get())
    }
    single<ProductRepository>{
        ProductRepositoryImpl(get())
    }
    single{
        DataPersistenceViewModel()
    }
    viewModel{
        ProductViewModel(get())
    }
}