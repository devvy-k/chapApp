package com.example.chapapp_compose.features_ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.model.ProductResponse
import com.example.chapapp_compose.core.domain.usecase.product.GetProductsUseCase
import com.example.chapapp_compose.core.domain.usecase.product.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val searchProductsUseCase: SearchProductUseCase
) : ViewModel() {

    private val _uiStateProduct : MutableStateFlow<UiState<ProductResponse>> = MutableStateFlow(UiState.Loading)
    val uiStateProduct: StateFlow<UiState<ProductResponse>> = _uiStateProduct

    private val _query = mutableStateOf("")
    val query : State<String> get() = _query

    fun getProductsApiCall(){
        getProductsUseCase.execute(Unit).onEach {
            products -> _uiStateProduct.value = UiState.Success(products)
        }.catch {
            e ->
            Log.e("HomeViewModel", "getProductsApiCall: ${e.message}")
            _uiStateProduct.value = UiState.Error(e.message.toString())
        }.launchIn(viewModelScope)
    }

    fun searchProductApiCall(query: String){
        _query.value = query
        viewModelScope.launch {
            try {
                searchProductsUseCase.execute(_query.value)
                    .catch{
                        _uiStateProduct.value = UiState.Error(it.message.toString())
                    }
                    .collect {
                        products -> _uiStateProduct.value = UiState.Success(products)
                    }
            } catch (e : Exception){
                _uiStateProduct.value = UiState.Error(e.message.toString())
            }
        }
    }
}