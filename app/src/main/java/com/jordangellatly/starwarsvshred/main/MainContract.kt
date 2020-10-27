package com.jordangellatly.starwarsvshred.main

import com.jordangellatly.starwarsvshred.BasePresenter
import com.jordangellatly.starwarsvshred.BaseView
import com.jordangellatly.starwarsvshred.data.StarWarsCharacter

interface MainContract {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun refreshCharacterDetails()
    }

    interface View : BaseView<Presenter> {
        fun displayCharacterNames(characters: List<StarWarsCharacter>)
        fun displayError()
        fun showProgress()
        fun hideProgress()
        fun showRefresh()
    }
}