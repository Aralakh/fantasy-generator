package com.example.fantasygenerator.repo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fantasygenerator.database.CharacterDao
import com.example.fantasygenerator.database.CharacterOptionsDao
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.models.CharacterOptions

@Database(entities = [Character::class, CharacterOptions::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao
    abstract fun characterOptionsDao() : CharacterOptionsDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        //this can be updated to inject data into the database at creation from json files
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "Characters.db").build()
        }

    }
}