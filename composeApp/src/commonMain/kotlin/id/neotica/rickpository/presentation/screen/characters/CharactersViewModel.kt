package id.neotica.rickpository.presentation.screen.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamondedge.logging.logging
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.CharacterRepository
import id.neotica.rickpository.domain.extension.extractPageNumberAfterEquals
import id.neotica.rickpository.domain.model.RickAndMortyResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel(
    private val repository: CharacterRepository
): ViewModel() {

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    var hasNext by mutableStateOf(false)
    var paginatedContent by mutableStateOf<Map<Int, RickAndMortyResponse>>(emptyMap())

    private var nextUrl by mutableStateOf("")

    val log = logging("marvis")

    init {
        getPage()
    }

    fun getPage() = viewModelScope.launch {
        if (!hasNext) {
            getNextPage()
        } else {
            getNextPage(nextUrl)
        }
    }

    fun getNextPage(url: String? = null) = viewModelScope.launch {
        repository.getCharacter(url).collect {
            when(it) {
                is ApiResult.Loading -> {
                    _loading.value = true
                }
                is ApiResult.Success -> {
                    log.info { "Success: ${it.data}" }
                    _loading.value = false
                    val nextUrlRaw = it.data?.info?.next
                    log.info { "regex: ${nextUrlRaw.extractPageNumberAfterEquals()}" }
                    val currentPageInt = url.extractPageNumberAfterEquals() ?: 1

                    paginatedContent =  paginatedContent + (currentPageInt to it.data!!)

                    if (it.data.info.next != null) {
                        log.info { "currentpage, vm: "+ it.data.toString() }
                        paginatedContent.keys + 1
                        nextUrl = it.data.info.next
                        log.info { "nextUrl, vm: "+ nextUrl }
                        hasNext = true
                    }
                }
                is ApiResult.Error -> {
                    log.info { "Error: ${it.errorMessage}" }
                    logging("Error: ${it.errorMessage}")
                    _loading.value = false
                    when {
                        it.errorMessage.toString() == "Cleartext HTTP traffic to localhost not permitted" -> {}
                        else -> {
                            _message.value = it.errorMessage.toString()
                        }
                    }
                }
            }
        }
    }
}