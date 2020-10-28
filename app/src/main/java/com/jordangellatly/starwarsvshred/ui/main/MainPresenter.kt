package com.jordangellatly.starwarsvshred.ui.main

import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.network.CharacterRepository
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val charactersRepository: CharacterRepository,
) : MainContract.Presenter, CharacterRepository.LoadCharactersCallback {

    private lateinit var mainView: MainContract.View

    override fun setView(mainView: MainContract.View) {
        this.mainView = mainView
    }

    override fun onViewCreated() {
        mainView.showProgress()
        charactersRepository.loadCharacters(this)
    }

    override fun refreshCharacterDetails() {
        mainView.clearSearchQuery()
        mainView.showProgress()
        mainView.clearList()
        mainView.showRefresh()
        charactersRepository.loadCharacters(this)
    }

    override fun showCharacterDetails(character: StarWarsCharacter) {
        mainView.onCharacterSelected(character)
    }

    override fun searchList(filterString: String?) {
        mainView.filterList(filterString)
    }

    override fun onCharactersLoaded(characters: List<StarWarsCharacter>) {
        mainView.hideProgress()
        mainView.displayCharacterNames(characters)
    }

    override fun onDataNotAvailable() {
        mainView.hideProgress()
        mainView.displayError()
    }

}