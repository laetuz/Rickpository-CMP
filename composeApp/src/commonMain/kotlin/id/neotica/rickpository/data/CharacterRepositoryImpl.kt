package id.neotica.rickpository.data

import com.diamondedge.logging.logging
import id.neotica.rickpository.data.mapper.toCharacterEntities
import id.neotica.rickpository.data.mapper.toPaginationEntity
import id.neotica.rickpository.data.mapper.toResponse
import id.neotica.rickpository.domain.ApiResult
import id.neotica.rickpository.domain.remote.CharacterRepository
import id.neotica.rickpository.domain.extension.extractPageNumberAfterEquals
import id.neotica.rickpository.domain.local.CharacterLocalDataSource
import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.domain.model.RickAndMortyResponse
import id.neotica.rickpository.networking.ktorClient
import id.neotica.rickpository.networking.safeApiCall
import id.neotica.rickpository.networking.safeApiCallNew
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow

class CharacterRepositoryImpl(
    private val local: CharacterLocalDataSource
): CharacterRepository {

    override fun getCharacter(url: String?): Flow<ApiResult<RickAndMortyResponse>> = safeApiCall {
        val log = logging("marvis2")
        val currentPage = url.extractPageNumberAfterEquals() ?: 1
        val getLocal = local.getPagingWithCharacter(currentPage)

        if (getLocal != null) {
            log.info { "local: yes" }
            getLocal.toResponse()
        } else {
            log.info { "local: no" }
            val response = ktorClient.get(url?: "https://rickandmortyapi.com/api/character?page=$currentPage").body<RickAndMortyResponse>()
            local.insertPagination(pagination = response.toPaginationEntity(currentPage))
            local.insertCharacter(characters = response.toCharacterEntities(currentPage))

            response
        }
    }

    override fun getCharacterDetail(id: Int): Flow<ApiResult<Character>> = safeApiCallNew<Character> {
        ktorClient.get("https://rickandmortyapi.com/api/character/$id")
    }
}