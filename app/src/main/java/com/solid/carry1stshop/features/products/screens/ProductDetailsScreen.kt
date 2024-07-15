package com.solid.carry1stshop.features.products.screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.solid.carry1stshop.R
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.features.products.screens.common.CustomToolbar
import com.solid.carry1stshop.features.products.screens.common.ModularScreen
import com.solid.carry1stshop.features.products.viewmodel.DataPersistenceViewModel

@Composable
fun ProductDetailScreen(
    viewModel: DataPersistenceViewModel = getViewModel(),
    navController: NavController
) {
    val product = viewModel.product.collectAsState().value

    product?.let {
        ModularScreen(
            headerView = {
                CustomToolbar(
                    title = product.name,
                    showBackButton = true,
                    onBackClicked = { navController.popBackStack() }
                )
            },
            contentView = {
                Details(product)
            }
        )
    }
}

@Composable
private fun Details(product: ProductDomain.Product) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = product.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Text(text = "${product.currencySymbol}${product.price}", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.description, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = { /* Add to cart */ }) {
                Text(text = stringResource(id = R.string.add_to_cart))
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = { /* Buy now */ }) {
                Text(text = stringResource(id = R.string.buy_now))
            }
        }
    }
}