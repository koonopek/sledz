package com.sledz.mobileapp

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sledz.mobileapp.ui.theme.MobileAppTheme

@Composable
fun LoginScreen() {
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

            LoginButton()

        }
    }
}

@Composable
private fun LoginButton() {
    SledzSecondaryButton(
        buttonText = "Log in",
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
private fun Spacing(value: Dp) {
    Spacer(modifier = Modifier.height(value))
}

@Composable
private fun PasswordInput() {
    val textState = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { newString ->
            textState.value = newString
        },
        label = {
            Text(text = "Password")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
private fun EmailInput() {
    val textState = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { newString ->
            textState.value = newString
        },
        label = {
            Text(text = "Email address")
        },
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
        LoginScreen()
    }
}