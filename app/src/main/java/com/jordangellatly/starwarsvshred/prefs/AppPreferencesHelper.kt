package com.jordangellatly.starwarsvshred.prefs

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class AppPreferencesHelper @Inject constructor(context: Context) : PreferencesHelper {
    private val favoriteSharedPrefs: SharedPreferences = context.getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)
    private val offlineCharactersSharedPrefs: SharedPreferences = context.getSharedPreferences(Const.OFFLINE, Context.MODE_PRIVATE)

    override fun isCharacterFavorite(characterName: String): Boolean = favoriteSharedPrefs.getBoolean(characterName, false)

    override fun saveFavoritePrefs(characterName: String, isFavorite: Boolean) {
        with(favoriteSharedPrefs.edit()) {
            putBoolean(characterName, !isFavorite)
            apply()
        }
    }

    override fun getOfflineCharacters(): String? = offlineCharactersSharedPrefs.getString(Const.CHARACTER_LIST, "")

    override fun saveOfflineCharacters(characterList: String) {
        with(offlineCharactersSharedPrefs.edit()) {
            putString(Const.CHARACTER_LIST, characterList)
            apply()
        }
    }
}