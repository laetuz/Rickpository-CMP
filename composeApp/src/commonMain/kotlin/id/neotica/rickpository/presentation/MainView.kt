package id.neotica.rickpository.presentation

import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import id.neotica.rickpository.navigation.Screen

@Composable
fun MainView(navController: NavController) {
    Scaffold {
        Text("MainView")
        Button(onClick = {
            navController.navigate(Screen.CharactersScreen)
        }) { Text("Click here") }
    }
}