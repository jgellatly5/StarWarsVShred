package com.jordangellatly.starwarsvshred.ui.main

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.network.CharacterRepository
import com.jordangellatly.starwarsvshred.prefs.PreferencesHelper
import java.lang.reflect.Type
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val charactersRepository: CharacterRepository,
    private val appPreferencesHelper: PreferencesHelper
) : MainContract.Presenter, CharacterRepository.LoadCharactersCallback {

    private lateinit var mainView: MainContract.View

    override fun setView(mainView: MainContract.View) {
        this.mainView = mainView
    }

    override fun onViewCreated(characterList: List<StarWarsCharacter>) {
        if (characterList.isEmpty()) {
            mainView.showProgress()
            charactersRepository.loadCharacters(this)
        } else {
            mainView.setupRecyclerView()
            mainView.hideProgress()
        }
    }

    override fun refreshCharacterDetails() {
        if (mainView.isWifiEnabled()) {
            mainView.clearSearchQuery()
            mainView.showProgress()
            mainView.clearList()
            mainView.showRefresh()
            charactersRepository.loadCharacters(this)
        } else {
            mainView.showWifiDisabled()
        }
    }

    override fun showCharacterDetails(character: StarWarsCharacter) {
        mainView.onCharacterSelected(character)
    }

    override fun searchList(filterString: String?) {
        mainView.filterList(filterString)
    }

    override fun retrieveOfflineCharacterList(): ArrayList<StarWarsCharacter> {
        val offlineCharacterListString = appPreferencesHelper.getOfflineCharacters()
        return if (!offlineCharacterListString.equals("")) {
            val collectionType: Type = object : TypeToken<List<StarWarsCharacter?>?>() {}.type
            val characters: List<StarWarsCharacter> = Gson().fromJson(offlineCharacterListString, collectionType)
            characters as ArrayList<StarWarsCharacter>
        } else {
            emptyList<StarWarsCharacter>() as ArrayList<StarWarsCharacter>
        }
    }

    override fun onCharactersLoaded(characters: List<StarWarsCharacter>) {
        mainView.hideProgress()
        mainView.addCharacterNamesFromApi(characters)
        mainView.setupRecyclerView()
    }

    override fun onDataNotAvailable() {
        mainView.hideProgress()
        mainView.displayError()
    }

}