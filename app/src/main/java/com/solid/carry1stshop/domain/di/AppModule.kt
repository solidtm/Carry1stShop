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

/**
 * Module definition for dependency injection.
 *
 * This module sets up the necessary dependencies for the product-related
 * components in the application, including services, data sources, repositories,
 * and view models.
 *
 * <p>This configuration uses the Koin library for dependency injection and
 * defines singletons and view models required in the application.</p>
 */
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