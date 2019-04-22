package com.example.fantasygenerator.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.repo.CharacterRepository

class CharacterDetailViewModel(
    characterRepository: CharacterRepository,
    private val characterId: String
) : ViewModel() {

    val character: LiveData<Character>

    init{
       character = characterRepository.getCharacter(characterId)
    }
}