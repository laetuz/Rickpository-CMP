package id.neotica.rickpository.domain

import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.domain.model.RickAndMortyResponse
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacter(url: String?): Flow<ApiResult<RickAndMortyResponse>>
    fun getCharacterDetail(id: Int): Flow<ApiResult<Character>>
}