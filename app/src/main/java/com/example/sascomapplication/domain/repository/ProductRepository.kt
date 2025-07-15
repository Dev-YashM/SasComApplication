package com.example.sascomapplication.domain.repository

import com.example.sascomapplication.domain.model.Product

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>
    suspend fun getProductsByCategory(category: String): List<Product>
    suspend fun searchProducts(query: String): List<Product>
    suspend fun getProductById(id: Int): Product?
}
