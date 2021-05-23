package com.sledz.mobileapp.views.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.views.SledzSecondaryButton
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.util.Resource
import com.sledz.mobileapp.views.Spacing

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltNavGraphViewModel()
) {

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {

            LoginHeader()

            EmailInput()

            Spacing(value = 8.dp)

            PasswordInput()

            TermsOfServiceLabel()

            LoginButton(navController)

//            Button(onClick = { viewModel.userLogin() }) {
//                Text("AAAAAAA")
//            }
        }
    }
}

@Composable
private fun LoginButton(navController: NavController) {
    SledzSecondaryButton(
        buttonText = "Log in",
        navController,
        "main"
    )

}

@Composable
private fun TermsOfServiceLabel() {
    Text(
        text = "By clicking below you agree to our Terms of Using Your Data and Trojan Policy",
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .paddingFromBaseline(
                top = 24.dp,
                bottom = 16.dp
            )
    )
}

@Composable
fun PasswordInput() {
    var textState by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            textState = it
        },
        label = {
            Text(text = "Password")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun EmailInput() {
    var textState by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textState,
        onValueChange = {
            textState = it
        },
        label = {
            Text(text = "Email address")
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun LoginHeader() {
    Text(
        text = "Log in with email",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 16.dp,
            )
    )
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun LoginScreenPreview() {
    MobileAppTheme {
        LoginScreen(rememberNavController())
    }
}