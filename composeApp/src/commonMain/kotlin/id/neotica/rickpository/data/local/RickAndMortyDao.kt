package id.neotica.rickpository.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface RickAndMortyDao {
    @Transaction
    @Query("SELECT * FROM pagination_table where id = :id")
    suspend fun getPagingWithCharacter(id: Int): PagingWithCharacter?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPagination(pagination: PaginationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characters: List<CharacterEntity>)
}