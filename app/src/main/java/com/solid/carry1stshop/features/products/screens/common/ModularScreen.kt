package com.solid.carry1stshop.features.products.screens.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ModularScreen(
    headerView: @Composable (Modifier) -> Unit,
    contentView: @Composable (Modifier) -> Unit
) {
    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            headerView(Modifier)
            contentView(Modifier)
        }
    }
}