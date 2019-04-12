package com.example.fantasygenerator.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.viewModels.CharacterDetailViewModel
import com.example.fantasygenerator.viewModels.CharacterDetailViewModelFactory

class CharacterDetailFragment : Fragment() {
    private lateinit var characterId: String

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        savedInstanceState?.let{
            if(it.containsKey("characterId")){
                characterId = it.getString("characterId")
            }
        }
        val characterRepository = CharacterRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).characterDao())

        val characterDetailViewModel = ViewModelProviders.of(this,
            CharacterDetailViewModelFactory(characterRepository, characterId))
            .get(CharacterDetailViewModel::class.java)
    }
}