package com.gooner.befootball

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gooner.befootball.featurehome.HomeScreen
import com.gooner.befootball.featurelivefixtures.LiveFixturesScreen
import com.gooner.befootball.feaurefixturedetails.FixtureDetailsScreen
import com.gooner.befootball.splashscreen.AnimatedSplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        // Navigation to SplashScreen
        composable(Screen.SplashScreen.route) {
            AnimatedSplashScreen {
                navController.popBackStack()
                navController.navigate(Screen.HomeScreen.route)
            }
        }
        // Navigation to HomeScreen
        composable(Screen.HomeScreen.route) {
            HomeScreen(
                onAllLiveMatchesClicked = { navController.navigate(Screen.LiveMatches.route) }
            ) { fixtureId ->
                navController.navigate(
                    Screen.FixtureDetails.withArgs(fixtureId.toString())
                )
            }
        }
        // Navigation to LiveMatches Screen
        composable(Screen.LiveMatches.route) {
            LiveFixturesScreen(
                onBackIconClicked = { navController.navigateUp() }
            ) { fixtureId ->
                navController.navigate(
                    Screen.FixtureDetails.withArgs(fixtureId.toString())
                )
            }
        }
        // Navigation to FixtureDetails Screen
        composable(
            Screen.FixtureDetails.route + "/{fixtureId}",
            arguments = listOf(
                navArgument("fixtureId") {
                    type = NavType.IntType
                }
            )
        ) { entry ->
            FixtureDetailsScreen(
                fixtureId = entry.arguments?.getInt("fixtureId") ?: 0
            )
        }
    }
}