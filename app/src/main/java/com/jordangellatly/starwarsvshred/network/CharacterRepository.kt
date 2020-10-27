package com.jordangellatly.starwarsvshred.network

import com.jordangellatly.starwarsvshred.model.StarWarsCharacter

interface CharacterRepository {
    interface LoadCharactersCallback {
        fun onCharactersLoaded(characters: List<StarWarsCharacter>)
        fun onDataNotAvailable()
    }

    fun loadCharacters(callback: LoadCharactersCallback)
}