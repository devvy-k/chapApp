package com.example.chapapp_compose.core.domain.usecase.product.db

import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity
import com.example.chapapp_compose.core.domain.repository.product.DbProductRepository
import com.example.chapapp_compose.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject

class InsertProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Long>() {
    override suspend fun execute(params: ProductEntity): Long {
        return dbProductRepository.insertProductDb(params)
    }
}