package com.gooner.befootball

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gooner.befootball.featurehome.HomeScreen
import com.gooner.befootball.featurelivefixtures.LiveFixturesScreen
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
            HomeScreen {
                navController.navigate(Screen.LiveMatches.route)
            }
        }
        // Navigation to LiveMatches Screen
        composable(Screen.LiveMatches.route) {
            LiveFixturesScreen {
                navController.navigateUp()
            }
        }
    }
}