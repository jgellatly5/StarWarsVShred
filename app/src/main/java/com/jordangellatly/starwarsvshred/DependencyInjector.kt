package com.jordangellatly.starwarsvshred

import com.jordangellatly.starwarsvshred.data.CharacterRepository

interface DependencyInjector {
    fun characterRepository(): CharacterRepository
}