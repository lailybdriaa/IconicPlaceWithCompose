package com.example.iconicplacewithcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.iconicplacewithcompose.ui.theme.IconicPlaceWithComposeTheme
import com.example.iconicplacewithcompose.ui.theme.Shapes
import com.example.iconicplacewithcompose.R

@Composable
fun FavoriteItem(
    image: Int,
    title: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(90.dp)
                .clip(Shapes.small)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(1.0f)
        ) {
            Text(
                text = title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .padding(top = 20.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun FavoriteItemPreview() {
    IconicPlaceWithComposeTheme {
        FavoriteItem(
            R.drawable.monas, "Monas"
        )
    }
}