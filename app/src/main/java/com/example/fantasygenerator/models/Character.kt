package com.example.fantasygenerator.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "characters")
data class Character (@PrimaryKey val id: String = UUID.randomUUID().toString(),
                      var name: Name = Name("Varnos", Gender.MALE),
                      var positiveTrait: Trait = Trait("Brave", TraitType.POSITIVE),
                      var negativeTrait: Trait = Trait("Alcoholic", TraitType.NEGATIVE),
                      var neutralTrait: Trait = Trait("Decisive", TraitType.NEUTRAL),
                      var motivation: Motivation = Motivation("Avenge a fallen friend"),
                      var profession: Profession = Profession("Adventurer"),
                      var notes: String = "")
{

    override fun toString() = name.name
}

//@Entity
//data class CharacterTraits(
//    var positiveTrait: Trait = Trait("Brave", TraitType.POSITIVE),
//    var negativeTrait: Trait = Trait("Alcoholic", TraitType.NEGATIVE),
//    var neutralTrait: Trait = Trait("Decisive", TraitType.NEUTRAL)
//)
