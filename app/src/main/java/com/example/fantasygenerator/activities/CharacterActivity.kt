package com.example.fantasygenerator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fantasygenerator.R
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.models.Gender
import com.example.fantasygenerator.models.Name
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.utils.JsonFileUtil
import kotlinx.coroutines.*
import java.util.*

class CharacterActivity : AppCompatActivity() {
   lateinit var characterOptionsRepository: CharacterOptionsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterRepository = CharacterRepository.getInstance(
            AppDatabase.getInstance(this).characterDao()
        )
        var character = Character()

        GlobalScope.launch {
            runBlocking {
//                loadDatabaseData()
                characterOptionsRepository = getCharacterRepo()
                characterOptionsRepository.addName(Name("Beth", Gender.FEMALE))
                character.name = getName()
                val success =characterRepository.addCharacter(character)
                val fragment = CharacterListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()
            }
        }
    }

    /* this should be a one-time run method when user first installs/or it should check that user already
    has data in the database so it's not doing this every time app starts. It would overwrite user
    changes to database if they deleted any of the defaults. */
    private suspend fun loadDatabaseData() {
        //this converts the old name style files to the new; should move this logic somewhere & think about using it to migrate existing app characters to this one
//        val oldNames = JsonFileUtil.loadOldNames(this@CharacterActivity)
//        JsonFileUtil.saveNames(oldNames, this@CharacterActivity)
        val names = JsonFileUtil.loadNames(this@CharacterActivity)
        names.run {
            characterOptionsRepository = getCharacterRepo()
            val success = characterOptionsRepository.addNames(this)
            val debug = 0
        }
    }

    private suspend fun getName(): Name {
        val names = characterOptionsRepository.getAllNames().value
        val debug = 0
        names?.forEach {
           if(it.name.startsWith("B")) return it
        }
        return Name("Chelsea", Gender.FEMALE)
    }

    private fun getCharacterRepo() =
        CharacterOptionsRepository
        .getInstance(
            AppDatabase.getInstance(this@CharacterActivity)
                .characterOptionsDao()
        )
}