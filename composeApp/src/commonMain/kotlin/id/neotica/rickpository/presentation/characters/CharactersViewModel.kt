package id.neotica.rickpository.presentation.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.neotica.rickpository.data.CharacterRepositoryImpl
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.model.Character
import io.ktor.util.logging.KtorSimpleLogger
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

    private val _characters = MutableStateFlow<List<Character>?>(null)
    val characters = _characters.asStateFlow()

    init {
        getCharacters()
    }

    fun getCharacters() = viewModelScope.launch {
        repository.getCharacters().collect {
            when(it) {
                is ApiResult.Loading -> {
//                    _message.value = it.data.toString()
                }
                is ApiResult.Success -> {
                    _message.value = it.data.toString()
                    _characters.value = it.data
                }
                is ApiResult.Error -> {
                    _message.value = it.errorMessage.toString()
                }
            }

            KtorSimpleLogger(it.data.toString())
            KtorSimpleLogger(it.errorMessage.toString())
        }
    }

}