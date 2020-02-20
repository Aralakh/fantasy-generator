package com.example.fantasygenerator.activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fantasygenerator.R
import com.example.fantasygenerator.database.CharacterDao
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.models.FemaleName
import com.example.fantasygenerator.models.Names
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterOptionsRepository
import com.example.fantasygenerator.repo.CharacterRepository
import com.example.fantasygenerator.utils.JsonFileUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.io.File

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        var character = Character()
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
        val names: List<FemaleName>? = characterOptionsRepository.getFemaleNames().value
        names?.run {

            forEach { name ->
                if (name.femaleName.startsWith("B"))
                //toDo MaleName & FemaleName should inherit from base type so either can work for Character name
                    character.name = name.toString()
            }
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

    suspend fun loadDatabaseData() = withContext(Dispatchers.IO) {
        val names = JsonFileUtil.loadNames()
        names?.run {
            val characterOptionsRepository = CharacterOptionsRepository
                .getInstance(
                    AppDatabase.getInstance(this@CharacterActivity)
                        .characterOptionsDao()
                )
            characterOptionsRepository.addNames(names)
        }
    }
}