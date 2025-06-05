package id.neotica.rickpository.presentation.characters

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diamondedge.logging.logging
import id.neotica.rickpository.data.CharacterRepositoryImpl
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.model.RickAndMortyResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel: ViewModel() {
    val repository = CharacterRepositoryImpl()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    private val _loading = MutableStateFlow<Boolean>(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    var hasNext by mutableStateOf(false)
    var paginatedContent by mutableStateOf<Map<Int, RickAndMortyResponse>>(emptyMap())

    private var _currentPage = MutableStateFlow(1)
    val currentPage: StateFlow<Int> = _currentPage.asStateFlow()

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
                    val prevUrlRar = it.data?.info?.next
                    log.info { "regex: ${nextUrlRaw.extractPageNumberAfterEquals()}" }
                    val currentPageInt: Int = if (prevUrlRar != null) {
                        nextUrlRaw.extractPageNumberAfterEquals()!!
                    } else {
                        1
                    }

                    paginatedContent =  paginatedContent + (currentPageInt to it.data!!)

                    if (it.data.info.next != null) {
                        _currentPage.value = currentPageInt - 1
                        log.info { "currentpage"+_currentPage.value }
                        paginatedContent.keys + 1
                        nextUrl = it.data.info.next
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

fun String?.extractPageNumberAfterEquals(): Int? {
    if (this.isNullOrEmpty()) {
        return null
    }
    // Regex to find one or more digits (\d+) immediately following an equals sign (=)
    // at the very end of the string ($). The digits are captured in a group.
    val regex = "=(\\d+)$".toRegex()
    val matchResult = regex.find(this)

    // groupValues[0] is the full match (e.g., "=3")
    // groupValues[1] is the first captured group (e.g., "3")
    return matchResult?.groupValues?.get(1)?.toIntOrNull()
}