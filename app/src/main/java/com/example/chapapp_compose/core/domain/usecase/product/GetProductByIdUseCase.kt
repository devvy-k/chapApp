package com.example.chapapp_compose.core.domain.usecase.product

import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.domain.repository.product.ProductRepository
import com.example.chapapp_compose.core.domain.usecase.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : BaseUseCase<Int, Flow<Product>>(){
    override fun execute(param: Int): Flow<Product> {
        return productRepository.getProductByIdApiCall(param)
    }
}