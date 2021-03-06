package com.jordangellatly.starwarsvshred.ui.main

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.application.StarWarsApplication
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.ui.adapter.CharacterAdapter
import com.jordangellatly.starwarsvshred.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.parceler.Parcels
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View, OnQueryTextListener {

    private lateinit var characterAdapter: CharacterAdapter
    private var characterList: ArrayList<StarWarsCharacter> = arrayListOf()

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as StarWarsApplication).starWarsComponent.inject(this)

        if (characterList.isEmpty()) {
            characterList = presenter.retrieveOfflineCharacterList()
        }

        characterAdapter = CharacterAdapter(
            this@MainActivity,
            characterList,
            presenter,
            (application as StarWarsApplication)
        )

        presenter.setView(this@MainActivity)
        presenter.onViewCreated(characterList)

        search_view.setOnQueryTextListener(this@MainActivity)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (recycler_view.isNotEmpty()) {
            presenter.searchList(query)
        } else {
            Toast.makeText(
                this@MainActivity,
                "No match found.",
                Toast.LENGTH_SHORT
            ).show()
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        presenter.searchList(newText)
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        presenter.saveOfflineCharacterList(characterList)
        super.onSaveInstanceState(outState)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                presenter.refreshCharacterDetails()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun addCharacterNamesFromApi(characters: List<StarWarsCharacter>) {
        if (characterList.isEmpty()) {
            for (character in characters) {
                characterList.add(character)
            }
        }
    }

    override fun setupRecyclerView() {
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

    override fun isWifiEnabled(): Boolean {
        val wifiManager: WifiManager =
            applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return wifiManager.isWifiEnabled
    }

    override fun showWifiDisabled() {
        Toast.makeText(this@MainActivity, "Wifi is disconnected.", Toast.LENGTH_SHORT).show()
    }

    override fun displayError() {
        Toast.makeText(
            this@MainActivity,
            "Could not process the request. Please check your internet connection.",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun showRefresh() {
        Toast.makeText(this@MainActivity, "Refreshing...", Toast.LENGTH_SHORT).show()
    }

    override fun onCharacterSelected(character: StarWarsCharacter) {
        val bundle = Bundle().apply {
            putParcelable("character", Parcels.wrap(character))
        }
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    override fun clearList() {
        characterAdapter.clear()
    }

    override fun filterList(filterString: String?) {
        characterAdapter.filter.filter(filterString)
    }

    override fun clearSearchQuery() {
        search_view.setQuery("", false)
        search_view.clearFocus()
    }
}