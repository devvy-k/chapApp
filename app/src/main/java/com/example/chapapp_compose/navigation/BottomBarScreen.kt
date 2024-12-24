package com.example.chapapp_compose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "STORE",
        icon = Icons.Default.Home
    )
    object Cart : BottomBarScreen(
        route = "CART",
        title = "PANIER",
        icon = Icons.Default.ShoppingCart
    )
    object Profile : BottomBarScreen(
        route = "PROFILE",
        title = "PROFILE",
        icon = Icons.Default.Person
    )
    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "PARAMETRE",
        icon = Icons.Default.Settings
    )
}