package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterOptionsDao
import com.example.fantasygenerator.models.Motivations
import com.example.fantasygenerator.models.Names
import com.example.fantasygenerator.models.Professions
import com.example.fantasygenerator.models.Traits
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterOptionsRepository private constructor(private val characterOptionsDao: CharacterOptionsDao) {

    suspend fun addNames(names: Names) = withContext(Dispatchers.IO) { characterOptionsDao.insertNames(names) }
    suspend fun addTraits(traits: Traits) = withContext(Dispatchers.IO) { characterOptionsDao.insertTraits(traits) }
    suspend fun addMotivations(motivations: Motivations) = withContext(Dispatchers.IO) { characterOptionsDao.insertMotivations(motivations) }
    suspend fun addProfessions(professions: Professions) = withContext(Dispatchers.IO) { characterOptionsDao.insertProfessions(professions) }

}