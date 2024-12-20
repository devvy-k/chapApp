package com.example.chapapp_compose.navigation.model.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.chapapp_compose.BottomBarScreen
import com.example.chapapp_compose.GeneralScreen
import com.example.chapapp_compose.ScreenContent
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import com.example.chapapp_compose.features_ui.detail.DetailScreen
import com.example.chapapp_compose.features_ui.home.HomeScreen
import com.example.chapapp_compose.features_ui.profile.ProfileScreen
import com.example.chapapp_compose.features_ui.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainNavGraph(
    navController: NavHostController,
    innerPadding: PaddingValues,
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier.padding(innerPadding),
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