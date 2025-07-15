package com.example.sascomapplication.data.remote.api
import com.example.sascomapplication.data.remote.dto.ProductDto
import com.example.sascomapplication.data.remote.dto.ProductListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): ProductDto

    @GET("products")
    suspend fun getAllProducts(): ProductListDto

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String
    ): ProductListDto

    @GET("products/search")
    suspend fun searchProducts(
        @Query("q") query: String
    ): ProductListDto
}
