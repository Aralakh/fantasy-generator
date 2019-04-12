package com.example.fantasygenerator.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "characters")
data class Character (@PrimaryKey val id: UUID = UUID.randomUUID(),
                      var name: String = "Varnos",
                      var traits: Traits = Traits(),
                      var motivation: String = "Avenge a fallen friend",
                      var profession: String = "Adventurer",
                      var notes: String){

    override fun toString() = name
}