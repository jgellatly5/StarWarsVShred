package com.jordangellatly.starwarsvshred.detail

class DetailPresenter(
    detailView: DetailContract.View
) : DetailContract.Presenter {
    private var view: DetailContract.View? = detailView

    override fun onViewCreated() {
        view?.displayCharacterDetails()
    }

    override fun storeFavoritePreferences() {
        view?.markCharacterFavorite()
    }

    override fun onDestroy() {
        view = null
    }
}