package com.sledz.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.views.WelcomeScreen
import com.sledz.mobileapp.views.login.LoginScreen
import com.sledz.mobileapp.views.login.LoginViewModel
import com.sledz.mobileapp.views.main.MainScreen
import com.sledz.mobileapp.views.register.RegisterScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileAppTheme {
                val navController = rememberNavController()

                NavHost(navController, "welcome") {
                    composable("welcome") {
                        WelcomeScreen(navController)
                    }
                    composable("register") {
                        RegisterScreen(navController)
                    }
                    composable("login") {
                        LoginScreen(navController)
                    }
                    composable("main") {
                        MainScreen()
                    }
                }
            }
        }
    }
}

