package com.jordangellatly.starwarsvshred.detail

class DetailPresenter(
    detailView: DetailContract.View
) : DetailContract.Presenter {
    private var view: DetailContract.View? = detailView

    override fun onViewCreated() {
        loadDataSingleCharacter()
    }

    override fun markCharacterFavorite() {
        // TODO store character as favorite in SharedPrefs
    }

    override fun onDestroy() {
        view = null
    }

    private fun loadDataSingleCharacter() {
        view?.displayCharacterDetails()
    }
}