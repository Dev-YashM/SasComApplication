package com.example.sascomapplication.data.local.repository

import com.example.sascomapplication.data.remote.api.ProductApi
import com.example.sascomapplication.domain.model.Product
import com.example.sascomapplication.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.example.sascomapplication.data.mapper.toDomain
import retrofit2.http.GET


class ProductRepositoryImpl(
    private val api: ProductApi
) : ProductRepository {



    override suspend fun getAllProducts(): List<Product> = withContext(Dispatchers.IO) {
        api.getAllProducts().products.map { it.toDomain() }
    }

    override suspend fun getProductsByCategory(category: String): List<Product> = withContext(Dispatchers.IO) {
        api.getProductsByCategory(category).products.map { it.toDomain() }
    }

    override suspend fun searchProducts(query: String): List<Product> = withContext(Dispatchers.IO) {
        api.searchProducts(query).products.map { it.toDomain() }
    }

    override suspend fun getProductById(id: Int): Product = withContext(Dispatchers.IO) {
        api.getProductById(id).toDomain()
    }
}
