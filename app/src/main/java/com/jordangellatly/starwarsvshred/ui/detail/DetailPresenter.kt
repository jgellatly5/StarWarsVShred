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

    override fun onViewCreated(name: String) {
        detailView.displayCharacterDetails()
        if (appPreferencesHelper.isCharacterFavorite(name)) {
            detailView.setStar()
        } else {
            detailView.removeStar()
        }
    }

    override fun storeFavoritePreferences() {
        detailView.markCharacterFavorite()
    }
}