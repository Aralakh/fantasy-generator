package com.example.fantasygenerator.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "names", primaryKeys = ["name", "gender"])
data class Names(
    val name: Name,
    val gender: Gender
)

@Entity
data class Name(@PrimaryKey val name: String)

@Entity(tableName = "traits", primaryKeys = ["trait", "type"])
data class Traits(
    val trait: String,
    val type: TraitType
)

enum class Gender {
    FEMALE, MALE
}

enum class TraitType {
    POSITIVE, NEGATIVE, NEUTRAL
}

@Entity(tableName = "professions")
data class Profession(@PrimaryKey val profession: String)

@Entity(tableName = "motivations")
data class Motivation(@PrimaryKey val motivation: String)
