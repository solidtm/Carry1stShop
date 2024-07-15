package com.solid.carry1stshop.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.solid.carry1stshop.features.products.screens.ProductDetailScreen
import com.solid.carry1stshop.features.products.screens.ProductListScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.ProductList.route) { ProductListScreen(navController = navController) }
        composable(route = Screen.ProductDetails.route) { ProductDetailScreen(navController = navController) }
    }
}
