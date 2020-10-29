package com.jordangellatly.starwarsvshred.ui.adapter

import com.jordangellatly.starwarsvshred.prefs.AppPreferencesHelper
import javax.inject.Inject

class ViewHolderPresenter @Inject constructor(
    private val appPreferencesHelper: AppPreferencesHelper
) : ViewHolderContract.Presenter  {
    private lateinit var characterViewHolder: ViewHolderContract.View

    override fun setView(characterViewHolder: ViewHolderContract.View) {
        this.characterViewHolder = characterViewHolder
    }

    override fun storeFavoritePreferences(characterName: String) {
        val isFavorite = appPreferencesHelper.isCharacterFavorite(characterName)
        if (isFavorite) {
            characterViewHolder.removeStar()
            characterViewHolder.removeFavoriteMessage()
        } else {
            characterViewHolder.setStar()
            characterViewHolder.setFavoriteMessage()
        }
        appPreferencesHelper.saveFavoritePrefs(characterName, isFavorite)
    }

    override fun onBindData(characterName: String) {
        characterViewHolder.setCharacterName(characterName)
        val isFavorite = appPreferencesHelper.isCharacterFavorite(characterName)
        if (isFavorite) {
            characterViewHolder.setStar()
        } else {
            characterViewHolder.removeStar()
        }
    }
}