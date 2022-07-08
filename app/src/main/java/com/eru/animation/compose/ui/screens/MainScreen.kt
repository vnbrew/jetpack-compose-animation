package com.eru.animation.compose.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.eru.animation.compose.ui.compositions.CustomSnackBarHost
import com.eru.animation.compose.ui.theme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun MainScreen(
    isDarkMode: Boolean,
    turnOnDarkMode: (Boolean) -> Unit,
) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = !isDarkMode
        )
    }

    MainScreenSkeleton(
        turnOnDarkMode = turnOnDarkMode,
    )
}

@Preview
@Composable
fun MainScreenSkeletonPreview() {
    AppTheme {
        ProvideWindowInsets {
            MainScreenSkeleton()
        }
    }
}

@Composable
fun MainScreenSkeleton(
    turnOnDarkMode: (Boolean) -> Unit = {},
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { CustomSnackBarHost(it) },
    ) {
        NavHostMain(
            modifier = Modifier,
            navController = navController,
            turnOnDarkMode = turnOnDarkMode,
        )
    }
}
