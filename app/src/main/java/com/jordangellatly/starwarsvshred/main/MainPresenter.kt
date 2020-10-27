package com.jordangellatly.starwarsvshred.main

import com.jordangellatly.starwarsvshred.DependencyInjector
import com.jordangellatly.starwarsvshred.data.CharacterRepository
import com.jordangellatly.starwarsvshred.data.StarWarsCharacter

class MainPresenter(
    mainView: MainContract.View,
    dependencyInjector: DependencyInjector
) : MainContract.Presenter, CharacterRepository.LoadCharactersCallback {
    private val charactersRepository: CharacterRepository = dependencyInjector.characterRepository()
    private var view: MainContract.View? = mainView

    override fun onViewCreated() {
        view?.showProgress()
        charactersRepository.loadCharacters(this)
    }

    override fun refreshCharacterDetails() {
        view?.showProgress()
        view?.showRefresh()
        charactersRepository.loadCharacters(this)
    }

    override fun showCharacterDetails(character: StarWarsCharacter) {
        view?.onCharacterSelected(character)
    }

    override fun onDestroy() {
        view = null
    }

    override fun onCharactersLoaded(characters: List<StarWarsCharacter>) {
        view?.hideProgress()
        view?.displayCharacterNames(characters)
    }

    override fun onDataNotAvailable() {
        view?.hideProgress()
        view?.displayError()
    }

}