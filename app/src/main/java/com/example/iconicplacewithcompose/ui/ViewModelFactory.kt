package com.example.iconicplacewithcompose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iconicplacewithcompose.data.IconicPlaceRepository
import com.example.iconicplacewithcompose.ui.screen.favorite.FavoriteViewModel
import com.example.iconicplacewithcompose.ui.screen.detail.DetailIconicPlaceViewModel
import com.example.iconicplacewithcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: IconicPlaceRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailIconicPlaceViewModel::class.java)) {
            return DetailIconicPlaceViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}