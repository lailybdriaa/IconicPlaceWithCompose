package com.example.iconicplacewithcompose.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconicplacewithcompose.data.IconicPlaceRepository
import com.example.iconicplacewithcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: IconicPlaceRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FavoriteState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FavoriteState>>
        get() = _uiState

    fun getAddedFavoritePlace() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedFavoritePlace()
                .collect { favoritePlace ->
                    val count = favoritePlace.count()
                    _uiState.value = UiState.Success(FavoriteState(favoritePlace, count))
                }
        }
    }
}