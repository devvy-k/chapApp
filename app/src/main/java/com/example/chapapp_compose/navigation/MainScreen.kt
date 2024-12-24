package com.example.chapapp_compose.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import com.example.chapapp_compose.navigation.graphs.MainNavGraph

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    googleAuthUiClient: GoogleAuthUiClient,
    onSignOut: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        bottomBar = { BottomBar(navController = navController) })
    {
        MainNavGraph(
            navController = navController,
            innerPadding = it,
            googleAuthUiClient = googleAuthUiClient,
            onSignOut = onSignOut
        )
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screen = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )

    val navbackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navbackStackEntry?.destination
    
    val bottomBarDestination = screen.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar{
            screen.forEach{ screen ->
                AddItems(
                    screen,
                    currentDestination,
                    navController
                )
        }
        }
    }

}

@Composable
fun RowScope.AddItems(screen: BottomBarScreen, currentDestination: NavDestination?, navController: NavController) {
    NavigationBarItem(
        label = { Text(text = screen.title)},
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation Icon")},
        selected = currentDestination?.hierarchy?.any{ it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}