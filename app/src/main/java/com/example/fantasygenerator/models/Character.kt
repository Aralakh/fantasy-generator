package com.example.fantasygenerator.models

import java.util.*

data class Character (val UUID: UUID = java.util.UUID.randomUUID(), var name: String = "Varnos",
                      var traits: Traits = Traits(), var profession: String = "Adventurer", var notes: String)