package com.example.tabrow

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {
        composable(Screens.HomeScreen.route) {
            TabRow(navController)
        }

        composable(Screens.AddScreen.route) {
            AddScreen()
        }

        composable(Screens.SearchScreen.route) {
            SearchScreen()
        }

        composable(Screens.SettingScreen.route) {
            SettingScreen()
        }

    }

}

sealed class Screens(val route: String, val title: String, val Icon: ImageVector) {
    object HomeScreen : Screens("Home", "Home", Icon = Icons.Default.Home)
    object AddScreen : Screens("Add", "Add", Icon = Icons.Default.Add)
    object SearchScreen : Screens("Search", "Search", Icon = Icons.Default.Search)
    object SettingScreen : Screens("Setting", "Setting", Icon = Icons.Default.Settings)

}

@Composable
fun BottomNavigation(navController: NavController) {
    val item = listOf(
        Screens.HomeScreen,
        Screens.AddScreen,
        Screens.SearchScreen,
        Screens.SettingScreen
    )
    NavigationBar(
        containerColor = Color(0XFF22cb3d),
        contentColor = Color.White
    ) {
        val navStack by navController.currentBackStackEntryAsState()
        var current = navStack?.destination?.route
        item.forEach {
            NavigationBarItem(
                selected = current == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.let {
                            it.route?.let { it1 ->
                                popUpTo(it1)
                                launchSingleTop = true
                                restoreState = true

                            }
                        }
                    }
                },
                icon = {
                    Icon(imageVector = it.Icon, contentDescription = "")
                },
                label = {
                    Text(
                        text = it.title,
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    selectedTextColor = Color.Black,
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White
                )
            )
        }

    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavEntry() {
    val navController = rememberNavController()
    Scaffold(bottomBar = {
        BottomNavigation(navController = navController)
    }) {
        Navigation(navController = navController)

    }

}