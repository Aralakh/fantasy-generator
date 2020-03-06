package com.example.fantasygenerator.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.fantasygenerator.models.*
import com.example.fantasygenerator.utils.ConverterUtils

@Database(entities = [Character::class, Trait::class, Profession::class, Motivation::class, Name::class], version = 7, exportSchema = false)
@TypeConverters(ConverterUtils::class)
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
            return Room.databaseBuilder(context, AppDatabase::class.java, "Characters.db").fallbackToDestructiveMigration().build()
        }
    }
}