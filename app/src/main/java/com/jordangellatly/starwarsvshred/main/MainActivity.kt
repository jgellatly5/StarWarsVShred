package com.jordangellatly.starwarsvshred.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordangellatly.starwarsvshred.DependencyInjectorImpl
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.data.StarWarsCharacter
import com.jordangellatly.starwarsvshred.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.parceler.Parcels

class MainActivity : AppCompatActivity(), MainContract.View,
    CharacterAdapter.CharacterAdapterListener {

    private lateinit var characterAdapter: CharacterAdapter
    private var characterDataset: MutableList<StarWarsCharacter> = mutableListOf()

    // BaseView
    override var presenter: MainContract.Presenter =
        MainPresenter(this@MainActivity, DependencyInjectorImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        characterAdapter = CharacterAdapter(this@MainActivity, characterDataset, this@MainActivity)

        // MainContract.Presenter
        presenter.onViewCreated()
    }

    // BasePresenter
    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

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
                characterDataset = mutableListOf()
                Toast.makeText(this@MainActivity, "Refreshing...", Toast.LENGTH_SHORT).show()
                presenter.refreshCharacterDetails()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // MainContract.View
    override fun displayCharacterNames(characters: List<StarWarsCharacter>) {
        progress_bar.visibility = View.GONE
        for (character in characters) {
            characterDataset.add(character)
        }
        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = characterAdapter
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun displayError() {
        progress_bar.visibility = View.GONE
        Toast.makeText(
            this@MainActivity,
            "Could not process the request. Please check your internet connection.",
            Toast.LENGTH_SHORT
        ).show()
    }

    // CharacterAdapter.CharacterAdapterListener
    override fun onCharacterSelected(character: StarWarsCharacter) {
        val bundle = Bundle().apply {
            putParcelable("character", Parcels.wrap(character))
        }
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}