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
    fun positiveTraitsToString(positiveTraits: List<PositiveTrait>) = gson.toJson(positiveTraits)

    @TypeConverter
    fun stringToPositiveTraits(data: String): List<PositiveTrait> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<PositiveTrait>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun negativeTraitsToString(negativeTraits: List<NegativeTrait>) = gson.toJson(negativeTraits)

    @TypeConverter
    fun stringToNegativeTraits(data: String): List<NegativeTrait> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<NegativeTrait>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun neutralTraitsToString(neutralTraits: List<NeutralTrait>) = gson.toJson(neutralTraits)

    @TypeConverter
    fun stringToNeutralTraits(data: String): List<NeutralTrait> {
        if(data == null)
            return Collections.emptyList()
        val listType = object: TypeToken<List<NeutralTrait>>() {}.type
        return gson.fromJson(data, listType)
    }
}