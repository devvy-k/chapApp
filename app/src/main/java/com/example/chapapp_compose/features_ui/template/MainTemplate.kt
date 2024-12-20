package com.example.chapapp_compose.features_ui.template

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainTemplate(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    //bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
    ) {
    Scaffold(
        topBar = {
            topBar()
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        innerPadding ->
        Surface(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content(innerPadding)
        }
    }
}