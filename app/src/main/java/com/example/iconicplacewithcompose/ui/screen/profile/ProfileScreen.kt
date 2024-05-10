package com.example.iconicplacewithcompose.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iconicplacewithcompose.R

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(top = 100.dp)
        ){
            Image(
                painter = painterResource(R.drawable.my_foto),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
            )

            Text(
                text = stringResource(R.string.my_name),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .padding(top = 20.dp)
            )

            Text(
                text = stringResource(R.string.my_email),
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = MaterialTheme.colors.primary,
                modifier = modifier
                    .padding(top = 5.dp)
            )
            Text(
                text = stringResource(R.string.my_univ),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                ),
                modifier = modifier
                    .padding(top = 20.dp)
            )

            Text(
                text = stringResource(R.string.my_jurusan),
                style = MaterialTheme.typography.subtitle2,
                fontSize = 15.sp,
                color = MaterialTheme.colors.primary,
                modifier = modifier
                    .padding(top = 5.dp)
            )
        }
    }
}