package com.solid.carry1stshop.domain.repository

import com.solid.carry1stshop.domain.mapper.map
import com.solid.carry1stshop.domain.util.Result
import com.solid.carry1stshop.data.remote.source.ProductDataSource
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.domain.util.ErrorHelper
import com.solid.carry1stshop.domain.util.ErrorHelper.handleException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

/**
 * Implementation of the ProductRepository interface that fetches product data from a data source.
 *
 * @property source The data source from which to fetch the products.
 */
class ProductRepositoryImpl(
    private val source: ProductDataSource,
) : ProductRepository {

    /**
     * Fetches a list of products from the data source and emits the result as a Flow.
     *
     * @return A Flow that emits the loading state, the list of products on success, or an error message on failure.
     */
    override suspend fun getProducts(): Flow<Result<List<ProductDomain.Product>>> {
        return flow {
            emit(Result.Loading())

            val products = try {
                source.getProducts()
            } catch (e: IOException) {
                emit(Result.Error(message = handleException(e)))
                return@flow
            } catch (e: HttpException) {
                emit(Result.Error(message = handleException(e)))
                return@flow
            } catch (e: Exception) {
                emit(Result.Error(message = handleException(e)))
                return@flow
            }
            emit(Result.Success(products.map()))
        }
    }
}
