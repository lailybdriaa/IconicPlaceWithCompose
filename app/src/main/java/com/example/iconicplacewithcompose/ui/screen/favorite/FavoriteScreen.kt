package com.example.iconicplacewithcompose.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.iconicplacewithcompose.di.Injection
import com.example.iconicplacewithcompose.ui.ViewModelFactory
import com.example.iconicplacewithcompose.ui.common.UiState
import com.example.iconicplacewithcompose.R
import com.example.iconicplacewithcompose.ui.components.FavoriteItem

@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Long) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedFavoritePlace()
            }
            is UiState.Success -> {
                FavoriteContent(
                    uiState.data,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun FavoriteContent(
    state: FavoriteState,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TopAppBar(backgroundColor = MaterialTheme.colors.surface) {
            Text(
                text = stringResource(R.string.menu_favorite),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.blue)
            )
        }
        if(state.count == 0){
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 300.dp),
                text = stringResource(R.string.favorite_empty),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.grey)
            )
        }else{
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.favoritePlace, key = { it.iconicPlace.id }) { item ->
                    FavoriteItem(
                        image = item.iconicPlace.image,
                        title = item.iconicPlace.name,
                        modifier = Modifier.clickable {
                            navigateToDetail(item.iconicPlace.id)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}