package com.example.iconicplacewithcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconicplacewithcompose.data.IconicPlaceRepository
import com.example.iconicplacewithcompose.model.FavoritePlace
import com.example.iconicplacewithcompose.model.IconicPlace
import com.example.iconicplacewithcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailIconicPlaceViewModel(
    private val repository: IconicPlaceRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoritePlace>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoritePlace>>
        get() = _uiState

    fun getFavoritePlaceById(iconicPlaceId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getFavoritePlaceById(iconicPlaceId))
        }
    }

    fun addToFavorite(iconicPlace: IconicPlace, check: Boolean) {
        viewModelScope.launch {
            repository.updateFavoritePlace(iconicPlace.id, check)
        }
    }
}