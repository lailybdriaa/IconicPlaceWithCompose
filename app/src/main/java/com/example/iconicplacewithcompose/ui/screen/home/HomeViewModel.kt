package com.example.iconicplacewithcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iconicplacewithcompose.data.IconicPlaceRepository
import com.example.iconicplacewithcompose.model.FavoritePlace
import com.example.iconicplacewithcompose.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: IconicPlaceRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FavoritePlace>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FavoritePlace>>>
        get() = _uiState

    fun getAllIconicPlace() {
        viewModelScope.launch {
            repository.searchIconicPlace(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { favoritePlace ->
                    _uiState.value = UiState.Success(favoritePlace)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        getAllIconicPlace()
    }

}