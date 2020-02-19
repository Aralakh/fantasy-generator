package com.example.fantasygenerator.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.fantasygenerator.models.*

@Dao
interface CharacterOptionsDao {

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


}