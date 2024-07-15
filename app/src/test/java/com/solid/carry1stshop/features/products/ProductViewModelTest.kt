package com.solid.carry1stshop.features.products

import assertk.assertions.isEqualTo
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.domain.repository.ProductRepository
import com.solid.carry1stshop.features.products.viewmodel.ProductViewModel
import com.solid.carry1stshop.domain.util.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ProductViewModelTest {
    @MockK
    private lateinit var repository: ProductRepository
    private lateinit var viewModel: ProductViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test products are fetched correctly`() = runTest {
        val productList = listOf(
            ProductDomain.Product(
                id = 11,
                name = "10 Lives",
                description = "10 Lives product bundle.",
                price = 1,
                currencySymbol = "$",
                logo = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/74e517a3-0615-4019-bb08-cc697cf4e747.png",
            )
        )

        // Mock repo, return expected flow
        coEvery { repository.getProducts() } returns flowOf(Result.Success(productList))

        viewModel = ProductViewModel(repository)

        // Advance coroutine dispatcher to complete all coroutines
        advanceUntilIdle()

        // Assert fetched product is correct
        assertk.assertThat(viewModel.products.value.data?.size).isEqualTo(Result.Success(productList).data?.size)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }
}