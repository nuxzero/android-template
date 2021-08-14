package com.example.app.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.app.R
import com.example.app.ui.components.AppBottomBar
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.insets.ProvideWindowInsets


enum class HomeSections(@StringRes val title: Int, @DrawableRes val icon: Int, val route: String) {
    HOME(R.string.home, R.drawable.ic_bottom_nav_home, "home/home"),
    ACCOUNT(R.string.account, R.drawable.ic_bottom_nav_account, "home/account"),
}

private val isBottomBarShow = true

@Composable
fun NotesApp() {
    ProvideWindowInsets {
        AppTheme {
            val tabs = remember { HomeSections.values() }
            val navController = rememberNavController()
            Scaffold(
                bottomBar = {
                    if (!isBottomBarShow) return@Scaffold
                    AppBottomBar(navController = navController, tabs = tabs)
                }
            ) { innerPadding ->
                NavigationGraph(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
