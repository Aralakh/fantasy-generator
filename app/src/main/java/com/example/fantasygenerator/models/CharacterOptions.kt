package com.example.fantasygenerator.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.lang.IllegalArgumentException

@Entity(tableName = "names", primaryKeys = ["name", "gender"])
data class Name (
    val name: String,
    val gender: Gender
)

@Entity(tableName = "trait", primaryKeys = ["trait", "type"])
data class Trait (
    val trait: String,
    val type: TraitType
)

@Entity
enum class Gender (val gender: String) {
    FEMALE("female"),
    MALE("male"),
    OTHER("other");

    companion object {
        fun getGenderByString(gender: String): Gender {
            return try {
                valueOf(gender.toUpperCase())
            } catch (exception: IllegalArgumentException) {
                OTHER
            }
        }
    }
}

@Entity
enum class TraitType (val traitType: String) {
    POSITIVE("positive"),
    NEGATIVE("negative"),
    NEUTRAL("neutral");

    companion object {
        fun getTraitTypeByString(traitType: String): TraitType {
            return try {
                valueOf(traitType.toUpperCase())
            } catch (exception: IllegalArgumentException) {
                NEUTRAL
            }
        }
    }
}

@Entity(tableName = "professions")
data class Profession(@PrimaryKey val profession: String)

@Entity(tableName = "motivations")
data class Motivation(@PrimaryKey val motivation: String)
