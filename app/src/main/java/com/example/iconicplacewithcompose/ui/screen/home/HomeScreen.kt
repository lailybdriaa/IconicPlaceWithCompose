package com.example.iconicplacewithcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.iconicplacewithcompose.di.Injection
import com.example.iconicplacewithcompose.model.FavoritePlace
import com.example.iconicplacewithcompose.ui.ViewModelFactory
import com.example.iconicplacewithcompose.ui.common.UiState
import com.example.iconicplacewithcompose.ui.components.IconicPlaceItem
import com.example.iconicplacewithcompose.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit
) {

    val query by viewModel.query

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllIconicPlace()
            }
            is UiState.Success -> {
                HomeContent(
                    favoritePlace = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail,
                )

                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier.background(MaterialTheme.colors.primary)
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    favoritePlace: List<FavoritePlace>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(start = 15.dp, top = 100.dp),
        modifier = modifier
    ) {
        items(favoritePlace) { data ->
                IconicPlaceItem(
                    image = data.iconicPlace.image,
                    name = data.iconicPlace.name,
                    country = data.iconicPlace.country,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.iconicPlace.id)
                    }
                )
        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(stringResource(R.string.search_iconic_place))
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}