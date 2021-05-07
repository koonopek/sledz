package com.sledz.mobileapp.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.ui.theme.MobileAppTheme

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            WelcomeHeader()

            SledzPrimaryButton(
                buttonText = "Login",
                navController,
                "login"
            )

            Spacing(value = 16.dp)

            SledzSecondaryButton(
                buttonText = "Register",
                navController,
                "register"
            )
        }
    }
}


@Composable
fun WelcomeHeader() {
    Text(
        text = "Is it time to sledz some prices?",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .paddingFromBaseline(
                top = 184.dp,
                bottom = 64.dp,
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
        WelcomeScreen(rememberNavController())
    }
}