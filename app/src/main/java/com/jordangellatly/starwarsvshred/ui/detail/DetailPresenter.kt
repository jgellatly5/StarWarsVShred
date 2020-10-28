package com.jordangellatly.starwarsvshred.ui.detail

import com.jordangellatly.starwarsvshred.prefs.PreferencesHelper
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val appPreferencesHelper: PreferencesHelper
) : DetailContract.Presenter {

    private lateinit var detailView: DetailContract.View

    override fun setView(detailView: DetailContract.View) {
        this.detailView = detailView
    }

    override fun onViewCreated(characterName: String) {
        detailView.displayCharacterDetails()
        if (appPreferencesHelper.isCharacterFavorite(characterName)) {
            detailView.setStar()
        } else {
            detailView.removeStar()
        }
    }

    override fun storeFavoritePreferences(characterName: String) {
        val isFavorite = appPreferencesHelper.isCharacterFavorite(characterName)
        if (isFavorite) {
            detailView.removeStar()
            detailView.removeFavoriteMessage()
        } else {
            detailView.setStar()
            detailView.setFavoriteMessage()
        }
        appPreferencesHelper.savePrefs(characterName, isFavorite)
    }
}