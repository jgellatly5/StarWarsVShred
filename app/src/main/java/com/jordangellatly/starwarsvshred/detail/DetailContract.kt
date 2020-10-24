package com.jordangellatly.starwarsvshred.detail

import com.jordangellatly.starwarsvshred.BasePresenter
import com.jordangellatly.starwarsvshred.BaseView

interface DetailContract {
    interface DetailPresenter : BasePresenter {
        fun onViewCreated()
        fun markCharacterFavorite()
    }

    interface DetailView : BaseView<DetailPresenter> {
        fun displayCharacterDetails()
    }
}