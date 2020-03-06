package com.example.fantasygenerator.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fantasygenerator.repo.CharacterOptionsRepository

class CharacterOptionsVeiwModelFactory(private val repository: CharacterOptionsRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharacterOptionsViewModel(repository) as T
    }
}