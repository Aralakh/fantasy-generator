package com.example.fantasygenerator.utils

import androidx.room.TypeConverter
import com.example.fantasygenerator.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ConverterUtils {
    var gson = Gson()

    @TypeConverter
    fun professionToString(professions: List<Profession>) = gson.toJson(professions)

    @TypeConverter
    fun stringToProfession(data: String): List<Profession> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<Profession>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun motivationsToString(motivations: List<Motivation>) = gson.toJson(motivations)

    @TypeConverter
    fun stringToMotivations(data: String): List<Motivation> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<Motivation>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun nameToString(names: List<Name>) = gson.toJson(names)

    @TypeConverter
    fun stringToNames(data: String): List<Name> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<Name>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun traitsToString(traits: List<Trait>) = gson.toJson(traits)

    @TypeConverter
    fun stringToTraits(data: String): List<Trait> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<Trait>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromMotivation(motivation: Motivation) = motivation.motivation

    @TypeConverter
    fun toMotivation(value: String) = Motivation(value)

    @TypeConverter
    fun fromProfession(profession: Profession) = profession.profession

    @TypeConverter
    fun toProfession(value: String) = Profession(value)

    @TypeConverter
    fun fromTrait(trait: Trait) = "${trait.trait},${trait.type.traitType.toUpperCase()}"

    @TypeConverter
    fun toTrait(value: String): Trait {
        val ( trait, type) = value.split(",")
        return Trait(trait, TraitType.getTraitTypeByString(type))
    }

    @TypeConverter
    fun fromName(name: Name) = "${name.name},${name.gender.gender.toUpperCase()}"

    @TypeConverter
    fun toName(value: String): Name {
        val ( name, gender) = value.split(",")
        return Name(name, Gender.getGenderByString(gender))
    }

    @TypeConverter
    fun fromGender(value: Gender) = value.gender

    @TypeConverter
    fun toGender(value: String) = Gender.getGenderByString(value)

    @TypeConverter
    fun fromTraitType(value: TraitType) = value.traitType

    @TypeConverter
    fun toTraitType(value: String) = TraitType.getTraitTypeByString(value)
}