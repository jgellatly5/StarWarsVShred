package com.jordangellatly.starwarsvshred.main

import com.jordangellatly.starwarsvshred.DependencyInjector
import com.jordangellatly.starwarsvshred.data.CharacterRepository

class MainPresenter(
    mainView: MainContract.View,
    dependencyInjector: DependencyInjector
) : MainContract.Presenter {
    private val charactersRepository: CharacterRepository = dependencyInjector.characterRepository()
    private var view: MainContract.View? = mainView

    override fun onViewCreated() {
        loadCharacters()
    }

    override fun onCharacterDetailSelected() {
        // TODO pass data to intent to DetailActivity
    }

    override fun onDestroy() {
        view = null
    }

    private fun loadCharacters() {
        val characters = charactersRepository.loadCharacters()
        view?.displayCharacterNames()
    }

}