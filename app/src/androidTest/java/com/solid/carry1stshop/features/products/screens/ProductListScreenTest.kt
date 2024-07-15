package com.solid.carry1stshop.features.products.screens

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelStore
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.domain.util.Result
import com.solid.carry1stshop.features.products.viewmodel.DataPersistenceViewModel
import com.solid.carry1stshop.features.products.viewmodel.ProductViewModel
import com.solid.carry1stshop.navigation.NavGraph
import com.solid.carry1stshop.navigation.Screen
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
class ProductListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var productViewModel: ProductViewModel
    private lateinit var dataViewModel: DataPersistenceViewModel
    private lateinit var navController: TestNavHostController
    val product = ProductDomain.Product(
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
        productViewModel = mockk(relaxed = true)
        dataViewModel = mockk(relaxed = true)

        // Mock the products in the view model
        val productsStateFlow = MutableStateFlow<Result<List<ProductDomain.Product>>>(
            Result.Success(listOf(product))
        )
        every { productViewModel.products } returns productsStateFlow.asStateFlow()

        composeTestRule.setContent {
            val context = InstrumentationRegistry.getInstrumentation().targetContext
            navController = TestNavHostController(context)
            navController.setViewModelStore(ViewModelStore())
            navController.navigatorProvider.addNavigator(ComposeNavigator())

            NavGraph(navController = navController, startDestination = Screen.ProductList.route)
            ProductListScreen(productViewModel, dataViewModel, navController)
        }
    }
    @Test
    fun testClickOnProductNavigatesToDetails() {
        // Click product item
        composeTestRule.onNodeWithText("10 Lives").performClick()

        // Verify navigation to details screen
        assertThat(navController.currentDestination?.route).isEqualTo(Screen.ProductDetails.route)
    }
}
