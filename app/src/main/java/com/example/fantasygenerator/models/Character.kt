package com.example.fantasygenerator.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "characters")
data class Character (@PrimaryKey @ColumnInfo(name = "id") val id: UUID = java.util.UUID.randomUUID(),
                      var name: String = "Varnos",
                      var traits: Traits = Traits(),
                      var profession: String = "Adventurer",
                      var notes: String){

    override fun toString() = name
}