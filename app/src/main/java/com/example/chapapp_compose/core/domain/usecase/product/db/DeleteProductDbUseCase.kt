package com.example.chapapp_compose.core.domain.usecase.product.db

import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity
import com.example.chapapp_compose.core.domain.repository.product.DbProductRepository
import com.example.chapapp_compose.core.domain.usecase.BaseUseCaseSuspend
import javax.inject.Inject

class DeleteProductDbUseCase @Inject constructor(
    private val dbProductRepository: DbProductRepository
) : BaseUseCaseSuspend<ProductEntity, Int>() {
    override suspend fun execute(params: ProductEntity): Int {
        return dbProductRepository.deleteProductDb(params)
    }
}