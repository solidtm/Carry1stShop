package com.solid.carry1stshop.features.products.screens.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import com.solid.carry1stshop.R

@Composable
fun ErrorDialog(message: String, shouldShowDialog: MutableState<Boolean>) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog.value = false },
            title = { Text(text = stringResource(id = R.string.error)) },
            text = { Text(text = message) },
            confirmButton = {
                Button(onClick = { shouldShowDialog.value = false }) {
                    Text("OK")
                }
            }

        )
    }
}