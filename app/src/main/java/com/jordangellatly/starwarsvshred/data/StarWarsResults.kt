package com.jordangellatly.starwarsvshred.data

data class StarWarsResults(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarWarsCharacter>
)