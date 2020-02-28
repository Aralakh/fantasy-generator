package com.example.fantasygenerator.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterOptions")
data class CharacterOptions(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @Embedded
    val names: Names,
    @Embedded
    val traits: Traits,
    val professions: List<Profession>,
    val motivations: List<Motivation>
)

data class Names(
    val maleNames: List<Name>,
    val femaleNames: List<Name>
)

@Entity
data class Name(@PrimaryKey val name: String)

data class Traits(
    val positiveTraits: List<PositiveTrait>,
    val negativeTraits: List<NegativeTrait>,
    val neutralTraits: List<NeutralTrait>
)

@Entity
data class PositiveTrait(@PrimaryKey val positiveTrait: String)

@Entity
data class NegativeTrait(@PrimaryKey val negativeTrait: String)

@Entity
data class NeutralTrait(@PrimaryKey val neutralTrait: String)

@Entity
data class Profession(@PrimaryKey val profession: String)

@Entity
data class Motivation(@PrimaryKey val motivation: String)
