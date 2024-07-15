package com.solid.carry1stshop.data.model

import com.squareup.moshi.JsonClass

object ProductDto {
    data class Product(
        val id: Int,
        val name: String,
        val description: String,
        val price: Int,
        val currencyCode: String,
        val currencySymbol: String,
        val quantity: Int,
        val imageLocation: String,
        val status: String
    )

    data class GenericResponse(
        val success: Boolean?,
        val statusCode: Int?,
        val message: String?,
    )

}