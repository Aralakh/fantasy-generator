package com.example.fantasygenerator.utils

import android.content.Context
import com.example.fantasygenerator.models.Names
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

        fun loadNames(): Names? {
            val string = JsonFileUtil().loadJsonFile("assets/names.json")
            var names: Names? = null
            string?.run {
                names = Gson().fromJson<Names>(this)
            }

            return names
        }

        inline fun <reified T> Gson.fromJson(json: String) =
            this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    }

}