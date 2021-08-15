package com.example.app.ui.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.HomeSections
import com.example.app.ui.theme.AppTheme
import com.google.accompanist.insets.navigationBarsPadding


@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    tabs: Array<HomeSections> = arrayOf()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = modifier.navigationBarsPadding(start = false, end = false)) {
        tabs.forEach { section ->
            BottomNavigationItem(
                modifier = modifier,
                icon = { Icon(painter = painterResource(section.icon), contentDescription = stringResource(section.title)) },
                label = { Text(stringResource(section.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == section.route } == true,
                onClick = {
                    navController.navigate(section.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun AppBottomBarPreview() {
    AppTheme {
        Scaffold(
            bottomBar = {
                AppBottomBar(
                    navController = rememberNavController(),
                    tabs = HomeSections.values()
                )
            }) {
        }
    }
}
