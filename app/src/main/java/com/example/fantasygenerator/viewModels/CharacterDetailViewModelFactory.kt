package com.example.fantasygenerator.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fantasygenerator.repo.CharacterRepository

class CharacterDetailViewModelFactory (
    private val characterRepository: CharacterRepository,
    private val characterId: String
    ) : ViewModelProvider.NewInstanceFactory() {

    override fun<T: ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailViewModel(characterRepository, characterId) as T
    }
}