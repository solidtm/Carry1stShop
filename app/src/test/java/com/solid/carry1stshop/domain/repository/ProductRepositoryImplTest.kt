package com.solid.carry1stshop.domain.repository

import assertk.assertions.isEqualTo
import com.solid.carry1stshop.data.model.ProductDto
import com.solid.carry1stshop.data.remote.source.ProductDataSource
import com.solid.carry1stshop.domain.mapper.map
import com.solid.carry1stshop.domain.util.ErrorHelper.handleException
import com.solid.carry1stshop.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import java.io.IOException

class ProductRepositoryImplTest {

    @MockK
    private lateinit var dataSource: ProductDataSource
    private lateinit var repository: ProductRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        repository = ProductRepositoryImpl(dataSource)
    }

    @Test
    fun `test getProducts returns success`() = runTest {
        val productList = listOf(
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
        coEvery { dataSource.getProducts() } returns productList

        val result = repository.getProducts().first()

        assertk.assertThat((result as Result.Success).data).isEqualTo(productList.map())
        coVerify { dataSource.getProducts() }
    }


    @Test
    fun `test getProducts returns network error`() = runTest {
        coEvery { dataSource.getProducts() } throws IOException()

        val result = repository.getProducts().first()

        assertk.assertThat((result as Result.Error).message).isEqualTo(handleException(IOException()))
        coVerify { dataSource.getProducts() }
    }

    @Test
    fun `test getProducts returns http error`() = runTest {
        coEvery { dataSource.getProducts() } throws HttpException(mockk(relaxed = true))

        val result = repository.getProducts().first()

        assertk.assertThat((result as Result.Error).message).isEqualTo(handleException(HttpException(mockk(relaxed = true))))
        coVerify { dataSource.getProducts() }
    }

    @Test
    fun `test getProducts returns generic error`() = runTest {
        coEvery { dataSource.getProducts() } throws Exception()

        val result = repository.getProducts().first()

        assertk.assertThat((result as Result.Error).message).isEqualTo(handleException(Exception()))
        coVerify { dataSource.getProducts() }
    }
}
