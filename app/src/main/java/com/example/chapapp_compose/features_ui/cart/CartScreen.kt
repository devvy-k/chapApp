package com.example.chapapp_compose.features_ui.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.features_ui.cart.section.CartContent
import com.example.chapapp_compose.features_ui.component.ProgressProduct

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Panier")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
            )
        }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Gray)
                    .padding(it)
            ) {
                viewModel.uiStateDbProducts.collectAsState(initial = UiState.Loading).value.let { uiState ->
                    when (uiState) {
                        is UiState.Loading -> {
                            viewModel.getProductsDb()
                            ProgressProduct()
                        }

                        is UiState.Success -> {
                            CartContent(products = uiState.data, viewModel = viewModel, navigateToDetail = navigateToDetail)
                        }

                        is UiState.Error -> {
                            Text(text = "Chargement des produits échoué", color = MaterialTheme.colorScheme.onSurface)
                        }
                    }
                }
            }
        })

}