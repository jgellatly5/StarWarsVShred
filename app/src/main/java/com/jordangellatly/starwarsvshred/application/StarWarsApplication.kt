package com.jordangellatly.starwarsvshred.application

import android.app.Application
import com.jordangellatly.starwarsvshred.dagger.AppComponent
import com.jordangellatly.starwarsvshred.dagger.AppModule
import com.jordangellatly.starwarsvshred.dagger.DaggerAppComponent

class StarWarsApplication : Application() {
    lateinit var starWarsComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        starWarsComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}