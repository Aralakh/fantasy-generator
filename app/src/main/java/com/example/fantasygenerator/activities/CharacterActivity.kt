package com.example.fantasygenerator.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.fantasygenerator.R
import com.example.fantasygenerator.models.*
import com.example.fantasygenerator.database.AppDatabase
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.utils.JsonFileUtil
import com.example.fantasygenerator.viewModels.CharacterOptionsVeiwModelFactory
import com.example.fantasygenerator.viewModels.CharacterOptionsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class CharacterActivity : AppCompatActivity() {
    private lateinit var characterOptionsRepository: CharacterOptionsRepository
    private lateinit var viewModel: CharacterOptionsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterOptionsRepository = getCharacterOptionsRepo()

        viewModel = ViewModelProvider(this,
            CharacterOptionsVeiwModelFactory(getCharacterOptionsRepo()))
            .get(CharacterOptionsViewModel::class.java)

//        GlobalScope.launch {
//            runBlocking {
//                loadDatabaseData()
//            }
//        }

        newCharacter.setOnClickListener {
            var character = Character()
            character.name = Name("Pharasma", Gender.FEMALE)
            getMotivation {
                character.motivation = it
                getProfession(character)
            }
        }
    }

    /* this should be a one-time run method when user first installs/or it should check that user already
    has data in the database so it's not doing this every time app starts. It would overwrite user
    changes to database if they deleted any of the defaults. */
    private fun loadDatabaseData() {
        //this converts the old name style files to the new; should move this logic somewhere & think about using it to migrate existing app characters to this one
//        val oldNames = JsonFileUtil.loadOldNames(this@CharacterActivity)
//        JsonFileUtil.saveNames(oldNames, this@CharacterActivity)
        val names = JsonFileUtil.loadNames(this@CharacterActivity)
        names.run {
            val success = characterOptionsRepository.addNames(this)
            val debug = 0
        }
        val trait = Trait("short-tempered", TraitType.NEGATIVE)
        characterOptionsRepository.addTrait(trait)
        val success = characterOptionsRepository.addName(Name("Beth", Gender.FEMALE))
        val debug = 0
        val motivations = JsonFileUtil.loadMotivations(this@CharacterActivity)
        motivations?.run {
            characterOptionsRepository.addMotivations(this)
        }

        val professions = JsonFileUtil.loadProfessions(this@CharacterActivity)
        professions?.run {
            characterOptionsRepository.addProfessions(this)
        }
    }


    private fun Character.getName(): Name {
        val names = characterOptionsRepository.getAllNames().value
        val debug = 0
        names?.forEach {
           if(it.name.startsWith("B")) return it
        }
        return Name("Chelsea", Gender.FEMALE)
    }

    private fun getProfession(character: Character) {
        viewModel.professions.observe(this@CharacterActivity, Observer { professions ->
            professions?.forEach {
                if(it.profession == "Locksmith") {
                    character.profession = it
                    addCharacterToList(character)
                }
            }

        })
    }

    private fun getMotivation(callback: (Motivation) -> Unit) {
        viewModel.motivations.observe(this@CharacterActivity, Observer { motivations ->
            motivations?.forEach {
                if(it.motivation.startsWith("Return")) callback(it)
            }
        })
    }

    private fun getCharacterOptionsRepo() =
        CharacterOptionsRepository
        .getInstance(
            AppDatabase.getInstance(this@CharacterActivity)
                .characterOptionsDao()
        )

    private fun addCharacterToList(character: Character) {
       GlobalScope.launch {
           runBlocking {
               val characterRepository = CharacterRepository.getInstance(
                   AppDatabase.getInstance(this@CharacterActivity).characterDao()
               )
               characterRepository.addCharacter(character)
           }
       }
        val fragment = CharacterListFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(fragment.tag)
            .commit()
        newCharacter.visibility = View.GONE
    }
}