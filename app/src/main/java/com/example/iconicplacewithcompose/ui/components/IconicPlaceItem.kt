package com.example.iconicplacewithcompose.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iconicplacewithcompose.R
import com.example.iconicplacewithcompose.ui.theme.IconicPlaceWithComposeTheme
import com.example.iconicplacewithcompose.ui.theme.Shapes

@Composable
fun IconicPlaceItem(
    image: Int,
    name: String,
    country: String,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(0.dp, 10.dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Column(
            modifier = modifier
                .padding(start = 15.dp)
        ){
            Text(
                text = name,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp
                ),
            )
            Text(
                text = country,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.primary
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun IconicPlaceItemPreview() {
    IconicPlaceWithComposeTheme {
        IconicPlaceItem(R.drawable.monas, "Monas", "Indonesia")
    }
}