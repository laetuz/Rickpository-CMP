package id.neotica.rickpository.di

import androidx.room.Room
import androidx.room.RoomDatabase
import id.neotica.rickpository.data.local.RickpositoryDb
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder(): RoomDatabase.Builder<RickpositoryDb> {
    val dbFilePath = documentDirectory() + "/rickpository.db"
    return Room.databaseBuilder<RickpositoryDb>(dbFilePath)
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val docDir = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null
    )

    return requireNotNull(docDir?.path)
}

/**
 * 7th of Fune, 11.45PM
 * IOS
 *
 * Add file database builder at shared/iosMain
 * https://medium.com/@hidayatasep43/implementing-room-database-in-kotlin-multiplatform-a-step-by-step-guide-2bc3e1b3aa16
 * **/