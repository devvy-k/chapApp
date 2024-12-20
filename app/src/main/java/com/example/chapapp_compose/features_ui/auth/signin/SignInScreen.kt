package com.example.chapapp_compose.features_ui.auth.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chapapp_compose.features_ui.component.molecules.CustomBox
import com.example.chapapp_compose.CustomTextField
import com.example.chapapp_compose.R
import com.example.chapapp_compose.features_ui.auth.AuthState
import com.example.chapapp_compose.features_ui.auth.SocialMediaLogin
import com.example.chapapp_compose.navigation.model.graphs.AuthScreen
import com.example.chapapp_compose.navigation.model.graphs.Graph


@Composable
fun SignInScreen(
    navController: NavHostController,
    state : AuthState,
    onSignInClick : () -> Unit
    ) {
    val textEmailState = remember { mutableStateOf("") }

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError) {
      state.signInError?.let { error ->
          Toast.makeText(
              context,
              error,
              Toast.LENGTH_LONG
          ).show()
      }
    }

    CustomBox(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.35f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "CHAP",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 50.sp,
                        color = colorResource(id = R.color.colorChap)
                    )
                    Text(
                        text = "Rapprochez vous de l'Afrique",
                        style = MaterialTheme.typography.headlineMedium,
                        fontStyle = FontStyle.Italic,
                        fontSize = 15.sp,
                        color = colorResource(id = R.color.colorChap)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.logochap2),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                    .background(colorResource(id = R.color.colorChap)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LoginSection(navController, state)
                Spacer(modifier = Modifier.size(30.dp))
                SocialMediaSection(onSignInClick)
                ToSignUp(navController)
            }
        }

    }
}

@Composable
private fun ToSignUp(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier.clickable(
                onClick = {
                    navController.navigate(AuthScreen.SIGNUP.route)
                }
            ),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("Vous n'avez pas de compte ? ")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Gray,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                ) {
                    append(" ")
                    append("Créer un compte")
                }
            }
        )
    }
}

@Composable
private fun SocialMediaSection(onSignInClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Ou connectez-vous avec",
            style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SocialMediaLogin(icon = R.drawable.google, text = "Google", onClick = onSignInClick)
        }
    }
}

@Composable
private fun LoginSection(
    navController: NavHostController,
    state : AuthState,
    signInViewModel: SignInViewModel = hiltViewModel()
    ) {
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(top = 50.dp, start = 30.dp, end = 30.dp)
    ) {
        CustomTextField(
            value = emailAddress,
            modifier = Modifier.fillMaxWidth(),
            onValueChanged = { emailAddress = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next),
            label = {
                Text(
                    text = "Email",
                    color = Color.White
                ) },
            trailing = "")
        Spacer(modifier = Modifier.size(10.dp))

        CustomTextField(
            value = password,
            modifier = Modifier.fillMaxWidth(),
            onValueChanged = { password = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next),
            label = {
                Text(
                    text = "Mot de passe",
                    color = Color.White
                ) },
            trailing = "")
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                signInViewModel.signIn(emailAddress, password)
                navController.navigate(Graph.MAIN){
                    popUpTo(AuthScreen.LOGIN.route){inclusive = true}
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = colorResource(id = R.color.colorChap)
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Connexion", fontSize = 20.sp)
        }
    }
}
