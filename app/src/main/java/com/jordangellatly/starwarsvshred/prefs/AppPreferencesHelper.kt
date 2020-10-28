package com.jordangellatly.starwarsvshred.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context) : PreferencesHelper {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)

    override fun isCharacterFavorite(name: String): Boolean = sharedPrefs.getBoolean(name, false)
}