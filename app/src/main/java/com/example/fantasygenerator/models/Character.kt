package com.example.fantasygenerator.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "characters")
data class Character (@PrimaryKey val id: String = UUID.randomUUID().toString(),
                      var name: String = "Varnos",
                      var positiveTrait: String = "Brave",
                      var negativeTrait: String = "Alcoholic",
                      var neutralTrait: String = "Decisive",
                      var motivation: String = "Avenge a fallen friend",
                      var profession: String = "Adventurer",
                      var notes: String = ""){

    override fun toString() = name
}