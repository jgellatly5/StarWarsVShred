package com.jordangellatly.starwarsvshred.data

data class StarWarsResults(
    private val count: Int,
    private val next: String?,
    private val previous: String?,
    private val results: List<StarWarsCharacter>
)