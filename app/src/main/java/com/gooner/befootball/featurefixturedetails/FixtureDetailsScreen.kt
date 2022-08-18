package com.gooner.befootball.featurefixturedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gooner.befootball.ui.theme.FixtureDetailsHeadingCardColor
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.ColorTextPrimaryDark
import com.gooner.befootball.ui.theme.ColorTextPrimaryLight
import com.gooner.befootball.util.BarsColors
import com.gooner.befootball.util.CustomSubcomposeAsyncImage
import com.gooner.befootball.util.GameStatusIndicator
import com.gooner.domain.model.Fixture
import org.koin.androidx.compose.getViewModel

@Composable
fun FixtureDetailsScreen(
    fixtureId: Int,
    onBackButtonClicked: () -> Unit
) {
    val fixtureDetailsViewModel = getViewModel<FixtureDetailsViewModel>()
    val fixture by remember { fixtureDetailsViewModel.fixture }
    LaunchedEffect(key1 = true) {
        fixtureDetailsViewModel.fetchFixtureDetails(fixtureId)
    }
    BarsColors(statusBarColor = FixtureDetailsHeadingCardColor)
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        fixture?.let {
            FixtureDetailsHeadingCard(
                competition = fixture?.league?.name ?: "",
                status = fixture?.status?.short ?: "",
                fixture = it
            ) {
                onBackButtonClicked()
            }
        }
    }
}

@Composable
fun FixtureDetailsHeadingCard(
    competition: String,
    status: String,
    fixture: Fixture,
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
            FixtureGeneralInfo(competition, status) {
                onBackButtonClicked()
            }
            FixtureInfo(fixture = fixture)
        }
    }
}

@Composable
fun FixtureGeneralInfo(
    competition: String,
    status: String,
    onBackButtonClicked: () -> Unit
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

@Composable
fun FixtureInfo(
    fixture: Fixture
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(top = 12.dp, start = 16.dp, bottom = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSubcomposeAsyncImage(
                context = LocalContext.current,
                url = fixture.teams.home.logoUrl,
                contentDescription = fixture.teams.home.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .background(Color.Transparent)
            )
            Text(
                text = fixture.teams.home.name,
                style = typography.body1,
                color = ColorTextPrimaryDark,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${fixture.goals.home} - ${fixture.goals.away}",
                style = typography.h3,
                color = ColorTextPrimaryDark,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${fixture.status.elapsed}\'",
                style = typography.h6,
                color = ColorTextPrimaryDark,
                fontWeight = FontWeight.Normal
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomSubcomposeAsyncImage(
                context = LocalContext.current,
                url = fixture.teams.away.logoUrl,
                contentDescription = fixture.teams.away.name,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .background(Color.Transparent)
            )
            Text(
                text = fixture.teams.away.name,
                style = typography.body2,
                color = ColorTextPrimaryDark,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}