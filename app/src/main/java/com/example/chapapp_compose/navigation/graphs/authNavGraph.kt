package com.example.chapapp_compose.navigation.graphs
import android.app.Activity.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.chapapp_compose.features_ui.auth.ForgotPasswordScreen
import com.example.chapapp_compose.features_ui.auth.signin.SignInScreen
import com.example.chapapp_compose.features_ui.auth.signup.SignUpScreen
import com.example.chapapp_compose.features_ui.auth.signin.GoogleAuthUiClient
import com.example.chapapp_compose.features_ui.auth.signin.SignInViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    googleAuthUiClient: GoogleAuthUiClient,
    viewModelScope: CoroutineScope
    ) {

    navigation(
        route = Graph.AUTH,
        startDestination = AuthScreen.LOGIN.route
    ){
        composable(route = AuthScreen.LOGIN.route){
            val viewModel : SignInViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit){
                if(googleAuthUiClient.getSignedInUser() != null){
                    navController.navigate(Graph.MAIN){
                        popUpTo(AuthScreen.LOGIN.route){inclusive = true}
                    }
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if(result.resultCode == RESULT_OK) {
                        viewModelScope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInGoogleResult(signInResult)
                        }
                    }

                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    navController.navigate(Graph.MAIN) {
                        viewModel.resetState()
                    }
                }
            }
            SignInScreen(
                state = state,
                navController = navController,
                onSignInClick = {
                    viewModelScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
        composable(route = AuthScreen.SIGNUP.route){
            SignUpScreen(navController)
        }
        composable(route = AuthScreen.FORGOT_PASSWORD.route){
            ForgotPasswordScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String){
    object LOGIN : AuthScreen("login_screen")
    object SIGNUP : AuthScreen("signup_screen")
    object FORGOT_PASSWORD : AuthScreen("forgot_password_screen")
}