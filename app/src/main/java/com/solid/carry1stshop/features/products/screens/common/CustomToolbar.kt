package com.solid.carry1stshop.features.products.screens.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solid.carry1stshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar(
    title: String,
    showBackButton: Boolean = false,
    showAction: Boolean = false,
    onBackClicked: () -> Unit = {},
    onActionClicked: () -> Unit = {}
) {
    TopAppBar(
        actions = {
            if(showAction){
                IconButton(onClick = { onActionClicked() }, modifier = Modifier.padding(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = stringResource(id = R.string.refresh)
                    )
                }
            }
        },
        navigationIcon = {
            if(showBackButton){
                IconButton(onClick = { onBackClicked() }, modifier = Modifier.padding(8.dp)) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.back)
                    )
                }
            }
        },
        title = {
            Text(
                modifier = Modifier.padding(horizontal = 16.dp),
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
    )
}