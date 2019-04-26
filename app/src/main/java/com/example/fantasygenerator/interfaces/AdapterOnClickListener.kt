package com.example.fantasygenerator.interfaces

import com.example.fantasygenerator.models.Character

interface AdapterOnClickListener {
    fun itemClicked(character: Character)
}