package com.example.fantasygenerator.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.repo.CharacterRepository

class CharacterListViewModel internal constructor(characterRepository: CharacterRepository) : ViewModel() {
    val characters: LiveData<List<Character>> = characterRepository.getCharacters()
}