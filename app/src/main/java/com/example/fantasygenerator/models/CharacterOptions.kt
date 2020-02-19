package com.example.fantasygenerator.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characterOptions")
data class CharacterOptions(
    @Embedded
    val names: Names,
    @Embedded
    val traits: Traits,
    val professions: List<Profession>,
    val motivations: List<Motivation>
)


@Entity
data class Names(
    val maleNames: List<MaleName>,
    val femaleNames: List<FemaleName>
)

@Entity
data class MaleName(@PrimaryKey var maleName: String)

@Entity
data class FemaleName(@PrimaryKey
                 var femaleName: String)

@Entity
data class Traits(
    val positiveTraits: List<PositiveTrait>,
    val negativeTraits: List<NegativeTrait>,
    val neutralTraits: List<NeutralTrait>
)

@Entity
data class PositiveTrait(@PrimaryKey var positiveTrait: String)

@Entity
data class NegativeTrait(@PrimaryKey var negativeTrait: String)

@Entity
data class NeutralTrait(@PrimaryKey var neutralTrait: String)

@Entity
data class Profession(@PrimaryKey var profession: String)

@Entity
data class Motivation(@PrimaryKey var motivation: String)
