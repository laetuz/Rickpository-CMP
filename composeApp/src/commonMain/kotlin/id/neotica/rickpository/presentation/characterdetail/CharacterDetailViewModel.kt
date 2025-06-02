package id.neotica.rickpository.presentation.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import id.neotica.rickpository.data.CharacterRepositoryImpl
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.navigation.Screen
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val repository = CharacterRepositoryImpl()

    private val args = savedStateHandle.toRoute<Screen.CharacterDetail>()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _characters = MutableStateFlow<Character?>(null)
    val characters = _characters.asStateFlow()

    init {
        getCharacters(args.id)
    }

    fun getCharacters(id: Int) = viewModelScope.launch {
        repository.getCharacterDetail(id).collect {
            when(it) {
                is ApiResult.Loading -> {
                    _loading.value = true
                }
                is ApiResult.Success -> {
                    _message.value = it.data.toString()
                    _characters.value = it.data
                    _loading.value = false
                }
                is ApiResult.Error -> {
                    _message.value = it.errorMessage.toString()
                    _loading.value = false
                }
            }

            KtorSimpleLogger(it.data.toString())
            KtorSimpleLogger(it.errorMessage.toString())
        }
    }

}