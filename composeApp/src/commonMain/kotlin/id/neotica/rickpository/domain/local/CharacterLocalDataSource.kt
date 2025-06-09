package id.neotica.rickpository.domain.local

import id.neotica.rickpository.data.local.CharacterEntity
import id.neotica.rickpository.data.local.PaginationEntity
import id.neotica.rickpository.data.local.PagingWithCharacter

interface CharacterLocalDataSource {
    suspend fun getPagingWithCharacter(id: Int): PagingWithCharacter?
    suspend fun insertPagination(pagination: PaginationEntity)
    suspend fun insertCharacter(characters: List<CharacterEntity>)
}