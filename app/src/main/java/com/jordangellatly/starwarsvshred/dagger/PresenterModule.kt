package com.jordangellatly.starwarsvshred.dagger

import com.jordangellatly.starwarsvshred.network.CharacterRepositoryImpl
import com.jordangellatly.starwarsvshred.ui.detail.DetailContract
import com.jordangellatly.starwarsvshred.ui.detail.DetailPresenter
import com.jordangellatly.starwarsvshred.ui.main.MainContract
import com.jordangellatly.starwarsvshred.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule {
    @Provides
    @Singleton
    fun provideMainActivityPresenter(characterRepository: CharacterRepositoryImpl): MainContract.Presenter =
        MainPresenter(characterRepository)

    @Provides
    @Singleton
    fun provideDetailActivityPresenter(): DetailContract.Presenter = DetailPresenter()
}