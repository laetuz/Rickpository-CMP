package id.neotica.rickpository.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import id.neotica.rickpository.data.local.RickpositoryDb
import org.koin.dsl.module

actual fun platformModule() = module {
    single<RoomDatabase.Builder<RickpositoryDb>> {
        getDatabaseBuilder(get())
    }
}

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<RickpositoryDb> {
    return Room.databaseBuilder<RickpositoryDb>(
        context = context,
        name = "rickpository.db"
    )
}