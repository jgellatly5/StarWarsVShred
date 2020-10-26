package com.jordangellatly.starwarsvshred.detail

import com.jordangellatly.starwarsvshred.BasePresenter
import com.jordangellatly.starwarsvshred.BaseView

interface DetailContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun storeFavoritePreferences()
    }

    interface View : BaseView<Presenter> {
        fun displayCharacterDetails()
        fun markCharacterFavorite()
    }
}