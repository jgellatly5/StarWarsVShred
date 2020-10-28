package com.jordangellatly.starwarsvshred.ui.adapter

interface ViewHolderContract {
    interface Presenter {
        fun setView(characterViewHolder: View)
        fun storeFavoritePreferences(characterName: String)
        fun onBindData(characterName: String)
    }

    interface View {
        fun setStar()
        fun removeStar()
        fun setFavoriteMessage()
        fun removeFavoriteMessage()
        fun setCharacterName(characterName: String)
    }
}