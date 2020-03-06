package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterDao
import com.example.fantasygenerator.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository private constructor(private val characterDao: CharacterDao) {

        fun addCharacter(character: Character) = characterDao.insertCharacter(character)

        fun addMultipleCharacters(character: List<Character>) = characterDao.insertAllCharacters(*character.toTypedArray())

        fun getCharacters() = characterDao.getCharacters()

        fun getCharacter(characterId: String) = characterDao.getCharacter(characterId)

        companion object {
        @Volatile private var instance: CharacterRepository? = null

        fun getInstance(characterDao: CharacterDao) =
            instance ?: synchronized(this) {
                instance ?: CharacterRepository(characterDao).also { instance = it }
            }
    }
}