package com.example.fantasygenerator.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fantasygenerator.models.*

@Dao
interface CharacterOptionsDao {

    @Insert
    fun insertNames(names: List<Name>): List<Long>

    @Insert
    fun insertAllTraits(traits: List<Trait>)

    @Insert
    fun insertAllMotivations(motivations: List<Motivation>)

    @Insert
    fun insertAllProfessions(professions: List<Profession>)

    @Insert
    fun insertName(name: Name): Long

    @Insert
    fun insertTrait(trait: Trait)

    @Insert
    fun insertMotivation(motivation: Motivation)

    @Insert
    fun insertProfession(profession: Profession)

    @Update
    fun updateName(name: Name)

    @Update
    fun updateTrait(trait:Trait)

    @Update
    fun updateProfession(profession: Profession)

    @Update
    fun updateMotivation(motivation: Motivation)

    @Delete
    fun deleteName(name: Name)

    @Delete
    fun deleteTrait(trait: Trait)

    @Delete
    fun deleteProfession(profession: Profession)

    @Delete
    fun deleteMotivation(motivation: Motivation)

    @Query("Select * FROM names WHERE gender = :gender")
    fun getNamesByGender(gender: Gender): LiveData<List<Name>>

    fun getMaleNames(): LiveData<List<Name>> = getNamesByGender(Gender.MALE)

    fun getFemaleNames(): LiveData<List<Name>> = getNamesByGender(Gender.FEMALE)

    @Query("Select * FROM names")
    fun getAllNames(): LiveData<List<Name>>

    @Query("Select * FROM trait WHERE type = :traitType")
    fun getTraitsByType(traitType: TraitType): LiveData<List<Trait>>

    fun getPositiveTraits(): LiveData<List<Trait>> = getTraitsByType(TraitType.POSITIVE)

    fun getNegativeTraits(): LiveData<List<Trait>> = getTraitsByType(TraitType.NEGATIVE)

    fun getNeutralTraits(): LiveData<List<Trait>> = getTraitsByType(TraitType.NEUTRAL)

    @Query("Select * FROM professions")
    fun getProfessions(): LiveData<List<Profession>>

    @Query("Select * FROM motivations")
    fun getMotivations(): LiveData<List<Motivation>>
}