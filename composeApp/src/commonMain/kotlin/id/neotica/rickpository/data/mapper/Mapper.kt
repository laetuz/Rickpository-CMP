package id.neotica.rickpository.data.mapper

import id.neotica.rickpository.data.local.CharacterEntity
import id.neotica.rickpository.data.local.PaginationEntity
import id.neotica.rickpository.data.local.PagingWithCharacter
import id.neotica.rickpository.domain.model.Character
import id.neotica.rickpository.domain.model.Info
import id.neotica.rickpository.domain.model.RickAndMortyResponse

fun RickAndMortyResponse.toPaginationEntity(page: Int): PaginationEntity {
    return PaginationEntity(
        id = page,
        count = info.count,
        next = info.next,
        prev = info.prev
    )
}

fun RickAndMortyResponse.toCharacterEntities(paginationId: Int): List<CharacterEntity> {
    return results.map { character ->
        CharacterEntity(
            characterId = character.id,
            paginationId = paginationId,
            name = character.name,
            status = character.status,
            species = character.species,
            type = character.type,
            gender = character.gender,
            image = character.image,
            url = character.url,
            created = character.created
        )
    }
}

fun PagingWithCharacter?.toResponse(): RickAndMortyResponse {
    return RickAndMortyResponse(
        info = Info(
            count = this?.paging?.count?: 0,
            pages = 42,
            next = this?.paging?.next,
            prev = this?.paging?.prev
        ),
        results = this?.characters?.map {
            Character(
                id = it.characterId,
                name = it.name,
                status = it.status,
                species = it.species,
                type = it.type,
                gender = it.gender,
                image = it.image,
                url = it.url,
                created = it.created
            )
        }?: emptyList()
    )
}