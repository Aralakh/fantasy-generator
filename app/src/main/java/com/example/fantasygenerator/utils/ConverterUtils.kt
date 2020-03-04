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
    fun genderToString(gender: Gender) = gender.toString()

    @TypeConverter
    fun stringToGender(data: String) = Gender.getGenderByString(data)

    @TypeConverter
    fun traitsToString(traits: TraitType) = traits.toString()

    @TypeConverter
    fun stringToTraits(data: String) = TraitType.getTraitTypeByString(data)
}