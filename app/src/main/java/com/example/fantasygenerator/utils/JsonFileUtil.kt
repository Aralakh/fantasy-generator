package com.example.fantasygenerator.utils

import android.content.Context
import com.example.fantasygenerator.models.Name
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

        fun loadNames(context: Context): List<Name> {
            val string = JsonFileUtil().loadJsonFile("names.json", context)
            var names: List<Name> = emptyList()
            string?.run {
                names = Gson().fromJson<List<Name>>(this)
            }

            return names
        }

        inline fun <reified T> Gson.fromJson(json: String) =
            this.fromJson<T>(json, object : TypeToken<T>() {}.type)
    }

}