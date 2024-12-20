package com.example.chapapp_compose.navigation.model.graphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chapapp_compose.MainScreen
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RootNavigationGraph(
    googleAuthUiClient: GoogleAuthUiClient,
    lifecycleScope: CoroutineScope
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Graph.AUTH,
        route = Graph.ROOT
        ){
            authNavGraph(navController, googleAuthUiClient, lifecycleScope)
            composable(route = Graph.MAIN){
                MainScreen(
                    googleAuthUiClient = googleAuthUiClient,
                    onSignOut = {
                        lifecycleScope.launch {
                            googleAuthUiClient.signOut()
                            navController.navigate(Graph.AUTH) {
                                popUpTo(Graph.MAIN) { inclusive = true }
                            }
                        }
                    }
                )
            }
    }

}

object Graph {
    const val ROOT = "root_graph"
    const val AUTH = "auth_graph"
    const val MAIN = "main_graph"
}