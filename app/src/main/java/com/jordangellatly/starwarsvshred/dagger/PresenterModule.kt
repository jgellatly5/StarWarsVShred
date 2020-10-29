package com.jordangellatly.starwarsvshred.dagger

import com.jordangellatly.starwarsvshred.network.CharacterRepositoryImpl
import com.jordangellatly.starwarsvshred.prefs.AppPreferencesHelper
import com.jordangellatly.starwarsvshred.ui.adapter.ViewHolderContract
import com.jordangellatly.starwarsvshred.ui.adapter.ViewHolderPresenter
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
    fun provideMainActivityPresenter(characterRepository: CharacterRepositoryImpl, appPreferencesHelper: AppPreferencesHelper): MainContract.Presenter =
        MainPresenter(characterRepository, appPreferencesHelper)

    @Provides
    @Singleton
    fun provideDetailActivityPresenter(appPreferencesHelper: AppPreferencesHelper): DetailContract.Presenter =
        DetailPresenter(appPreferencesHelper)

    @Provides
    fun provideViewHolderPresenter(appPreferencesHelper: AppPreferencesHelper): ViewHolderContract.Presenter =
        ViewHolderPresenter(appPreferencesHelper)
}