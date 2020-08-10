package com.example.fantasygenerator.utils

import android.content.Context
import android.util.Log
import com.example.fantasygenerator.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileOutputStream
import java.io.IOException

open class JsonFileUtil {
    fun loadJsonFile(filePath: String, context: Context): String? {
        var json: String? = null
        try {
            context.assets
                .open(filePath)
                .bufferedReader()
                .use { json = it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
        }

        return json
    }

    companion object {
        fun loadOldTraits(context: Context): OldTraits {
            val string = JsonFileUtil().loadJsonFile("attributes.json", context)
            var traits = OldTraits(positive = emptyList(), negative = emptyList(), neutral = emptyList())
            string?.run {
                traits = Gson().fromJson(this)
            }
            return traits
        }

        fun loadOldNames(context: Context): OldNames {
            val string = JsonFileUtil().loadJsonFile("names.json", context)
            var names = OldNames(femaleNames = emptyList(), maleNames = emptyList())
            string?.run {
                names = Gson().fromJson(this)
            }
            return names
        }

        fun saveNames(names: OldNames, context: Context) {
            val newNames = convertNames(names)
            if(newNames.isNotEmpty()) {
                val file = "newNames.json"
                val namesJson = Gson().toJson(newNames)
                try {
                    val fos: FileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
                    namesJson?.run {
                        fos.write(this.toByteArray())
                    }
                } catch (ioe: IOException) {
                    Log.d("json name conversion ", ioe.message)
                }
            }
        }

        fun saveTraits(traits: OldTraits, context: Context) {
            val newTraits = convertTraits(traits)
            if(newTraits.isNotEmpty()) {
                val file = "traits.json"
                val traitsJson = Gson().toJson(newTraits)
                try {
                    val fos: FileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE)
                    traitsJson?.run {
                        fos.write(this.toByteArray())
                    }
                } catch (ioe: IOException) {
                    Log.d("json trait conversion ", ioe.message)
                }
            }
        }

        private fun convertTraits(traits: OldTraits): List<Trait> {
            val newTraits = mutableListOf<Trait>()
            traits.run {
                positive.forEach {
                    trait -> newTraits.add(Trait(trait, TraitType.POSITIVE))
                }

                negative.forEach {
                    trait -> newTraits.add(Trait(trait, TraitType.NEGATIVE))
                }

                neutral.forEach {
                    trait -> newTraits.add(Trait(trait, TraitType.NEUTRAL))
                }
            }

            return newTraits
        }

       private fun convertNames(names: OldNames): List<Name> {
            val newNames = mutableListOf<Name>()
            names.run {
                maleNames.forEach {
                    name -> newNames.add(Name(name, Gender.MALE))
                }

                femaleNames.forEach {
                    name -> newNames.add(Name(name, Gender.FEMALE))
                }
            }
            return newNames
        }

        fun loadNames(context: Context): List<Name> {
            val string = JsonFileUtil().loadJsonFile("newNames.json", context)
            var names: List<Name> = emptyList()
            string?.run {
                names = Gson().fromJson<List<Name>>(this)
            }

            return names
        }

        fun loadTraits(context: Context): List<Trait> {
            val string = JsonFileUtil().loadJsonFile("traits.json", context)
            var traits: List<Trait> = emptyList()
            string?.run {
                traits = Gson().fromJson<List<Trait>>(this)
            }

            return traits
        }

        fun loadMotivations(context: Context): List<Motivation> {
            val string = JsonFileUtil().loadJsonFile("motivations.json", context)
            var motivations: List<Motivation> = emptyList()
            string?.run {
                motivations = Gson().fromJson<List<Motivation>>(this)
            }

            return motivations
        }

        fun loadProfessions(context: Context): List<Profession> {
            val string = JsonFileUtil().loadJsonFile("professions.json", context)
            var professions: List<Profession> = emptyList()
            string?.run {
                professions = Gson().fromJson<List<Profession>>(this)
            }
            return professions
        }

        inline fun <reified T> Gson.fromJson(json: String) =
            this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    }

}