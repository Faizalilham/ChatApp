package android.faizal.openai

import android.faizal.openai.ui.navigation.Screen
import android.faizal.openai.ui.screen.AnimatedSplashScreen
import android.faizal.openai.ui.screen.HomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route ){
        composable(route = Screen.Splash.route){
           AnimatedSplashScreen(navController = navController)
        }

        composable(route = Screen.Home.route){
            HomeScreen()
        }
    }
}