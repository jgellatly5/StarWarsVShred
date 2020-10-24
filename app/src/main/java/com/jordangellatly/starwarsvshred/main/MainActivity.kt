package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jordangellatly.starwarsvshred.DependencyInjectorImpl
import com.jordangellatly.starwarsvshred.R

class MainActivity : AppCompatActivity(), MainContract.View {

    override var presenter: MainContract.Presenter = MainPresenter(this, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun displayCharacterNames() {
        // TODO populate RecyclerView
    }
}