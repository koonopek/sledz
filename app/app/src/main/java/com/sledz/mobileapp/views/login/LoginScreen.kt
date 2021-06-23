package com.sledz.mobileapp.views.login

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.util.Resource
import com.sledz.mobileapp.views.Spacing

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val userLogin by viewModel.loginResponse.observeAsState(Resource.Loading<AuthToken>())
    when (userLogin) {
        is Resource.Success<AuthToken> -> {
            navController.navigate("main") {
                popUpTo("welcome") { inclusive = true }
            }
        }
        // wiem, że mogą być różne inne problemy z logowaniem, ale przynajmniej pokazuję, że coś jest nie tak
        is Resource.Error<AuthToken> -> {
            Toast.makeText(LocalContext.current, "Wrong email or password", Toast.LENGTH_SHORT).show()
            Log.i("LOGIN", userLogin.message.toString())
        }
        else -> {
        }
    }


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

            EmailInput(viewModel)

            Spacing(value = 8.dp)

            PasswordInput(viewModel)

            TermsOfServiceLabel()

            LoginButton(viewModel)
        }
    }
}

@Composable
private fun LoginButton(viewModel: LoginViewModel) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            viewModel.userLogin()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = "Log in")
    }
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
fun PasswordInput(viewModel: LoginViewModel) {
    val password: String by viewModel.password.observeAsState("")

    OutlinedTextField(
        value = password,
        onValueChange = {
            viewModel.onPasswordChange(it)
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
fun EmailInput(viewModel: LoginViewModel) {
    val login: String by viewModel.name.observeAsState("")

    OutlinedTextField(
        value = login,
        onValueChange = {
            viewModel.onLoginChange(it)
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