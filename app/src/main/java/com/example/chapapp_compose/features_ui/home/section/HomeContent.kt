package com.example.chapapp_compose.features_ui.home.section

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.features_ui.component.EmptyProduct
import com.example.chapapp_compose.features_ui.component.ProductItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    modifier: Modifier,
    listProduct: MutableList<Product>?,
    navigateToDetails: (Int) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        if (listProduct != null ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                content = {
                    items(listProduct, key = { it.id ?: -1}) { product ->
                        modifier
                            .fillMaxSize()
                        ProductItem(
                            product = product,
                            modifier = Modifier.animateItem(
                                fadeInSpec = null,
                                fadeOutSpec = null,
                                placementSpec = tween(durationMillis = 100)
                            )
                                .clickable {
                                    navigateToDetails(product.id ?: return@clickable)
                                }
                        )
                    }
                },
                contentPadding = PaddingValues(8.dp)
            )
            if (listProduct.isEmpty()) EmptyProduct()
        } else EmptyProduct()
    }
}