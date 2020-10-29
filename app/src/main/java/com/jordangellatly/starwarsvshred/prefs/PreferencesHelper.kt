package com.jordangellatly.starwarsvshred.prefs

interface PreferencesHelper {
    fun isCharacterFavorite(characterName: String): Boolean
    fun saveFavoritePrefs(characterName: String, isFavorite: Boolean)
    fun getOfflineCharacters(): String?
    fun saveOfflineCharacters(characterList: String)
}