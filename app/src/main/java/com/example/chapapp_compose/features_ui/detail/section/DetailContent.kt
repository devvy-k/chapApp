package com.example.chapapp_compose.features_ui.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.util.Dimens
import com.example.chapapp_compose.features_ui.detail.DetailViewModel

@Composable
fun DetailContent(
    product : Product,
    viewModel: DetailViewModel,
) {
    val context = LocalContext.current
    val strBuy = "BUY"
    val strAddedToCart = "ADD TO CART"
    val strThanks = "Thanks for your purchase!"
    val productId = product.id?.toLong() ?: -1
    var buyText by remember { mutableStateOf(strBuy) }
    var isAlreadyOnCart by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .background(color = Color.White)
    ) {
        item{
            ImageProductPager(product = product)
            Spacer(modifier = Modifier.size(Dimens.dp8))
            TitleProduct(product = product)
            Divider(color = Color.LightGray, thickness = 10.dp)
            DescriptionProduct(product = product)
        }
    }
    }
