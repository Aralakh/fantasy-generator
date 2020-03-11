package com.example.fantasygenerator.fragments

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fantasygenerator.R
import com.example.fantasygenerator.adapters.CharacterAdapter
import com.example.fantasygenerator.interfaces.AdapterOnClickListener
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.database.AppDatabase
import com.example.fantasygenerator.models.Gender
import com.example.fantasygenerator.models.Motivation
import com.example.fantasygenerator.models.Name
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.viewModels.CharacterListViewModel
import com.example.fantasygenerator.viewModels.CharacterListViewModelFactory
import com.example.fantasygenerator.viewModels.CharacterOptionsVeiwModelFactory
import com.example.fantasygenerator.viewModels.CharacterOptionsViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import kotlinx.android.synthetic.main.fragment_character_list.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CharacterListFragment : Fragment(), AdapterOnClickListener {
    private lateinit var characterVewModel: CharacterListViewModel
    private lateinit var characterRepo: CharacterRepository
    private lateinit var characterOptionsRepository: CharacterOptionsRepository
    private lateinit var optionsViewModel: CharacterOptionsViewModel

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_character_list, container, false)
        view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.recyclerView.apply{
            val borderLine = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(borderLine)
        }

        characterRepo = CharacterRepository.getInstance(
            AppDatabase.getInstance(context!!.applicationContext).characterDao()
        )

        val factory = CharacterListViewModelFactory(characterRepo)
        characterVewModel = ViewModelProvider(this, factory).get(CharacterListViewModel::class.java)

        characterOptionsRepository = getCharacterOptionsRepo()

        optionsViewModel = ViewModelProvider(this,
            CharacterOptionsVeiwModelFactory(getCharacterOptionsRepo())
        ).get(CharacterOptionsViewModel::class.java)

        val adapter = CharacterAdapter(context!!)
        adapter.adapterOnClickListener = this
        subscribeAdapter(adapter)
        view.recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newCharactersBtn.setOnClickListener {
            var character = Character()
            character.name = Name("Pharasma", Gender.FEMALE)
            getMotivation {
                character.motivation = it
                getProfession(character)
            }
        }
    }

    private fun subscribeAdapter(adapter: CharacterAdapter) {
        characterVewModel.characters.observe(viewLifecycleOwner, Observer { characters ->
            if(characters != null) adapter.setCharacters(characters)
        })
    }

    override fun itemClicked(character: Character) {
        val fragment = CharacterDetailFragment.newInstance(character.id)
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(tag)
            .commit()
    }

    private fun getProfession(character: Character) {
        optionsViewModel.professions.observe(viewLifecycleOwner, Observer { professions ->
            professions?.forEach {
                if(it.profession == "Locksmith") {
                    character.profession = it
                    addCharacterToList(character)
                }
            }
        })
    }

    private fun getMotivation(callback: (Motivation) -> Unit) {
        optionsViewModel.motivations.observe(viewLifecycleOwner, Observer { motivations ->
            motivations?.forEach {
                if(it.motivation.startsWith("Return")) callback(it)
            }
        })
    }

    private fun getCharacterOptionsRepo() =
        CharacterOptionsRepository
            .getInstance(
                AppDatabase.getInstance(context as Activity).characterOptionsDao()
            )

    private fun addCharacterToList(character: Character) {
        GlobalScope.launch {
            runBlocking {
                characterRepo.addCharacter(character)
            }
        }
    }
}