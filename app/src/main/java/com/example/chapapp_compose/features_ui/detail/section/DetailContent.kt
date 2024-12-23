package com.example.chapapp_compose.features_ui.detail.section

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.chapapp_compose.core.data.UiState
import com.example.chapapp_compose.core.data.model.Product
import com.example.chapapp_compose.core.util.Dimens
import com.example.chapapp_compose.core.util.UtilFunctions.logE
import com.example.chapapp_compose.features_ui.detail.DetailViewModel
import com.example.chapapp_compose.features_ui.util.Extensions.myToast

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

    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomStart
    ){
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            viewModel.uiStateDbProduct.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getProductByIdDb(productId)
                    }

                    is UiState.Success -> {
                        isAlreadyOnCart = true
                    }

                    is UiState.Error -> {
                        logE(uiState.errorMessage)
                    }
                }
            }
            if (!isAlreadyOnCart) {
                Text(
                    text = "ADD TO CART",
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondary)
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(vertical = 20.dp)
                        .clickable {
                            context.myToast(strAddedToCart)
                            viewModel.insertProductDb(product)
                        },
                    textAlign = TextAlign.Center,
                    color = Color.White,
                )
            }
            Text(
                text = buyText,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 20.dp)
                    .clickable {
                        context.myToast(strThanks)
                        buyText = strThanks
                    },
                color = Color.White,
                textAlign = TextAlign.Center,
            )
        }
    }
    }
