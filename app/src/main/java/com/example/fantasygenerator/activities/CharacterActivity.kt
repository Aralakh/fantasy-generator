package com.example.fantasygenerator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fantasygenerator.R
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.models.Name
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.utils.JsonFileUtil
import kotlinx.coroutines.*

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterRepository = CharacterRepository.getInstance(
            AppDatabase.getInstance(this).characterDao()
        )
        val uiScope = CoroutineScope(Dispatchers.IO)
        uiScope.launch { loadDatabaseData() }

        var character = Character()
        val characterOptionsRepository = CharacterOptionsRepository
            .getInstance(
                AppDatabase.getInstance(this@CharacterActivity)
                    .characterOptionsDao()
            )
        var names: List<Name>? = emptyList()

        uiScope.launch {
             names = characterOptionsRepository.getFemaleNames().value
        }
            names?.forEach {

                it.name.startsWith("B")
                character.name = it
            }

            GlobalScope.launch {
                runBlocking {
                    characterRepository.addCharacter(character)
                    val fragment = CharacterListFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(fragment.tag)
                        .commit()
                }
            }
        }

    private suspend fun loadDatabaseData() = withContext(Dispatchers.IO) {
        val names = JsonFileUtil.loadNames(this@CharacterActivity)
        names?.run {
            val characterOptionsRepository = CharacterOptionsRepository
                .getInstance(
                    AppDatabase.getInstance(this@CharacterActivity)
                        .characterOptionsDao()
                )
            characterOptionsRepository.addNames(this)
        }
    }
}