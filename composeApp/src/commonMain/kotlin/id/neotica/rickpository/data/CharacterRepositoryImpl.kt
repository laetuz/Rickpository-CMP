package id.neotica.rickpository.data

import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.domain.model.RickAndMortyResponse
import id.neotica.rickpository.networking.ktorClient
import id.neotica.rickpository.networking.safeApiCall
import id.neotica.rickpository.networking.safeApiCallNew
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl {

    fun getCharacter(url: String?): Flow<ApiResult<RickAndMortyResponse>> = safeApiCall {
        val response = ktorClient.get(url?: "https://rickandmortyapi.com/api/character").body<RickAndMortyResponse>()

        response
    }

    fun getCharacterDetail(id: Int): Flow<ApiResult<Character>> = safeApiCallNew<Character> {
        ktorClient.get("https://rickandmortyapi.com/api/character/$id")
    }
}