package com.example.chapapp_compose.core.data.repository.product

import com.example.chapapp_compose.core.data.datasource.local.db.AppDatabase
import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity
import com.example.chapapp_compose.core.domain.repository.product.DbProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbProductRepositoryImpl @Inject constructor(
    private val db: AppDatabase
) : DbProductRepository {

    override fun getProductsDb(): Flow<MutableList<ProductEntity>> {
        return flowOf(db.productDao().getProducts())
    }

    override fun getProductByIdDb(id: Long): Flow<ProductEntity> {
        return flowOf(db.productDao().getProductById(id))
    }

    override suspend fun insertProductDb(product: ProductEntity): Long {
        return db.productDao().insertProduct(product)
    }

    override suspend fun deleteProductDb(product: ProductEntity): Int {
        return db.productDao().deleteProduct(product)
    }
}