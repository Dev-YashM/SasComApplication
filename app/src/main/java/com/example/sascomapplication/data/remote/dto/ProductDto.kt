package com.example.sascomapplication.data.remote.dto

data class ProductDto(
    val id: Int?,
    val title: String?,
    val description: String?,
    val price: Double?,
    val discountPercentage: Double?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,           // <-- Made nullable
    val category: String?,
    val thumbnail: String?,
    val images: List<String>?
)

