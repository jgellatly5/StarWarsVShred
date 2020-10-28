package com.jordangellatly.starwarsvshred.ui.main

import com.jordangellatly.starwarsvshred.model.StarWarsCharacter

interface MainContract {
    interface Presenter {
        fun setView(mainView: View)
        fun onViewCreated()
        fun refreshCharacterDetails()
        fun showCharacterDetails(character: StarWarsCharacter)
        fun searchList(filterString: String?)
    }

    interface View {
        fun displayCharacterNames(characters: List<StarWarsCharacter>)
        fun displayError()
        fun showProgress()
        fun hideProgress()
        fun showRefresh()
        fun onCharacterSelected(character: StarWarsCharacter)
        fun clearList()
        fun filterList(filterString: String?)
        fun clearSearchQuery()
    }
}