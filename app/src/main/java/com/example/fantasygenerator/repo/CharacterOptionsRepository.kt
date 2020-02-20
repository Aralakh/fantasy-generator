package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterOptionsDao
import com.example.fantasygenerator.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterOptionsRepository private constructor(private val characterOptionsDao: CharacterOptionsDao) {

    suspend fun addNames(names: Names) = withContext(Dispatchers.IO) { characterOptionsDao.insertNames(names) }
    suspend fun addNeutralTraits(neutralTraits: List<NeutralTrait>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllNeutralTraits(neutralTraits) }
    suspend fun addNegativeTraits(negativeTraits: List<NegativeTrait>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllNegativeTraits(negativeTraits) }
    suspend fun addPositiveTraits(positiveTraits: List<PositiveTrait>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllPositiveTraits(positiveTraits) }
    suspend fun addMotivations(motivations: List<Motivation>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllMotivations(motivations) }
    suspend fun addProfessions(professions: List<Profession>) = withContext(Dispatchers.IO) { characterOptionsDao.insertAllProfessions(professions) }
    fun getFemaleNames() = characterOptionsDao.getFemaleNames()
    fun getMaleNames() = characterOptionsDao.getMaleNames()

    companion object {
        @Volatile private var instance: CharacterOptionsRepository? = null

        fun getInstance(characterOptionsDao: CharacterOptionsDao) =
            instance ?: synchronized(this) {
                instance ?: CharacterOptionsRepository(characterOptionsDao).also { instance = it }
            }
    }

}