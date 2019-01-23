package com.bristot.data.client.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type
import javax.inject.Inject

class PreferenceImpl @Inject constructor() : Preference {

    companion object {
        private const val APP_PREFERENCES = "APP_PREFERENCES"
        lateinit var sharedPreferences: SharedPreferences

        fun start(context: Context) {
            sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        }
    }

    private val gson = Gson()

    override fun <T> get(key: String, type: Type): T {
        val stringValue = sharedPreferences.getString(key, null)
                ?: throw Preference.NotFoundException()
        return gson.fromJson(stringValue, type) ?: throw Preference.NotFoundException()
    }

    override fun set(key: String, value: Any?) {
        if (value == null) {
            sharedPreferences.edit().remove(key).apply()
        } else {
            sharedPreferences.edit().putString(key, gson.toJson(value)).apply()
        }
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}