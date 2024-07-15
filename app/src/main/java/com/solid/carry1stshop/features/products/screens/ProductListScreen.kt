package com.solid.carry1stshop.features.products.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.solid.carry1stshop.R
import com.solid.carry1stshop.domain.model.ProductDomain
import com.solid.carry1stshop.features.products.screens.common.CustomToolbar
import com.solid.carry1stshop.features.products.screens.common.ErrorDialog
import com.solid.carry1stshop.features.products.screens.common.ModularScreen
import com.solid.carry1stshop.features.products.viewmodel.DataPersistenceViewModel
import com.solid.carry1stshop.features.products.viewmodel.ProductViewModel
import com.solid.carry1stshop.navigation.Screen
import com.solid.carry1stshop.domain.util.Result
import com.solid.carry1stshop.ui.theme.shapes
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductListScreen(
    productViewModel: ProductViewModel = getViewModel(),
    dataViewModel: DataPersistenceViewModel = getViewModel(),
    navController: NavController
) {
    val result = productViewModel.products.collectAsState().value
    val shouldShowDialog = rememberSaveable { mutableStateOf(true) }
    val displayEmptyList = rememberSaveable { mutableStateOf(false) }

    ModularScreen(
        headerView = {
            CustomToolbar(
                title = stringResource(id = R.string.product),
                showAction = true,
                onActionClicked = { productViewModel.fetchProducts() }
            )
        },
        contentView = {
            when (result) {
                is Result.Loading -> { ProgressLoader() }
                is Result.Error -> {
                    shouldShowDialog.value = true
                    displayEmptyList.value = true
                    ErrorDialog(message = result.message ?: stringResource(id = R.string.error_occurred), shouldShowDialog = shouldShowDialog)
                }
                is Result.Success -> {
                    displayEmptyList.value = result.data?.isEmpty() == true
                    ProductList(navController, result.data ?: emptyList(), dataViewModel)
                }
            }

            if(displayEmptyList.value) EmptyListMessage()
        }
    )
}

@Composable
private fun ProgressLoader() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyListMessage() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = stringResource(id = R.string.no_products))
    }
}

@Composable
private fun ProductList(
    navController: NavController,
    products: List<ProductDomain.Product>,
    dataViewModel: DataPersistenceViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(items = products, key = { item -> item.id }) { item ->
            Product(item) {
                dataViewModel.setProduct(item)
                navController.navigate(Screen.ProductDetails.route)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Product(
    product: ProductDomain.Product,
    onClick: () -> Unit
) {
    val imageState = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current).data(product.logo)
            .size(Size.ORIGINAL).build()
    ).state
    Column(
        modifier = Modifier
            .clip(shapes.medium)
            .height(300.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(6.dp))

        if (imageState is AsyncImagePainter.State.Error) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape),
                painter = imageState.painter,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = product.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Price: ${product.currencySymbol}${product.price}",
            fontSize = 17.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

