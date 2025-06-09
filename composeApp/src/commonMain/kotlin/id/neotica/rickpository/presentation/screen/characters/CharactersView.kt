package id.neotica.rickpository.presentation.screen.characters

import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import id.neotica.droidcore.component.alert.NeoToast
import id.neotica.rickpository.navigation.Screen
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersView(
    navController: NavController,
    viewModel: CharactersViewModel = koinViewModel()
) {
    val loading = viewModel.loading.collectAsState().value
    val listState = rememberLazyListState()
    var toast = remember { mutableStateOf(false) }
    var toastMessage = remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(viewModel.message) {
        viewModel.message.collect {
            if (it != null) {
                toast.value = true
                toastMessage.value = it
            }
        }
        if (viewModel.message.value != null) toast.value = true
    }

    val isMoreLoadingVisible by remember {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo
                .filter { it.key.toString() == "loading" }
                .size == 1
        }
    }

    LaunchedEffect(key1 = isMoreLoadingVisible) {
        if (isMoreLoadingVisible) {
            viewModel.getPage()
        }
    }

    val pagContent = viewModel.paginatedContent
        .values.map { it.results }.flatten()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("Characters")
                },
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                    scrolledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                ),
                scrollBehavior = scrollBehavior
            )
        },
    ) {
        Column {
            if (loading && pagContent.isEmpty()) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    state = listState,
                    contentPadding = it
                ) {
                    items(
                        items = pagContent,
                        key = { it.id }
                    ) {
                        Card(
                            Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                                .combinedClickable(
                                    onLongClick = {
                                        toast.value = true
                                        toastMessage.value = it.name
                                    }
                                ) { navController.navigate(Screen.CharacterDetail(it.id)) }
                        ) {
                            Row {
                                AsyncImage(
                                    model = it.image,
                                    contentDescription = null,
                                )
                                Column(
                                    Modifier.padding(8.dp)
                                ) {
                                    Text(it.name)
                                    Text(it.status)
                                    Text(it.gender)
                                }
                            }
                        }
                    }
                    if (viewModel.hasNext || loading) {
                        item(key = "loading") {
                            Box(
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
    }
    if (toast.value) NeoToast(toastMessage.value .toString(), toast)
}