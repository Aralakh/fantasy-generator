package com.example.fantasygenerator.database

import android.arch.lifecycle.LiveData
import androidx.room.*
import com.example.fantasygenerator.models.Character

@Dao
interface CharacterDao {

    @Insert
    fun insertCharacter(vararg character: Character)

    @Update
    fun updateCharacter(vararg character: Character)

    @Delete
    fun removeCharacter(vararg character: Character)

    @Query("Select * FROM characters")
    fun getCharacters(): LiveData<List<Character>>

    @Query("Select * FROM characters where id = :characterId")
    fun getCharacter(characterId: String): LiveData<Character>
}