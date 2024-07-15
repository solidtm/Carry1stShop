package com.solid.carry1stshop.features

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.solid.carry1stshop.navigation.NavGraph
import com.solid.carry1stshop.navigation.Screen
import com.solid.carry1stshop.ui.theme.Carry1stShopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Carry1stShopTheme {
                NavGraph(startDestination = Screen.ProductList.route)
            }
        }
    }
}