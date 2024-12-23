package com.example.chapapp_compose.core.domain.repository.product

import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface DbProductRepository {
    fun getProductsDb(): Flow<MutableList<ProductEntity>>
    fun getProductByIdDb(id: Long): Flow<ProductEntity>
    suspend fun insertProductDb(product: ProductEntity): Long
    suspend fun deleteProductDb(product: ProductEntity): Int
}