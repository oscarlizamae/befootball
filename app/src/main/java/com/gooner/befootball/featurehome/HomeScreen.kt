package com.gooner.befootball.featurehome

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.gooner.befootball.R
import com.gooner.befootball.ui.theme.*
import com.gooner.befootball.util.BarsColors
import com.gooner.befootball.util.CircleShimmer
import com.gooner.domain.model.*
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    onAllLiveMatchesClicked: () -> Unit
) {

    val context = LocalContext.current
    val homeScreenViewModel = getViewModel<HomeScreenViewModel>()
    val leagues by remember { homeScreenViewModel.leagues }
    val liveMatches by remember { homeScreenViewModel.liveMatches }

    LaunchedEffect(key1 = true) {
        homeScreenViewModel.fetchLivesMatches()
        //homeScreenViewModel.fetchCurrentLeagues()
    }

    BarsColors(
        statusBarColor = if (isSystemInDarkTheme()) BackgroundColorDark else StatusBarColorLight,
        navigationBarColor = if (isSystemInDarkTheme()) NavigationBarColorDark else NavigationBarColorLight
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
    ) {
        AppLogoContainer()
        LiveMatchesLeagues(leagues = leagues)
        LiveMatches(liveMatches = liveMatches) {
            onAllLiveMatchesClicked()
        }
    }

}

@Composable
fun AppLogoContainer() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.ic_logo_app),
            contentDescription = "appLogo",
            colorFilter = ColorFilter.tint(
                if (isSystemInDarkTheme()) LogoColorDark else LogoColorLight
            )
        )
    }
}

@Composable
fun LiveMatchesLeagues(
    leagues: List<League>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp)
            .clip(
                shape = RoundedCornerShape(
                    topStart = 24.dp, bottomStart = 24.dp
                )
            )
            .background(LiveMatchesLeagueColor)
    ) {
        Text(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 24.dp,
                    bottom = 8.dp
                ),
            text = stringResource(id = R.string.live_matches_from_leagues),
            color = MaterialTheme.colors.onPrimary,
            style = typography.h6
        )
        LiveMatchesLeaguesContent(leagues = leagues)
    }
}

@Composable
fun LiveMatchesLeaguesContent(
    leagues: List<League>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        if (leagues.isEmpty()) {
            for (i in 1..5) {
                Box(
                    modifier = Modifier
                        .padding(top = 8.dp, start = 12.dp, bottom = 24.dp, end = 4.dp)
                ) {
                    CircleShimmer(size = 80.dp)
                }
            }
        } else {
            leagues.forEachIndexed { index, league ->
                if (index < 10) {
                    LeagueIcon(
                        leagueName = league.name,
                        logoUrl = league.logoUrl ?: "https://images.matematego.com/assets/noimage-cf86abd9b579765c1131ec86cb1e70052199ddadfecf252e5cb98e50535d11f3.png"
                    )
                }
            }
        }
    }
}

@Composable
fun LeagueIcon(
    leagueName: String,
    logoUrl: String
) {
    Box(
        modifier = Modifier
            .padding(top = 8.dp, start = 12.dp, bottom = 24.dp, end = 4.dp)
            .clip(CircleShape)
            .clickable { }
            //.background(Color(0xFFcfd8dc))
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoUrl)
                .build(),
            contentDescription = leagueName,
            loading = {
                CircularProgressIndicator(
                    color = ColorCircleProgressBarLeagues
                )},
            modifier = Modifier
                .padding(8.dp)
                .size(72.dp)
                .background(Color.Transparent),
            contentScale = ContentScale.Inside,
        )
    }
}

@Composable
fun LiveMatches(
    liveMatches: List<Fixture>,
    onAllLiveMatchesClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.live_matches),
                style = typography.h6,
                color = MaterialTheme.colors.onPrimary
            )
            Text(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { onAllLiveMatchesClicked() },
                text = stringResource(id = R.string.all_matches),
                style = typography.body2,
                color = MaterialTheme.colors.onPrimary
            )
        }
        LiveMatchesContainer(
            liveMatches = liveMatches,
            color = LiveMatchCardColor
        )
    }
}

@Composable
fun LiveMatchesContainer(
    liveMatches: List<Fixture>,
    color: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
    ) {
        /* for (i in 1..5) {
            LiveMatchCard(color = color)
        } */
        liveMatches.forEachIndexed { index, fixture ->
            if (index < 10) {
                LiveMatchCard(
                    fixture = fixture,
                    color = color
                )
            }
        }
    }
}

@Composable
fun LiveMatchCard(
    fixture: Fixture,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp)
                .fillMaxWidth()
                .clickable { }
                .widthIn(0.dp, 130.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = color
        ) {
            GameInfo(fixture = fixture)
        }
    }
}

@Composable
fun GameStatus(
    elapsed: Int,
    short: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 8.dp, bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 12.dp,
                    top = 2.dp,
                    bottom = 2.dp,
                    end = 16.dp
                ),
                text = if (short != "HT") "$elapsed\'" else { short },
                style = typography.body2,
                fontWeight = FontWeight.Bold,
                color = LiveMatchCardColor
            )
        }
        Box(
            modifier = Modifier
                .padding(top = 2.dp)
                .size(8.dp, 8.dp)
                .clip(CircleShape)
                .background(
                    if (short == "HT") IndicatorCircleYellow else IndicatorCircleGreen
                )
        )
    }
}

@Composable
fun GameInfo(
    fixture: Fixture
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
    ) {
        GameStatus(elapsed = fixture.status.elapsed, short = fixture.status.short)
        Teams(teams = fixture.teams)
        MatchScore(teams = fixture.teams, goals = fixture.goals)
    }
}

@Composable
fun Teams(
    teams: FixtureTeams
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(teams.home.logoUrl)
            .build(),
            contentDescription = teams.home.name,
            modifier = Modifier.size(36.dp)
        )
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(teams.away.logoUrl)
            .build(),
            contentDescription = teams.away.name,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Composable
fun MatchScore(
    teams: FixtureTeams,
    goals: Goals
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 10.dp, start = 8.dp, end = 8.dp)
    ) {
        TeamScore(teamName = teams.home.name, score = goals.home.toString())
        TeamScore(teamName = teams.away.name, score = goals.away.toString())
    }
}

@Composable
fun TeamScore(
    teamName: String,
    score: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = teamName,
            style = typography.body2,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(8f)
                .fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = score,
            style = typography.body2,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .padding(start = 4.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen { }
}
