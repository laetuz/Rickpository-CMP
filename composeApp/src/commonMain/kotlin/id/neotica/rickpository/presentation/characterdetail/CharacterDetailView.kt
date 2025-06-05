package id.neotica.rickpository.presentation.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CharacterDetailView(
    navController: NavController,
    viewModel: CharacterDetailViewModel = koinViewModel()
) {
    val characters by viewModel.characters.collectAsState()
    val loading by viewModel.loading.collectAsState()

    Scaffold {
        Column {
            LazyColumn {
                item {
                    characters?.let {
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
                item {
                    if (loading) Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}