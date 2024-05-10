package com.example.iconicplacewithcompose.ui.screen.favorite

import com.example.iconicplacewithcompose.model.FavoritePlace

data class FavoriteState(
    val favoritePlace: List<FavoritePlace>,
    val count: Int
)