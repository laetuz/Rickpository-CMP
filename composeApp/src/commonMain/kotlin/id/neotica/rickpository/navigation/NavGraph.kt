package id.neotica.rickpository.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import id.neotica.rickpository.presentation.characters.CharactersView
import id.neotica.rickpository.presentation.MainView
import id.neotica.rickpository.presentation.SecondView

@Composable
fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing),
        navController = navController,
        startDestination = Screen.MainScreen
    ) {
        composable<Screen.MainScreen> { MainView(navController) }
        composable<Screen.SecondScreen> { SecondView(navController) }
        composable<Screen.CharactersScreen> { CharactersView(navController) }
    }
}