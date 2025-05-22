package id.neotica.rickpository.presentation.characters

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharactersView(
    navController: NavController,
    viewModel: CharactersViewModel = koinViewModel()
) {
//    val test = viewModel.getCharacters()
//    val viewModel = koinViewModel<CharactersViewModel>()
    val message = viewModel.message.collectAsState().value
    val characters = viewModel.characters.collectAsState().value
    Scaffold {
        Column {
//            Text(message.toString())
            Button(
                {"test"}
            ) { Text("yeah bro") }
            LazyColumn {
                characters?.let { char ->
                    items(char) {
                        Card(
                            Modifier.padding(bottom = 8.dp)
                        ) {
                            Column {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                )
                                Text(it.name)
                                Text(it.status)
                                Text(it.gender)
                            }
                        }

                    }
                }
            }
        }
    }
}