package com.jordangellatly.starwarsvshred.main

import com.jordangellatly.starwarsvshred.BasePresenter
import com.jordangellatly.starwarsvshred.BaseView

interface MainContract {
    interface MainPresenter : BasePresenter {
        fun onViewCreated()
        fun onCharacterDetailSelected()
    }

    interface MainView : BaseView<MainPresenter> {
        fun displayCharacterNames()
    }
}