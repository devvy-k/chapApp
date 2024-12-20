package com.example.chapapp_compose.features_ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.features_ui.component.ProgressProduct
import com.example.chapapp_compose.features_ui.detail.section.DetailContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    productId : Int,
    viewModel: DetailViewModel = hiltViewModel(),
    navigateBack : () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Detail Product")
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }){
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Icon",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                )
            )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = Color.LightGray)
            ){
                viewModel.uiStateProduct.collectAsState(initial = UiState.Loading).value.let{ uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getProductByApiCall(productId)
                            ProgressProduct()
                        }

                        is UiState.Success -> {
                            DetailContent(product = uiState.data, viewModel = viewModel)
                        }

                        is UiState.Error -> {
                            Text(text = "Chargement du produit echoué, reessayez", color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        }
    )
}