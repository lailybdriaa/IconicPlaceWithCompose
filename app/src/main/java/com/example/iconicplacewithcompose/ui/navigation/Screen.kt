package com.example.iconicplacewithcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailPlace : Screen("home/{favoritePlaceId}") {
        fun createRoute(favoritePlaceId: Long) = "home/$favoritePlaceId"
    }
}
