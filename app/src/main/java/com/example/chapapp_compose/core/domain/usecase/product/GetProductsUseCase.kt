package com.example.chapapp_compose.core.domain.usecase.product

import com.example.chapapp_compose.core.data.model.ProductResponse
import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import com.example.chapapp_compose.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Unit, Flow<ProductResponse>>() {
    override fun execute(param: Unit) : Flow<ProductResponse> {
        return productRepository.getProductsApiCall()
    }
}