package com.jordangellatly.starwarsvshred.dagger

import com.jordangellatly.starwarsvshred.ui.detail.DetailActivity
import com.jordangellatly.starwarsvshred.ui.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, PresenterModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
}