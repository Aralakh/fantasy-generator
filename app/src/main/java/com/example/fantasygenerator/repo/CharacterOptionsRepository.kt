package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterOptionsDao
import com.example.fantasygenerator.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterOptionsRepository private constructor(private val characterOptionsDao: CharacterOptionsDao) {

    suspend fun addNames(names: List<Name>) =  withContext(Dispatchers.IO) { characterOptionsDao.insertNames(names)}
    suspend fun addTraits(traits: List<Trait>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllTraits(traits) }
    suspend fun addMotivations(motivations: List<Motivation>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllMotivations(motivations) }
    suspend fun addProfessions(professions: List<Profession>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllProfessions(professions) }
    suspend fun getFemaleNames() = withContext(Dispatchers.IO) { characterOptionsDao.getFemaleNames() }
    suspend fun getMaleNames() = withContext(Dispatchers.IO) { characterOptionsDao.getMaleNames() }
    suspend fun getPositiveTraits() = withContext(Dispatchers.IO) { characterOptionsDao.getPositiveTraits() }
    suspend fun getNegativeTraits() = withContext(Dispatchers.IO) { characterOptionsDao.getNegativeTraits() }
    suspend fun getNeutralTraits() = withContext(Dispatchers.IO) { characterOptionsDao.getNeutralTraits() }

    companion object {
        @Volatile private var instance: CharacterOptionsRepository? = null

        fun getInstance(characterOptionsDao: CharacterOptionsDao) =
            instance ?: synchronized(this) {
                instance ?: CharacterOptionsRepository(characterOptionsDao).also { instance = it }
            }
    }
}