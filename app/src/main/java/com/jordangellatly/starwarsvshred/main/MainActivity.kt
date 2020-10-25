package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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

    private lateinit var characterAdapter: CharacterAdapter

    override var presenter: MainContract.Presenter = MainPresenter(this, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val characterDataset = mutableListOf(
            "Han Solo",
            "Darth Vader",
            "Luke Skywalker"
        )
        characterAdapter = CharacterAdapter(characterDataset)

        val request = RetrofitBuilder.buildService(StarWarsApi::class.java)
        val call = request.getCharacterResults()

        // TODO add refresh mechanism

        call.enqueue(object : Callback<StarWarsResults> {
            override fun onResponse(
                call: Call<StarWarsResults>,
                response: Response<StarWarsResults>
            ) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    recycler_view.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = characterAdapter
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.appSearchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                characterAdapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun displayCharacterNames() {
        // TODO populate RecyclerView
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}