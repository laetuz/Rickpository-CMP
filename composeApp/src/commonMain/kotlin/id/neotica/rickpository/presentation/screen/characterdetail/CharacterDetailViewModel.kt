package id.neotica.rickpository.presentation.screen.characterdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.remote.CharacterRepository
import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.navigation.Screen
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: CharacterRepository
): ViewModel() {

    private val args = savedStateHandle.toRoute<Screen.CharacterDetail>()

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