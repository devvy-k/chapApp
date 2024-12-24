package com.example.chapapp_compose.navigation.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chapapp_compose.navigation.BottomBarScreen
import com.example.chapapp_compose.navigation.GeneralScreen
import com.example.chapapp_compose.features_ui.ScreenContent
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import com.example.chapapp_compose.features_ui.cart.CartScreen
import com.example.chapapp_compose.features_ui.detail.DetailScreen
import com.example.chapapp_compose.features_ui.home.HomeScreen
import com.example.chapapp_compose.features_ui.profile.ProfileScreen
import com.example.chapapp_compose.features_ui.search.SearchScreen


@Composable
fun MainNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut: () -> Unit
) {
    NavHost(
        modifier = Modifier.padding(innerPadding).consumeWindowInsets(innerPadding),
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        route = Graph.MAIN
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(
                navigateToDetails = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateToSearch = { navController.navigate(GeneralScreen.SearchProduct.route) }
            )
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = onSignOut,
                onGoBack = {}
            )
        }
        composable(BottomBarScreen.Cart.route) {
            CartScreen(
                navigateToDetail = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                }
            )
        }
        composable(route = BottomBarScreen.Settings.route){
            ScreenContent(
                name = BottomBarScreen.Settings.route,
                onClick = {
                })
        }

        composable(
            route = GeneralScreen.DetailProduct.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ){
            val id = it.arguments?.getInt("productId") ?: -1
            DetailScreen(
                productId = id,
                navigateBack = { navController.navigateUp() }
            )
        }

        composable(route = GeneralScreen.SearchProduct.route){
            SearchScreen(
                navigateToDetails = { productId ->
                    navController.navigate(GeneralScreen.DetailProduct.createRoute(productId))
                },
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }

    }
}