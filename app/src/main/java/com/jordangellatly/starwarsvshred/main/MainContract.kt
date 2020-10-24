package com.jordangellatly.starwarsvshred.main

import com.jordangellatly.starwarsvshred.BasePresenter
import com.jordangellatly.starwarsvshred.BaseView

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun onCharacterDetailSelected()
    }

    interface View : BaseView<Presenter> {
        fun displayCharacterNames()
    }
}