package com.example.chapapp_compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class GeneralScreen(val route : String, title: String, icon: ImageVector) {
    object DetailProduct : BottomBarScreen(route = "HOME/{productId}", title = "", icon = Icons.Default.Home ) {
        fun createRoute(productId: Int) = "HOME/$productId"
    }
    object SearchProduct : GeneralScreen(route = "HOME/search", title = "", icon = Icons.Default.Home)
}