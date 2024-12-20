package com.example.chapapp_compose.features_ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.model.ProductResponse
import com.example.chapapp_compose.features_ui.component.ProgressProduct
import com.example.chapapp_compose.features_ui.component.molecules.SearchBar
import com.example.chapapp_compose.features_ui.home.HomeViewModel
import com.example.chapapp_compose.features_ui.search.section.SearchContent
import com.example.chapapp_compose.features_ui.template.MainTemplate

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel : HomeViewModel = hiltViewModel(),
    navigateToDetails: (Int) -> Unit,
    navigateBack : () -> Unit
) {
    val query by viewModel.query
    val focusRequester = remember { FocusRequester() }
    val uiStateProduct by remember { viewModel.uiStateProduct }.collectAsState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    MainTemplate (
        modifier = modifier,
        topBar = {
            Row(
                modifier = modifier.background(MaterialTheme.colorScheme.primary),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = navigateBack,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Retour"
                    )
                }
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::searchProductApiCall,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .focusRequester(focusRequester),
                )
            }
        },
        content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                when (uiStateProduct) {
                    is UiState.Loading -> {
                        viewModel.getProductsApiCall()
                        ProgressProduct()
                    }

                    is UiState.Success -> {
                        SearchContent(
                            modifier = modifier,
                            listProduct = (uiStateProduct as UiState.Success<ProductResponse>).data.products,
                            navigateToDetails = navigateToDetails,
                        )
                    }

                    is UiState.Error -> {
                        Text(text = "Chargement du produit echoué, reessayez plus tard", color = MaterialTheme.colorScheme.onSurface)
                    }
                }
            }
        }
    )
}