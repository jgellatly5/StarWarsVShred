package com.jordangellatly.starwarsvshred

import com.jordangellatly.starwarsvshred.data.CharacterRepository
import com.jordangellatly.starwarsvshred.data.CharacterRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
    override fun characterRepository(): CharacterRepository {
        return CharacterRepositoryImpl()
    }
}