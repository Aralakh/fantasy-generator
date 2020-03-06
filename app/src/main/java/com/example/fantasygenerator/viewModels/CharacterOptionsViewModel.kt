package com.example.fantasygenerator.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.fantasygenerator.models.Motivation
import com.example.fantasygenerator.models.Name
import com.example.fantasygenerator.models.Profession
import com.example.fantasygenerator.models.Trait
import com.example.fantasygenerator.repo.CharacterOptionsRepository

class CharacterOptionsViewModel(private val characterOptionsRepository: CharacterOptionsRepository): ViewModel() {
    val professions: LiveData<List<Profession>> = characterOptionsRepository.getAllProfessions()
    val motivations: LiveData<List<Motivation>> = characterOptionsRepository.getAllMotivations()
    val names: LiveData<List<Name>> = characterOptionsRepository.getAllNames()
    val negativeTraits: LiveData<List<Trait>> = characterOptionsRepository.getNegativeTraits()
    val neutralTraits: LiveData<List<Trait>> = characterOptionsRepository.getNeutralTraits()
    val positiveTraits: LiveData<List<Trait>> = characterOptionsRepository.getPositiveTraits()
}