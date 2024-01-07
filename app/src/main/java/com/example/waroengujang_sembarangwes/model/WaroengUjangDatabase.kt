package com.example.waroengujang_sembarangwes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Menu::class), version =  1)

abstract class WaroengUjangDatabase :RoomDatabase() {
    abstract fun WaroengUjangDao(): WaroengUjangDao

    companion object {
        @Volatile
        private var instance: WaroengUjangDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context): WaroengUjangDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WaroengUjangDatabase::class.java,
                "WaroengUjang").build()


    }
}