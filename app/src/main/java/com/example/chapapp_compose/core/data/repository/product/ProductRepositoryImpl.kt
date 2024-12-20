package com.example.chapapp_compose.core.data.repository.product

import com.example.chapapp_compose.core.data.datasource.remote.ApiService
import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.data.model.ProductResponse
import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductRepository {
    override fun getProductsApiCall(): Flow<ProductResponse> { // this is sample not using `suspend`
        return flow {
            emit(apiService.getProducts())
        }
    }

    override fun getProductByIdApiCall(id: Int): Flow<Product> {
        return flow {
            emit(apiService.getProductById(id))
        }
    }

    override suspend fun searchProductApiCall(query: String): Flow<ProductResponse> {
        return flowOf(apiService.searchProduct(query))
    }
}