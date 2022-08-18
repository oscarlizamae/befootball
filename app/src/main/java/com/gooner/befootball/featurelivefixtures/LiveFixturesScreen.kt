package com.gooner.befootball.featurelivefixtures

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gooner.befootball.ui.theme.ColorTextPrimaryLight
import com.gooner.befootball.ui.theme.PinkColor
import com.gooner.befootball.R
import com.gooner.befootball.util.RegularTopAppBar
import com.gooner.domain.model.*
import org.koin.androidx.compose.getViewModel

@Composable
fun LiveFixturesScreen(
    onBackIconClicked: () -> Unit
) {

    val liveMatchesViewModel = getViewModel<LiveMatchesViewModel>()
    val liveMatches by remember { liveMatchesViewModel.liveMatches }

    LaunchedEffect(key1 = true) {
        liveMatchesViewModel.fetchLiveMatches()
    }

    Column {
        RegularTopAppBar(title = stringResource(id = R.string.live_matches)) {
            onBackIconClicked()
        }
        LiveFixturesContainer(fixtures = liveMatches)
    }
}

@Composable
fun LiveFixturesContainer(
    fixtures: List<Fixture>
) {
    LazyColumn {
        itemsIndexed(fixtures) { index, fixture ->
            Row {
                LiveFixtureDetail(fixture = fixture)
            }
            if (index < fixtures.size - 1)
                Divider(
                    Modifier
                        .height(1.dp)
                        .padding(start = 16.dp, end = 16.dp))
        }
    }
}

@Composable
fun LiveFixtureDetail(fixture: Fixture) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 16.dp, end = 16.dp)
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(3f)
        ) {
            FixtureTeamsDetails(teams = fixture.teams, fixtureScore = fixture.goals)
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 8.dp, bottom = 8.dp)
                .width(1.dp)
        )
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FixtureStatus(status = fixture.status)
        }
    }
}

@Composable
fun FixtureTeamsDetails(
    teams: FixtureTeams,
    fixtureScore: Goals
) {
    Column {
        TeamDetail(team = teams.home, goals = fixtureScore.home)
        TeamDetail(team = teams.away, goals = fixtureScore.away)
    }
}

@Composable
fun TeamDetail (
    team: Team,
    goals: Int
) {
    Row(
        modifier = Modifier
            .padding(bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier
            .weight(3f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data(team.logoUrl)
                .build(),
                contentDescription = team.name,
                modifier = Modifier
                    .size(36.dp)
                    .padding(end = 8.dp)
            )
            Text(
                text = team.name,
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = goals.toString(),
                style = MaterialTheme.typography.body2,
                color = ColorTextPrimaryLight,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun FixtureStatus(
    status: FixtureStatus
) {
    if (status.short != "HT") {
        LinearProgressIndicator(
            modifier = Modifier
                .width(16.dp)
                .height(2.dp),
            color = PinkColor,
            backgroundColor = Color.LightGray
        )
    }
    Text(
        text = if (status.short == "HT") {
            status.long
        }  else { "${status.elapsed}\'" },
        style = MaterialTheme.typography.body2,
        color = if (status.short == "HT") { Color.LightGray } else { PinkColor },
        fontWeight = if (status.short == "HT") { FontWeight.Medium } else { FontWeight.Bold },
        modifier = Modifier.padding(top = 6.dp)
    )
}