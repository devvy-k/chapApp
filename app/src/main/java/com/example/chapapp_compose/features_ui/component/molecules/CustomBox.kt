package com.example.chapapp_compose.features_ui.component.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

val Blue = Color(0xFF0000FF)
val Tail600 = Color(0xFF57C1BD)
@Composable
fun CustomBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier.background(color = Color.White)) {
        content()
    }
}