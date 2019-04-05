package com.example.fantasygenerator.repo

import android.arch.lifecycle.LiveData
import com.example.fantasygenerator.database.CharacterDao
import com.example.fantasygenerator.models.Character

class CharacterRepository(private val characterDao: CharacterDao) {
    fun getCharacters(): LiveData<List<Character>?> {
        return object : List<Character> {

            override fun loadFromDb() = characterDao.getCharacters()
            override fun shouldFetch(data: List<Character>?) = true
        }.asLiveData()
    }
}