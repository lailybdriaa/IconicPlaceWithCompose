@file:Suppress("UNUSED_EXPRESSION")

package com.example.iconicplacewithcompose.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.iconicplacewithcompose.R
import com.example.iconicplacewithcompose.di.Injection
import com.example.iconicplacewithcompose.ui.ViewModelFactory
import com.example.iconicplacewithcompose.ui.common.UiState
import com.example.iconicplacewithcompose.ui.components.FavoriteButton
import com.example.iconicplacewithcompose.ui.theme.IconicPlaceWithComposeTheme

@Composable
fun DetailScreen(
    favoritePlaceId: Long,
    viewModel: DetailIconicPlaceViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToFavorite: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoritePlaceById(favoritePlaceId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.iconicPlace.image,
                    data.iconicPlace.name,
                    data.iconicPlace.country,
                    data.isFavorited,
                    data.iconicPlace.description,
                    data.iconicPlace.city,
                    data.iconicPlace.year,
                    onBackClick = navigateBack,
                    onAddToFavorite = { check ->
                        viewModel.addToFavorite(data.iconicPlace, check)
                        navigateToFavorite()
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    name: String,
    country: String,
    check: Boolean,
    desc: String,
    city: String,
    year: String,
    onBackClick: () -> Unit,
    onAddToFavorite: (check: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {

    val favoriteCheck by rememberSaveable { mutableStateOf(check) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = country,
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = MaterialTheme.colors.primary,
                    modifier = modifier
                        .padding(top = 5.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(top = 5.dp)
                ){
                    Text(
                        text = stringResource(
                            R.string.city,
                            city
                        ),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                    )

                    Text(
                        text = stringResource(
                            R.string.year,
                            year
                        ),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Justify,
                        modifier = modifier
                            .padding(start = 30.dp)
                    )
                }

                Text(
                    text = desc,
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Justify,
                    modifier = modifier
                        .padding(0.dp, 20.dp)
                )
            }
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(LightGray))
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if(!favoriteCheck){
                FavoriteButton(
                    text = stringResource(R.string.add_to_favorite),
                    onClick = {
                        onAddToFavorite(true)
                    }
                )
            }else{
                FavoriteButton(
                    text = stringResource(R.string.remove_from_favorite),
                    onClick = {
                        onAddToFavorite(false)
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    IconicPlaceWithComposeTheme {
        DetailContent(
            R.drawable.monas,
            "Monas",
            "Indonesia",
            true,
            R.string.lorem_ipsum.toString(),
            "Jakarta",
            "1961",
            onBackClick = {},
            onAddToFavorite = {}
        )
    }
}