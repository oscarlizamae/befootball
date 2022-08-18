package com.gooner.befootball.featurefixturedetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gooner.befootball.ui.theme.FixtureDetailsHeadingCardColor
import com.gooner.befootball.R
import com.gooner.befootball.util.BarsColors
import com.gooner.befootball.util.GameStatusIndicator
import org.koin.androidx.compose.getViewModel

@Composable
fun FixtureDetailsScreen(
    fixtureId: Int,
    onBackButtonClicked: () -> Unit
) {
    val fixtureDetailsViewModel = getViewModel<FixtureDetailsViewModel>()
    LaunchedEffect(key1 = true) {
        fixtureDetailsViewModel.fetchFixtureDetails(fixtureId)
    }
    BarsColors(statusBarColor = FixtureDetailsHeadingCardColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        FixtureDetailsHeadingCard(
            competition = "UEFA Conference League",
            status = "FH"
        ) {
            onBackButtonClicked()
        }
    }
}

@Composable
fun FixtureDetailsHeadingCard(
    competition: String,
    status: String,
    onBackButtonClicked: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp),
        backgroundColor = FixtureDetailsHeadingCardColor
    ) {
        Column(
            modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackButtonClicked() },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back_secondary),
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Text(
                    text = competition,
                    color = Color.White,
                    style = typography.body2,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(3f),
                    textAlign = TextAlign.Center
                )
                Column(
                    Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    GameStatusIndicator(status = status)
                }
            }
        }
    }
}