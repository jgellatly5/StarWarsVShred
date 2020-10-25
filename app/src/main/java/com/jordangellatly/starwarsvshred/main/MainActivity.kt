package com.jordangellatly.starwarsvshred.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
    private val characterDataset: MutableList<String> = mutableListOf()

    override var presenter: MainContract.Presenter = MainPresenter(this, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterAdapter = CharacterAdapter(characterDataset)

        makeNetworkCall()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.search)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                progress_bar.visibility = View.VISIBLE
                makeNetworkCall()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Presenter
    override fun displayCharacterNames() {
        // TODO populate RecyclerView
    }

    private fun makeNetworkCall() {
        val request = RetrofitBuilder.buildService(StarWarsApi::class.java)
        val call = request.getCharacterResults()

        call.enqueue(object : Callback<StarWarsResults> {
            override fun onResponse(
                call: Call<StarWarsResults>,
                response: Response<StarWarsResults>
            ) {
                if (response.isSuccessful) {
                    progress_bar.visibility = View.GONE
                    val characters = response.body()!!.results
                    for (character in characters) {
                        characterDataset.add(character.name)
                    }
                    recycler_view.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = characterAdapter
                    }
                }
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

    companion object {
        private const val TAG = "MainActivity"
    }
}