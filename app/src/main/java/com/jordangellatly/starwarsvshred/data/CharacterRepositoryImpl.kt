package com.jordangellatly.starwarsvshred.data

class CharacterRepositoryImpl : CharacterRepository {
    override fun loadCharacters(): StarWarsCharacter {
        return StarWarsCharacter("Han Solo")
    }
}