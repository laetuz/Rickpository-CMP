package id.neotica.rickpository.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.neotica.rickpository.presentation.MainView
import id.neotica.rickpository.presentation.SecondView
import id.neotica.rickpository.presentation.characterdetail.CharacterDetailView
import id.neotica.rickpository.presentation.characters.CharactersView

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        enterTransition = { fadeIn(tween(100)) },
        popEnterTransition = { EnterTransition.None },
        exitTransition = { fadeOut(tween(100)) },
        popExitTransition = { ExitTransition.None },
        startDestination = Screen.CharactersScreen
    ) {
        composable<Screen.MainScreen> { MainView(navController) }
        composable<Screen.SecondScreen> { SecondView(navController) }
        composable<Screen.CharactersScreen> { CharactersView(navController) }
        composable<Screen.CharacterDetail> { CharacterDetailView(navController) }
    }
}