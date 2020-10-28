package com.jordangellatly.starwarsvshred.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context, prefFileName: String) : PreferencesHelper {
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE)

    override fun isCharacterFavorite(): Boolean = sharedPrefs.getBoolean(IS_FAVORITE, false)

    companion object {
        private const val IS_FAVORITE = "IS_FAVORITE"
    }
}