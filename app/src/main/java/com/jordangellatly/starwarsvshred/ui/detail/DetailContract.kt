package com.jordangellatly.starwarsvshred.ui.detail

interface DetailContract {
    interface Presenter {
        fun setView(detailView: View)
        fun onViewCreated(characterName: String)
        fun storeFavoritePreferences(characterName: String)
    }

    interface View {
        fun displayCharacterDetails()
        fun setStar()
        fun removeStar()
        fun setFavoriteMessage()
        fun removeFavoriteMessage()
    }
}