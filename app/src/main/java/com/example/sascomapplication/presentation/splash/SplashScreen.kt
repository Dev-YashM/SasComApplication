package com.example.sascomapplication.presentation.splash

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.sascomapplication.presentation.auth.AuthViewModel
import com.example.sascomapplication.presentation.navigation.Screen
import kotlinx.coroutines.delay
import androidx.hilt.navigation.compose.hiltViewModel

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(
    navController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val isLoggedIn = remember { authViewModel.isUserLoggedIn() }

    LaunchedEffect(Unit) {
        delay(2000) // wait 2 seconds on splash
        if (isLoggedIn) {
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    // You can show a logo or animation here if you want
}
