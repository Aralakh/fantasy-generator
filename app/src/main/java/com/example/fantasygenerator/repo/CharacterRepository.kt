package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterDao

class CharacterRepository private constructor(private val characterDao: CharacterDao) {

    fun getCharacters() = characterDao.getCharacters()

    fun getCharacter(characterId: String) = characterDao.getCharacter(characterId)

    companion object {
        @Volatile private var instance: CharacterRepository? = null

        fun getInstance(characterDao: CharacterDao) =
                instance ?: synchronized(this){
                    instance ?: CharacterRepository(characterDao).also { instance = it }
                }
    }
}