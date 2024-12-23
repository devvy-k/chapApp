package com.example.chapapp_compose.core.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity

@Dao
interface ProductDao {
    @Query("SELECT * FROM table_shop")
    fun getProducts() : MutableList<ProductEntity>

    @Query("SELECT * FROM table_shop WHERE id = :id")
    fun getProductById(id: Long) : ProductEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductEntity): Long

    @Delete
    suspend fun deleteProduct(product: ProductEntity): Int
}