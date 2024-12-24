package com.example.chapapp_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import com.example.chapapp_compose.navigation.graphs.RootNavigationGraph
import com.example.chapapp_compose.ui.theme.ChapApp_composeTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            ChapApp_composeTheme {
                Surface{
                    val systemUiController = rememberSystemUiController()
                    val statusBarColor = MaterialTheme.colorScheme.primary
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = statusBarColor,
                            darkIcons = false
                        )
                    }
                    RootNavigationGraph(
                        googleAuthUiClient,
                        lifecycleScope = lifecycleScope
                    )
                }
            }
        }
    }
}
