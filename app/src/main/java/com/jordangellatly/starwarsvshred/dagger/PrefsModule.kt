package com.jordangellatly.starwarsvshred.dagger

import android.content.Context
import com.jordangellatly.starwarsvshred.prefs.AppPreferencesHelper
import com.jordangellatly.starwarsvshred.prefs.PreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {

    @Provides
    @Singleton
    fun provideAppPreferencesHelper(context: Context): PreferencesHelper = AppPreferencesHelper(context)
}