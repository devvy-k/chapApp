package com.example.chapapp_compose.features_ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.domain.usecase.product.GetProductByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
) : ViewModel(){
    private val _uiStateProduct: MutableStateFlow<UiState<Product>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct : StateFlow<UiState<Product>> = _uiStateProduct

    fun getProductByApiCall(id: Int){
        getProductByIdUseCase.execute(id).onEach {
            _uiStateProduct.value = UiState.Success(it)
        }.catch { e ->
            _uiStateProduct.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

}