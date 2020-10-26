package com.jordangellatly.starwarsvshred.data

interface CharacterRepository {
    interface LoadCharactersCallback {
        fun onCharactersLoaded(characters: List<StarWarsCharacter>)
        fun onDataNotAvailable()
    }

    fun loadCharacters(callback: LoadCharactersCallback)
}