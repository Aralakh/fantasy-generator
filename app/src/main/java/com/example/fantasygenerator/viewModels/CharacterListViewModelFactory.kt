package com.example.fantasygenerator.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.fantasygenerator.repo.CharacterRepository

class CharacterListViewModelFactory(private val repository: CharacterRepository):
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = CharacterListViewModel(repository) as T
}