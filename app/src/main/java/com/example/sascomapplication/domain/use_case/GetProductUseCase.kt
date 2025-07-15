package com.example.sascomapplication.domain.use_case

import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend fun getAll(): List<Product> {
        return repository.getAllProducts()
    }

    suspend fun getByCategory(category: String): List<Product> {
        return repository.getProductsByCategory(category)
    }

    suspend fun search(query: String): List<Product> {
        return repository.searchProducts(query)
    }

    suspend fun getById(id: Int): Product? {
        return repository.getProductById(id)
    }
}
