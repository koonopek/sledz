package com.sledz.mobileapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun SledzPrimaryButton(
    buttonText: String,
    navController: NavController,
    path: String
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            navController.navigate(path)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
        Text(text = buttonText)
    }
}

@Composable
fun SledzSecondaryButton(
    buttonText: String,
    navController: NavController,
    path: String,
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            navController.navigate(path)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
    ) {
      Text(text = buttonText)
    }
}

@Composable
fun SledzEmailInput() {
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
fun SledzPasswordInput() {
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
fun Spacing(value: Dp) {
    Spacer(modifier = Modifier.height(value))
}