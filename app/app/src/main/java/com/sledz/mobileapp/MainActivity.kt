package com.sledz.mobileapp

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.sledz.mobileapp.data.database.AppDatabase
import com.sledz.mobileapp.data.database.DatabaseHelper
import com.sledz.mobileapp.data.models.ProductDetails
import com.sledz.mobileapp.data.remote.MainApi
import com.sledz.mobileapp.repository.MainRepository
import com.sledz.mobileapp.ui.theme.MobileAppTheme
import com.sledz.mobileapp.views.WelcomeScreen
import com.sledz.mobileapp.views.login.LoginScreen
import com.sledz.mobileapp.views.login.LoginViewModel
import com.sledz.mobileapp.views.main.MainScreen
import com.sledz.mobileapp.views.product_detail.ProductDetailsScreen
import com.sledz.mobileapp.views.register.RegisterScreen
import com.sledz.mobileapp.views.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
                        MainScreen(navController)
                    }
                    composable("productDetails/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })) {
                        ProductDetailsScreen(navController, it.arguments!!.getLong("id"))
                    }
                }
            }
        }
    }

}

