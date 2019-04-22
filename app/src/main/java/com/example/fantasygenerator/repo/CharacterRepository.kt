package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterDao
import java.util.*

class CharacterRepository private constructor(private val characterDao: CharacterDao) {

    fun getCharacters() = characterDao.getCharacters()

    fun getCharacter(characterId: UUID) = characterDao.getCharacter(characterId.toString())

    companion object {
        @Volatile private var instance: CharacterRepository? = null

        fun getInstance(characterDao: CharacterDao) =
                instance ?: synchronized(this){
                    instance ?: CharacterRepository(characterDao).also { instance = it }
                }
    }
}