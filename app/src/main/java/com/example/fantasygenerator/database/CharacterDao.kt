package com.example.fantasygenerator.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fantasygenerator.models.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: Character)

    @Insert
    fun insertAllCharacters(vararg character: Character)

    @Update
    fun updateCharacter(character: Character)

    @Update
    fun updateAllCharacters(vararg character: Character)

    @Delete
    fun removeCharacter(vararg character: Character)

    @Query("Select * FROM characters")
    fun getCharacters(): LiveData<List<Character>>

    @Query("Select * FROM characters where id = :characterId")
    fun getCharacter(characterId: String): LiveData<Character>
}