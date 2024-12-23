package com.example.chapapp_compose.features_ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.datasource.local.db.entity.ProductEntity
import com.example.chapapp_compose.core.domain.usecase.product.GetProductsUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.DeleteProductDbUseCase
import com.example.chapapp_compose.core.domain.usecase.product.db.GetProductsDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getProductsDbUseCase: GetProductsDbUseCase,
    private val deleteProductDbUseCase: DeleteProductDbUseCase
) : ViewModel() {

    private val _uiStateDbProducts : MutableStateFlow<UiState<MutableList<ProductEntity>>> = MutableStateFlow(UiState.Loading)
    val uiStateDbProducts : StateFlow<UiState<MutableList<ProductEntity>>> = _uiStateDbProducts

    fun getProductsDb(dispatcher: CoroutineDispatcher = Dispatchers.Default){
        viewModelScope.launch(dispatcher) {
            try {
                getProductsDbUseCase.execute(Unit).catch{
                    _uiStateDbProducts.value = UiState.Error(it.message.toString())
                }.collect{ products ->
                    _uiStateDbProducts.value = UiState.Success(products)
                }
            } catch (e: Exception) {
                _uiStateDbProducts.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun deleteProductDb(product: ProductEntity){
        viewModelScope.launch {
            val intDelete = deleteProductDbUseCase.execute(product)
            if (intDelete == 1) getProductsDb()
        }
    }
}