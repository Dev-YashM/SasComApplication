package com.example.sascomapplication.data.mapper

import com.example.sascomapplication.data.remote.dto.ProductDto
import com.example.sascomapplication.domain.model.Product

fun ProductDto.toDomain(): Product {
    return Product(
        id = id ?: -1,
        title = title ?: "No Title",
        description = description ?: "No Description",
        price = price ?: 0.0,
        discountPercentage = discountPercentage ?: 0.0,
        rating = rating ?: 0.0,
        stock = stock ?: 0,
        brand = brand ?: "Unknown",
        category = category ?: "Uncategorized",
        thumbnail = thumbnail ?: "",
        images = images ?: emptyList()
    )
}

