package com.example.iconicplacewithcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.iconicplacewithcompose.ui.navigation.NavigationItem
import com.example.iconicplacewithcompose.ui.navigation.Screen
import com.example.iconicplacewithcompose.ui.screen.favorite.FavoriteScreen
import com.example.iconicplacewithcompose.ui.screen.detail.DetailScreen
import com.example.iconicplacewithcompose.ui.screen.home.HomeScreen
import com.example.iconicplacewithcompose.ui.screen.profile.ProfileScreen
import com.example.iconicplacewithcompose.ui.theme.IconicPlaceWithComposeTheme

@Composable
fun JetIconicPlaceApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailPlace.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { favoritePlaceId ->
                        navController.navigate(Screen.DetailPlace.createRoute(favoritePlaceId))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    navigateToDetail = { favoritePlaceId ->
                        navController.navigate(Screen.DetailPlace.createRoute(favoritePlaceId))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.DetailPlace.route,
                arguments = listOf(navArgument("favoritePlaceId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("favoritePlaceId") ?: -1L
                DetailScreen(
                    favoritePlaceId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToFavorite = {
                        navController.popBackStack()
                        navController.navigate(Screen.Favorite.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = stringResource(R.string.home_page)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite,
                contentDescription = stringResource(R.string.favorite_page)
            ),
            NavigationItem(
                title = stringResource(R.string.menu_profile),
                icon = Icons.Default.AccountCircle,
                screen = Screen.Profile,
                contentDescription = stringResource(R.string.about_page)
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetIconicPlaceAppPreview() {
    IconicPlaceWithComposeTheme {
        JetIconicPlaceApp()
    }
}
