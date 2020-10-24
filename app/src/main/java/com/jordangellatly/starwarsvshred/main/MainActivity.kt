package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordangellatly.starwarsvshred.DependencyInjectorImpl
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.data.RetrofitBuilder
import com.jordangellatly.starwarsvshred.data.StarWarsApi
import com.jordangellatly.starwarsvshred.data.StarWarsResults
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), MainContract.View {

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

                val characterDataset = listOf(
                    "Han Solo",
                    "Darth Vader",
                    "Luke Skywalker"
                )

                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    recycler_view.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = CharacterAdapter(characterDataset)
                    }
                }
                Log.w(TAG, "onResponse: ${response.body()}")
            }

            override fun onFailure(call: Call<StarWarsResults>, t: Throwable) {
                // TODO improve handling this
                progress_bar.visibility = View.GONE
                Toast.makeText(
                    this@MainActivity,
                    "Could not process the request. Please check your internet connection.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun displayCharacterNames() {
        // TODO populate RecyclerView
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}