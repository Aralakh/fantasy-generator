package com.example.fantasygenerator.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fantasygenerator.models.*

@Dao
interface CharacterOptionsDao {

    @Insert
    fun insertNames(names: Names)

    @Insert
    fun insertAllPositiveTraits(positiveTraits: List<PositiveTrait>)

    @Insert
    fun insertAllNegativeTraits(negativeTraits: List<NegativeTrait>)

    @Insert
    fun insertAllNeutralTraits(neutralTraits: List<NeutralTrait>)

    @Insert
    fun insertAllMotivations(motivations: List<Motivation>)

    @Insert
    fun insertAllProfessions(professions: List<Profession>)

    @Insert
    fun insertMaleName(maleName: MaleName)

    @Insert
    fun insertFemaleName(femaleName: FemaleName)

    @Insert
    fun insertPositiveTrait(positiveTrait: PositiveTrait)

    @Insert
    fun insertNegativeTrait(negativeTrait: NegativeTrait)

    @Insert
    fun insertNeutralTrait(neutralTrait: NeutralTrait)

    @Insert
    fun insertMotivation(motivation: Motivation)

    @Insert
    fun insertProfession(profession: Profession)

    @Update
    fun updateMaleName(maleName: MaleName)

    @Update
    fun updateFemaleName(femaleName: FemaleName)

    @Update
    fun updatePositiveTrait(positiveTrait: PositiveTrait)

    @Update
    fun updateNegativeTrait(negativeTrait: NegativeTrait)

    @Update
    fun updateNeutralTrait(neutralTrait: NeutralTrait)

    @Update
    fun updateProfession(profession: Profession)

    @Update
    fun updateMotivation(motivation: Motivation)

    @Delete
    fun deleteMaleName(maleName: MaleName)

    @Delete
    fun deleteFemaleName(femaleName: FemaleName)

    @Delete
    fun deletePositiveTrait(positiveTrait: PositiveTrait)

    @Delete
    fun deleteNegativeTrait(negativeTrait: NegativeTrait)

    @Delete
    fun deleteNeutralTrait(neutralTrait: NeutralTrait)

    @Delete
    fun deleteProfession(profession: Profession)

    @Delete
    fun deleteMotivation(motivation: Motivation)

    @Query("Select * FROM characterOptions")
    fun getMaleNames(): LiveData<List<MaleName>>

    @Query("Select * FROM characterOptions")
    fun getFemaleNames(): LiveData<List<FemaleName>>

    @Query("Select * FROM characterOptions")
    fun getPositiveTraits(): LiveData<List<PositiveTrait>>

    @Query("Select * FROM characterOptions")
    fun getNegativeTraits(): LiveData<List<NegativeTrait>>

    @Query("Select * FROM characterOptions")
    fun getNeutralTraits(): LiveData<List<NeutralTrait>>

    @Query("Select * FROM characterOptions")
    fun getProfessions(): LiveData<List<Profession>>

    @Query("Select * FROM characterOptions")
    fun getMotivations(): LiveData<List<Motivation>>
}