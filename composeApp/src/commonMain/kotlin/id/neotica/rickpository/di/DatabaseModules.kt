package id.neotica.rickpository.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import id.neotica.rickpository.data.local.RickpositoryDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun platformModule(): Module

val provideDatabaseModule = module {
    single { getDatabase(get()) }
    single { getRoomDatabase(get()) }
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<RickpositoryDb>
): RickpositoryDb = builder
    .addMigrations()
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()

fun getDatabase(db: RickpositoryDb) = db.baseDao()