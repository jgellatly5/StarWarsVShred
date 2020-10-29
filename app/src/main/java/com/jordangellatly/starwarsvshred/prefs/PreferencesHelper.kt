package com.jordangellatly.starwarsvshred.prefs

interface PreferencesHelper {
    fun isCharacterFavorite(characterName: String): Boolean
    fun savePrefs(characterName: String, isFavorite: Boolean)
    fun getOfflineCharacters(): String?
}