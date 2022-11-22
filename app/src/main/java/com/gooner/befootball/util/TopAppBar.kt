package com.gooner.befootball.util

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gooner.befootball.R

@Composable
fun BaseTopAppBar(
    content: @Composable () -> Unit
) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 1.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp)
        ) {
            content()
        }
    }
}

@Composable
fun RegularTopAppBar(
    title: String,
    onBackIconClick: () -> Unit
) {
    BaseTopAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onBackIconClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = title,
                style = typography.h6,
                color = Color.Black
            )
        }
    }
}