package com.example.chapapp_compose.features_ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.model.ProductResponse
import com.example.chapapp_compose.features_ui.component.ProgressProduct
import com.example.chapapp_compose.features_ui.component.molecules.SearchBar
import com.example.chapapp_compose.features_ui.home.section.HomeContent
import com.example.chapapp_compose.features_ui.template.MainTemplate

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (Int) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    MainTemplate(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "",
                onQueryChange = {},
                modifier = Modifier.background(MaterialTheme.colorScheme.primary),
                isEnabled = false,
                onSearchClicked = { navigateToSearch() }
                )
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Gray)
            ){
                when (uiStateProduct){
                    is UiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        HomeContent(
                            modifier = modifier,
                            listProduct = (uiStateProduct as UiState.Success<ProductResponse>).data.products,
                            navigateToDetails = navigateToDetails,
                        )
                    }

                    is UiState.Error -> {
                        Text(text = "Chargement des données échoué, réessayez", color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    )
}