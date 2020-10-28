package com.jordangellatly.starwarsvshred.ui.detail

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.application.StarWarsApplication
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
import com.jordangellatly.starwarsvshred.prefs.Const
import kotlinx.android.synthetic.main.activity_detail.*
import org.parceler.Parcels
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var characterFromIntent: StarWarsCharacter
    private lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        (application as StarWarsApplication).starWarsComponent.inject(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        characterFromIntent = Parcels.unwrap(intent.getParcelableExtra("character"))

        sharedPreferences = getSharedPreferences(Const.FAVORITES, Context.MODE_PRIVATE)

        presenter.setView(this@DetailActivity)
        presenter.onViewCreated(characterFromIntent.name)

        favorite_icon.setOnClickListener {
            presenter.storeFavoritePreferences()
        }
    }

    override fun displayCharacterDetails() {
        character_name.text = characterFromIntent.name
        height_value.text = characterFromIntent.height
        mass_value.text = characterFromIntent.mass
        hair_color_value.text = characterFromIntent.hairColor
        skin_color_value.text = characterFromIntent.skinColor
        eye_color_value.text = characterFromIntent.eyeColor
        birth_year_value.text = characterFromIntent.birthYear
        gender_value.text = characterFromIntent.gender
    }

    override fun markCharacterFavorite() {
        val isFavorite = sharedPreferences.getBoolean(characterFromIntent.name, false)
        if (isFavorite) {
            favorite_icon.setImageResource(R.drawable.ic_star_outline)
            Toast.makeText(
                this@DetailActivity,
                "${character_name.text} removed as a favorite",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            favorite_icon.setImageResource(R.drawable.ic_star)
            Toast.makeText(
                this@DetailActivity,
                "${character_name.text} is now a favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
        with(sharedPreferences.edit()) {
            putBoolean(characterFromIntent.name, !isFavorite)
            apply()
        }
    }

    override fun setStar() {
        favorite_icon.setImageResource(R.drawable.ic_star)
    }

    override fun removeStar() {
        favorite_icon.setImageResource(R.drawable.ic_star_outline)
    }
}