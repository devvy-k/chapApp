package com.example.chapapp_compose.core.data.model

import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity

object ProductMapper {
    fun mapFromProductToEntity(product: Product) = ProductEntity(
        product.id, product.description, product.price, product.thumbnail, product.title
    )
}