package com.gooner.befootball.featurelivematches

import androidx.compose.runtime.Composable
import com.gooner.befootball.util.RegularTopAppBar

@Composable
fun LiveMatchesScreen(
    onBackIconClicked: () -> Unit
) {
    RegularTopAppBar(title = "Live matches") {
        onBackIconClicked()
    }
}