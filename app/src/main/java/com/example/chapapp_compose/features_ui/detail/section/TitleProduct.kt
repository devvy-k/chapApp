package com.example.chapapp_compose.features_ui.detail.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chapapp_compose.core.data.model.Product

@Composable
fun TitleProduct(product: Product) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 8.dp
        )
    ) {
        Text(
            text = product.title ?: "-",
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal, fontSize = 28.sp
            ),
            color = Color.Black
        )
        Text(
            text = product.price.toString(),
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.Light, fontSize = 20.sp
            ),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}