package com.gooner.befootball.util

import android.content.Context
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.gooner.befootball.ui.theme.ColorCircleProgressBarLeagues

@Composable
fun CustomSubcomposeAsyncImage(
    context: Context,
    url: String,
    contentDescription: String,
    contentScale: ContentScale,
    modifier: Modifier
) {
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(context)
            .data(url)
            .build(),
        contentDescription = contentDescription,
        loading = {
            CircularProgressIndicator(
                color = ColorCircleProgressBarLeagues
            )
        },
        modifier = modifier,
        contentScale = contentScale
    )
}