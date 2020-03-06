package com.example.fantasygenerator.repo

import com.example.fantasygenerator.database.CharacterOptionsDao
import com.example.fantasygenerator.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterOptionsRepository private constructor(private val characterOptionsDao: CharacterOptionsDao) {

    fun addNames(names: List<Name>) =  characterOptionsDao.insertNames(names)
    fun addTraits(traits: List<Trait>) = characterOptionsDao.insertAllTraits(traits)
    fun addMotivations(motivations: List<Motivation>) = characterOptionsDao.insertAllMotivations(motivations)
    fun addProfessions(professions: List<Profession>) =  characterOptionsDao.insertAllProfessions(professions)
    fun getFemaleNames() = characterOptionsDao.getFemaleNames()
    fun getMaleNames() = characterOptionsDao.getMaleNames()
    fun getPositiveTraits() = characterOptionsDao.getPositiveTraits()
    fun getNegativeTraits() =  characterOptionsDao.getNegativeTraits()
    fun getNeutralTraits() =  characterOptionsDao.getNeutralTraits()
    fun getAllNames() = characterOptionsDao.getAllNames()
    fun addName(name: Name) = characterOptionsDao.insertName(name)
    fun addTrait(trait: Trait) = characterOptionsDao.insertTrait(trait)
    fun getAllProfessions() = characterOptionsDao.getProfessions()
    fun getAllMotivations() =  characterOptionsDao.getMotivations()

    companion object {
        @Volatile private var instance: CharacterOptionsRepository? = null

        fun getInstance(characterOptionsDao: CharacterOptionsDao) =
            instance ?: synchronized(this) {
                instance ?: CharacterOptionsRepository(characterOptionsDao).also { instance = it }
            }
    }
}