package com.jordangellatly.starwarsvshred.data

class CharacterRepositoryImpl : CharacterRepository {
    override fun loadCharacters(): StarWarsCharacter {
        return StarWarsCharacter(
            "Han Solo",
            "180",
            "100",
            "brown",
            "white",
            "blue",
            "1960",
            "male"
        )
    }
}