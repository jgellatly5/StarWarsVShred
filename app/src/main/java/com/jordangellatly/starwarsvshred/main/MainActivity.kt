package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jordangellatly.starwarsvshred.DependencyInjectorImpl
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.data.RetrofitBuilder
import com.jordangellatly.starwarsvshred.data.StarWarsApi
import com.jordangellatly.starwarsvshred.data.StarWarsResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var recyclerView: RecyclerView

    override var presenter: MainContract.Presenter = MainPresenter(this, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = RetrofitBuilder.buildService(StarWarsApi::class.java)
        val call = request.getCharacterResults()
        call.enqueue(object : Callback<StarWarsResults> {
            override fun onResponse(
                call: Call<StarWarsResults>,
                response: Response<StarWarsResults>
            ) {
                Log.w(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<StarWarsResults>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

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

    companion object {
        private const val TAG = "MainActivity"
    }
}