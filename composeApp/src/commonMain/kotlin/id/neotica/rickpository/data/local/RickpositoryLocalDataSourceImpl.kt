package id.neotica.rickpository.data.local

import com.diamondedge.logging.logging
import id.neotica.rickpository.domain.local.RickpositoryLocalDataSource

class RickpositoryLocalDataSourceImpl(
    private val dao: RickAndMortyDao,
): RickpositoryLocalDataSource {
    override suspend fun getPagingWithCharacter(id: Int): PagingWithCharacter? {
        val log = logging("marvis")
        val daos = dao.getPagingWithCharacter(id)
        log.info { "getPagingWithCharacter: $daos" }
        return daos
    }


    override suspend fun insertPagination(pagination: PaginationEntity) {
        val log = logging("marvis")
        dao.insertPagination(pagination)

        log.info { "insertPagination: $pagination" }
    }

    override suspend fun insertCharacter(characters: List<CharacterEntity>) {
        val log = logging("marvis")
        dao.insertCharacter(characters)
        log.info { "insertCharacter: $characters" }
    }
}