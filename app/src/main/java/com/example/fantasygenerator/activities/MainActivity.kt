package com.example.fantasygenerator.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fantasygenerator.R
import com.example.fantasygenerator.database.AppDatabase
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.models.*
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.utils.JsonFileUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var characterOptionsRepository: CharacterOptionsRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            characterOptionsRepository = CharacterOptionsRepository.getInstance(
                AppDatabase.getInstance(this@MainActivity).characterOptionsDao()
            )
            if(!checkIfDatabaseWasLoaded()) loadDatabaseData()
        }

        characters.setOnClickListener {

            val fragment = CharacterListFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(fragment.tag)
                .commit()
        }
    }

    /* this should be a one-time run method when user first installs/or it should check that user already
    has data in the database so it's not doing this every time app starts. It would overwrite user
    changes to database if they deleted any of the defaults. */
    private fun loadDatabaseData() {
        //this converts the old name style files to the new; should move this logic somewhere & think about using it to migrate existing app characters to this one
//        val oldNames = JsonFileUtil.loadOldNames(this@CharacterActivity)
//        JsonFileUtil.saveNames(oldNames, this@CharacterActivity)
        val names = JsonFileUtil.loadNames(this@MainActivity)
        names.run {
            val success = characterOptionsRepository.addNames(this)
            val debug = 0
        }

        val trait = Trait("short-tempered", TraitType.NEGATIVE)
        characterOptionsRepository.addTrait(trait)

        val motivations = JsonFileUtil.loadMotivations(this@MainActivity)
        motivations?.run {
            characterOptionsRepository.addMotivations(this)
        }

        val professions = JsonFileUtil.loadProfessions(this@MainActivity)
        professions?.run {
            characterOptionsRepository.addProfessions(this)
        }

        saveDatabaseStateToSharedPreferences()
    }

    private fun Character.getName(): Name {
        val names = characterOptionsRepository.getAllNames().value
        val debug = 0
        names?.forEach {
           if(it.name.startsWith("B")) return it
        }
        return Name("Chelsea", Gender.FEMALE)
    }

    private fun saveDatabaseStateToSharedPreferences() {
        val sharedPreferences = getSharedPreferences("FantasyGenerator", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("loadedDatabase", true).commit()
    }

    private fun checkIfDatabaseWasLoaded(): Boolean {
        val sharedPreferences = getSharedPreferences("FantasyGenerator", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("loadedDatabase", false)
    }
}