package com.sledz.mobileapp.views.register

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.models.AuthToken
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.util.Resource
import com.sledz.mobileapp.views.SledzEmailInput
import com.sledz.mobileapp.views.SledzPasswordInput
import com.sledz.mobileapp.views.SledzSecondaryButton
import com.sledz.mobileapp.views.Spacing

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val rpassword: String by viewModel.rpassword.observeAsState("")
    val result by viewModel.registerResponse.observeAsState(Resource.Loading())

    when (result) {
        is Resource.Success<AuthToken> -> {
            navController.navigate("main") {
                popUpTo("welcome") { inclusive = true }
            }
        }
        is Resource.Error<AuthToken> -> {
            Toast.makeText(LocalContext.current, result.message, Toast.LENGTH_SHORT).show()
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
            RegisterHeader()

            SledzEmailInput(email, viewModel::onEmailChange)

            Spacing(value = 8.dp)

            SledzPasswordInput(password, viewModel::onPasswordChange)

            Spacing(value = 8.dp)

            SledzPasswordInput(rpassword, viewModel::onRPasswordChange)

            TermsOfServiceLabel()

            SledzSecondaryButton(buttonText = "Register", viewModel::onRegister)
        }
    }
}


@Composable
fun RegisterHeader() {
    Text(
        text = "Register with your email",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 16.dp,
            )
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

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun RegisterScreenPreview() {
    MobileAppTheme {
        RegisterScreen(rememberNavController())
    }
}