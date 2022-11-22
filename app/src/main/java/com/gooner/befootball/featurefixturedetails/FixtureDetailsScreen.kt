package com.gooner.befootball.featurefixturedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gooner.befootball.ui.theme.FixtureDetailsHeadingCardColor
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.ColorTextPrimaryDark
import com.gooner.befootball.util.BarsColors
import com.gooner.befootball.util.CustomSubcomposeAsyncImage
import com.gooner.befootball.util.GameStatusIndicator
import com.gooner.domain.model.*
import org.koin.androidx.compose.getViewModel

@Composable
fun FixtureDetailsScreen(
    fixtureId: Int,
    onBackButtonClicked: () -> Unit
) {
    val fixtureDetailsViewModel = getViewModel<FixtureDetailsViewModel>()
    val fixture by remember { fixtureDetailsViewModel.fixture }
    val homeTeam =  fixture?.teams?.home?.name ?: ""
    val awayTeam =  fixture?.teams?.away?.name ?: ""

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
            FixtureInformationEvents(
                events = it.events,
                homeTeam = homeTeam,
                awayTeam = awayTeam
            )
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

@Composable
fun FixtureInformationEvents(
    events: List<Event>,
    homeTeam: String,
    awayTeam: String,
) {
    LazyColumn {
        itemsIndexed(events.reversed()) { index, event ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            ) {
                val iconResource: Int? = when (getEventTypeFilter(event.type ?: "")) {
                    EventTypeFilter.GOAL -> R.drawable.ic_goal
                    EventTypeFilter.CARD -> {
                        when (getEventDetailFilter(event.detail ?: "")) {
                            EventDetailsFilter.YELLOW_CARD ->
                                R.drawable.ic_yellow_card
                            EventDetailsFilter.RED_CARD ->
                                R.drawable.ic_red_card
                            EventDetailsFilter.SECOND_YELLOW_CARD ->
                                R.drawable.ic_double_yellow_card
                            else -> null
                        }
                    }
                    EventTypeFilter.SUBSTITUTION -> R.drawable.ic_substitution
                    else -> null
                }

                if (event.team.name == homeTeam) {
                    Column(
                        Modifier.weight(1f)
                    ) {
                        EventDetail(
                            event = event,
                            icon = iconResource
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .weight(1f)
                    )
                } else if (event.team.name == awayTeam) {
                    Spacer(
                        modifier = Modifier
                            .height(IntrinsicSize.Min)
                            .weight(1f)
                    )
                    Column(
                        Modifier.weight(1f)
                    ) {
                        EventDetail(
                            event = event,
                            icon = iconResource
                        )
                    }
                }
            }
            if (index < events.size - 1)
                Divider(
                    Modifier
                        .height(1.dp)
                        .padding(start = 8.dp, end = 8.dp)
                )
        }
    }
}

@Composable
fun EventDetail(
    event: Event,
    icon: Int? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${event.time.elapsed}\'",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                style = typography.body1
            )
            icon?.let {
                Image(
                    imageVector = ImageVector.vectorResource(
                        id = it
                    ),
                    contentDescription = event.type,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(start = 8.dp, end = 4.dp)
                )
            }
        }
        Column(
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = event.player.name.ifEmpty { "No information" },
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                style = typography.body1,
            )
            if (
                getEventTypeFilter(event.type ?: "") == EventTypeFilter.GOAL
                || getEventTypeFilter(event.type ?: "") == EventTypeFilter.SUBSTITUTION
            ) {
                event.assist.id.let {
                    Text(text = event.assist.name ?: "No information")
                }
            }
        }
    }
}