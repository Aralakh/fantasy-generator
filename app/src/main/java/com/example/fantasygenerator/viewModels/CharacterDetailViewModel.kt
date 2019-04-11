package com.example.fantasygenerator.viewModels

import androidx.lifecycle.ViewModel
import com.example.fantasygenerator.repo.CharacterRepository

class CharacterDetailViewModel(
    characterRepository: CharacterRepository,
    private val characterId: String
) : ViewModel() {

    init{
       val character = characterRepository.getCharacter(characterId)
    }
}