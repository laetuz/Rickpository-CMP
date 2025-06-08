package id.neotica.rickpository.di

import androidx.room.RoomDatabase
import id.neotica.rickpository.data.local.RickpositoryDb
import id.neotica.rickpository.domain.CharacterRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single<RoomDatabase.Builder<RickpositoryDb>> { getDatabaseBuilder() }
}

class CharacterRepositoryHelper: KoinComponent {
    private val repository: CharacterRepository by inject()
    fun charRepo(): CharacterRepository = repository
}