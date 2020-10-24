package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.DependencyInjectorImpl
import com.jordangellatly.starwarsvshred.R

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var recyclerView: RecyclerView

    override var presenter: MainContract.Presenter = MainPresenter(this, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterDataset = listOf(
            "Han Solo",
            "Darth Vader",
            "Luke Skywalker"
        )

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CharacterAdapter(characterDataset)
        }
    }

    override fun displayCharacterNames() {
        // TODO populate RecyclerView
    }
}