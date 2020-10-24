package com.jordangellatly.starwarsvshred.data

interface CharacterRepository {
    fun loadCharacters(): StarWarsCharacter
}