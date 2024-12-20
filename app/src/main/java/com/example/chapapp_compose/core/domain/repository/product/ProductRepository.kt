package com.example.chapapp_compose.core.domain.repository.product

import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.data.model.ProductResponse
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsApiCall(): Flow<ProductResponse>
    suspend fun searchProductApiCall(query: String): Flow<ProductResponse>
    fun getProductByIdApiCall(id: Int): Flow<Product>
}