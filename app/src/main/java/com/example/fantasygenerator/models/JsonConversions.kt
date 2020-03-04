package com.example.fantasygenerator.models

data class OldNames(
    val maleNames: List<String>,
    val femaleNames: List<String>)

data class OldTraits(
    val positive: List<String>,
    val negative: List<String>,
    val neutral: List<String>
)