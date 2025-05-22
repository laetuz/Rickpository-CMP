package id.neotica.rickpository

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import id.neotica.rickpository.di.viewModelModules
import id.neotica.rickpository.navigation.NavGraph
import id.neotica.rickpository.networking.ktorModule
import id.neotica.rickpository.presentation.MainView
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.compose.KoinContext
import org.koin.core.KoinApplication

@Composable
@Preview
fun App() {
    MaterialTheme {
        KoinContext {
            val navController = rememberNavController()

            Scaffold {
                NavGraph(
                    navController,
                    it
                )
            }
        }

//        KoinApplication(
//            application = {
//                modules(ktorModule, viewModelModules)
//            }
//        ) {
//            val navController = rememberNavController()
//
//            Scaffold(
//
//            ) {
//                NavGraph(
//                    navController,
//                    it
//                )
//            }
//        }


//        MainView(navController)



//        var showContent by remember { mutableStateOf(false) }

//        MainView(navController)
//        Column(
//            modifier = Modifier
//                .safeContentPadding()
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//            val animals = listOf("Shark", "Pig", "Cow", "Dog", "Cat")
//            LazyRow {
//                items(animals) {
//                    Text(it)
//                }
////                items(animals.size) {
////                    Text(animals[it])
////                }
//            }
//        }
    }
}