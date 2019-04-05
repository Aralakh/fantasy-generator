package com.example.fantasygenerator.models

import java.util.*

data class Character (val UUID: UUID, var name: String, var traits: Traits, var profession: String, var notes: String){
}