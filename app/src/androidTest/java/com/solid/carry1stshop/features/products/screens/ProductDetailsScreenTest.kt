package com.solid.carry1stshop.features.products.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.features.products.viewmodel.DataPersistenceViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProductDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var dataViewModel: DataPersistenceViewModel
    private lateinit var navController: TestNavHostController

    private val product = ProductDomain.Product(
        id = 11,
        logo = "https://dev-images-carry1st-products.s3.eu-west-2.amazonaws.com/74e517a3-0615-4019-bb08-cc697cf4e747.png",
        name = "10 Lives",
        price = 1,
        currencySymbol = "$",
        description = "10 Lives product bundle."
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        dataViewModel = mockk(relaxed = true)

        // Mock the product in the view model using MutableStateFlow
        val productStateFlow = MutableStateFlow(product)
        every { dataViewModel.product } returns productStateFlow.asStateFlow()

        composeTestRule.setContent {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            navController = TestNavHostController(context)
            navController.setViewModelStore(ViewModelStore())
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            ProductDetailScreen(viewModel = dataViewModel, navController = navController)
        }
    }

    @Test
    fun testProductDetailsDisplayed() {
        // Verify product details display
        composeTestRule.onNodeWithText("$1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ten Lives product bundle.").assertIsDisplayed()
    }
}
