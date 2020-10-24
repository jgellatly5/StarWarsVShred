package com.jordangellatly.starwarsvshred.data

class CharactersRepositoryImpl : CharacterRepository {
    override fun loadCharacters(): StarWarsCharacter {
        return StarWarsCharacter("Han Solo")
    }
}