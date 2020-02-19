package com.example.fantasygenerator.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fantasygenerator.R
import com.example.fantasygenerator.database.CharacterDao
import com.example.fantasygenerator.fragments.CharacterListFragment
import com.example.fantasygenerator.models.Character
import com.example.fantasygenerator.repo.AppDatabase
import com.example.fantasygenerator.repo.CharacterRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class CharacterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Room.databaseBuilder(this, AppDatabase::class.java, "Names.db")
            .createFromFile(File("assets/names.json"))
//        var character = Character()
//        val characterRepository = CharacterRepository.getInstance(
//            AppDatabase.getInstance(this).characterDao())

        GlobalScope.launch {
            runBlocking {
//                characterRepository.addCharacter(character)
                val fragment = CharacterListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.tag)
                    .commit()
            }
        }
    }
}