package id.neotica.rickpository.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [
        PaginationEntity::class,
        CharacterEntity::class,
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class RickpositoryDb: RoomDatabase() {
    abstract fun baseDao(): RickAndMortyDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<RickpositoryDb> {
    override fun initialize(): RickpositoryDb
}
