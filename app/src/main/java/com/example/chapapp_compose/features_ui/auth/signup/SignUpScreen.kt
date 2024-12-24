package com.example.chapapp_compose.features_ui.auth.signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.chapapp_compose.features_ui.component.molecules.CustomTextField
import com.example.chapapp_compose.R
import com.example.chapapp_compose.features_ui.component.molecules.CustomBox
import com.example.chapapp_compose.navigation.graphs.AuthScreen
import com.example.chapapp_compose.navigation.graphs.Graph

@Composable
fun SignUpScreen(
    navController: NavHostController,
    signUpViewModel: SignUpViewModel = hiltViewModel()
    ) {
    var emailAddress by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var nom by remember { mutableStateOf("") }
    var prenom by remember { mutableStateOf("") }
    var numTel by remember { mutableStateOf("") }
    var numRue by remember { mutableStateOf("") }
    var avenue by remember { mutableStateOf("") }
    var ville by remember { mutableStateOf("") }
    var codePostal by remember { mutableStateOf("") }

    CustomBox {
        CustomBox(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //Spacer(modifier = Modifier.fillMaxHeight(0.1f))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.10f),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = "CHAP",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 25.sp,
                            color = colorResource(id = R.color.colorChap)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logochap2),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                        .background(colorResource(id = R.color.colorChap))
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    CustomTextField(
                        value = nom,
                        onValueChanged = { nom = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    CustomTextField(
                        value = prenom,
                        onValueChanged = { prenom = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")
                        CustomTextField(
                            value = emailAddress,
                            onValueChanged = { emailAddress = it },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next),
                            label = {
                            Text(
                                text = "Email Address"
                            ) },
                            trailing = "")

                    CustomTextField(
                        value = numTel,
                        onValueChanged = { numTel = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    CustomTextField(
                        value = numRue,
                        onValueChanged = { numRue = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    CustomTextField(
                        value = avenue,
                        onValueChanged = { avenue = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    CustomTextField(
                        value = ville,
                        onValueChanged = { ville = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    CustomTextField(
                        value = codePostal,
                        onValueChanged = { codePostal = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")
                    CustomTextField(
                        value = password,
                        onValueChanged = { password = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")
                    CustomTextField(
                        value = confirmPassword,
                        onValueChanged = { confirmPassword = it },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next),
                        label = {
                            Text(
                                text = "Email Address"
                            ) },
                        trailing = "")

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {
                            signUpViewModel.registerUser(
                                email = emailAddress,
                                password = password
                            )
                            navController.navigate(Graph.MAIN) {
                                popUpTo(AuthScreen.LOGIN.route) { inclusive = true }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = colorResource(id = R.color.colorChap)
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(text = "Créer un compte", fontSize = 20.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen(navController = rememberNavController())
}