package com.solid.carry1stshop.navigation

sealed class Screen(val route: String) {
    data object ProductList: Screen(route = "product_list")
    data object ProductDetails: Screen(route = "product_details")
}