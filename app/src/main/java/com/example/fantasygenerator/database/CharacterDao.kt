package com.example.fantasygenerator.database

import android.arch.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.fantasygenerator.models.Character

@Dao
interface CharacterDao {
    @Insert
    fun insertCharacters(characters: List<Character>)

    @Insert
    fun insertCharacter(character: Character)

    @Delete
    fun removeCharacter(character: Character)

    @Query("Select * FROM characters")
    fun getCharacters(): LiveData<List<Character>>

    @Query("Select * FROM characters where id = :characterId")
    fun getCharacter(characterId: String): LiveData<Character>
}