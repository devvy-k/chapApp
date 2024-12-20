package com.example.chapapp_compose.core.data.datasource.remote

import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductResponse

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String
    ): ProductResponse

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ): Product

}