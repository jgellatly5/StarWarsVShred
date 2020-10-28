package com.jordangellatly.starwarsvshred.ui.detail

interface DetailContract {
    interface Presenter {
        fun setView(detailView: View)
        fun onViewCreated(characterName: String)
        fun storeFavoritePreferences()
    }

    interface View {
        fun displayCharacterDetails()
        fun markCharacterFavorite()
        fun setStar()
        fun removeStar()
    }
}