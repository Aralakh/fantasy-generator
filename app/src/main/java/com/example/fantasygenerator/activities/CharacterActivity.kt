package com.example.fantasygenerator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fantasygenerator.R
import com.example.fantasygenerator.fragments.CharacterDetailFragment
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var character = Character()
        val characterRepository = CharacterRepository.getInstance(
            AppDatabase.getInstance(this).characterDao())

        GlobalScope.launch {
            runBlocking {
                characterRepository.addCharacter(character)
                var fragment = CharacterDetailFragment.newInstance(character.id)
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
            }
        }
    }
}