package com.solid.carry1stshop.data.remote

import com.solid.carry1stshop.BuildConfig
import com.solid.carry1stshop.data.model.ProductDto
import com.solid.carry1stshop.data.remote.api.HttpRoutes
import com.solid.carry1stshop.data.remote.api.ProductService
import com.solid.carry1stshop.data.remote.api.ProductServiceFactory
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ProductServiceTest {

    private lateinit var productService: ProductService
    private val mockProductList = listOf(
        ProductDto.Product(
            id = 11,
            name = "10 Lives",
            description = "10 Lives product bundle.",
            price = 1,
            currencyCode = "USD",
            currencySymbol = "$",
            quantity = 10,
            imageLocation = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/74e517a3-0615-4019-bb08-cc697cf4e747.png",
            status = "ACTIVE"
        )
    )

    @BeforeEach
    fun setUp() {
        productService = mockk()
        coEvery { productService.getProducts() } returns mockProductList
    }

    @Test
    fun `test getProducts returns expected data`() = runTest {
        val result = productService.getProducts()

        assertEquals(mockProductList, result)
    }

    @Test
    fun `test createApiService creates the correct service`() {
        val isDebug = true
        val okHttpClient: OkHttpClient = ProductServiceFactory.makeOkHttpClient(
            ProductServiceFactory.makeLoggingInterceptor(isDebug)
        )
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val createdService = retrofit.create(ProductService::class.java)

        assert(createdService is ProductService)
    }
}