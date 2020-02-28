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
    fun insertMaleName(maleName: Name)

    @Insert
    fun insertFemaleName(femaleName: Name)

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
    fun updateMaleName(maleName: Name)

    @Update
    fun updateFemaleName(femaleName: Name)

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
    fun deleteMaleName(maleName: Name)

    @Delete
    fun deleteFemaleName(femaleName: Name)

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

    @Query("Select maleNames FROM characterOptions")
    fun getMaleNames(): LiveData<List<Name>>

    @Query("Select femaleNames FROM characterOptions")
    fun getFemaleNames(): LiveData<List<Name>>

    @Query("Select positiveTraits FROM characterOptions")
    fun getPositiveTraits(): LiveData<List<PositiveTrait>>

    @Query("Select negativeTraits FROM characterOptions")
    fun getNegativeTraits(): LiveData<List<NegativeTrait>>

    @Query("Select neutralTraits FROM characterOptions")
    fun getNeutralTraits(): LiveData<List<NeutralTrait>>

    @Query("Select professions FROM characterOptions")
    fun getProfessions(): LiveData<List<Profession>>

    @Query("Select motivations FROM characterOptions")
    fun getMotivations(): LiveData<List<Motivation>>
}