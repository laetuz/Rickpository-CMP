package id.neotica.rickpository.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "pagination_table")
data class PaginationEntity (
    @PrimaryKey
    val id: Int = 0,
    val count: Int,
    val next: String? = null,
    val prev: String? = null
)

@Entity(
    tableName = "character_table",
    foreignKeys = [
        ForeignKey(
            entity = PaginationEntity::class,
            parentColumns = ["id"],
            childColumns = ["paginationId"],
            onDelete = ForeignKey.CASCADE
            )
    ]
)
data class CharacterEntity(
    @PrimaryKey
    val characterId: Int = 0,
    val paginationId: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val url: String,
    val created: String
)

data class PagingWithCharacter(
    @Embedded
    val paging: PaginationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "paginationId"
    )
    val characters: List<CharacterEntity>
)