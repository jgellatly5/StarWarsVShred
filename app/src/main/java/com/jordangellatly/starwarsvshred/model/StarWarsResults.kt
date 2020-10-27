package com.jordangellatly.starwarsvshred.model

data class StarWarsResults(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<StarWarsCharacter>
)