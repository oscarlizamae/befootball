package com.gooner.befootball.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gooner.befootball.ui.theme.IndicatorCircleGreen
import com.gooner.befootball.ui.theme.IndicatorCircleYellow

@Composable
fun GameStatusIndicator(
    status: String
) {
    Box(
        modifier = Modifier
            .padding(top = 2.dp)
            .size(8.dp, 8.dp)
            .clip(CircleShape)
            .background(
                if (status == "HT") IndicatorCircleYellow else IndicatorCircleGreen
            )
    )
}