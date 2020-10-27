package com.jordangellatly.starwarsvshred.ui.detail

import javax.inject.Inject

class DetailPresenter @Inject constructor() : DetailContract.Presenter {

    private lateinit var detailView: DetailContract.View

    override fun setView(detailView: DetailContract.View) {
        this.detailView = detailView
    }

    override fun onViewCreated() {
        detailView.displayCharacterDetails()
    }

    override fun storeFavoritePreferences() {
        detailView.markCharacterFavorite()
    }
}