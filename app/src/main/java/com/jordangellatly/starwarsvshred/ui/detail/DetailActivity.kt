package com.jordangellatly.starwarsvshred.ui.detail

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.application.StarWarsApplication
import com.jordangellatly.starwarsvshred.model.StarWarsCharacter
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

        presenter.setView(this@DetailActivity)
        presenter.onViewCreated()

        favorite_icon.setOnClickListener {
            presenter.storeFavoritePreferences()
        }
    }

    override fun displayCharacterDetails() {
        characterFromIntent = Parcels.unwrap(intent.getParcelableExtra("character"))
        character_name.text = characterFromIntent.name
        height_value.text = characterFromIntent.height
        mass_value.text = characterFromIntent.mass
        hair_color_value.text = characterFromIntent.hairColor
        skin_color_value.text = characterFromIntent.skinColor
        eye_color_value.text = characterFromIntent.eyeColor
        birth_year_value.text = characterFromIntent.birthYear
        gender_value.text = characterFromIntent.gender

        sharedPreferences = getSharedPreferences(character_name.text.toString(), Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean("is_favorite", false)) {
            favorite_icon.setImageResource(R.drawable.ic_star)
        } else {
            favorite_icon.setImageResource(R.drawable.ic_star_outline)
        }
    }

    override fun markCharacterFavorite() {
        val isFavorite = sharedPreferences.getBoolean("is_favorite", false)
        if (isFavorite) {
            favorite_icon.setImageResource(R.drawable.ic_star_outline)
            Toast.makeText(this@DetailActivity, "${character_name.text} removed as a favorite", Toast.LENGTH_SHORT).show()
        } else {
            favorite_icon.setImageResource(R.drawable.ic_star)
            Toast.makeText(this@DetailActivity, "${character_name.text} is now a favorite", Toast.LENGTH_SHORT).show()
        }
        with(sharedPreferences.edit()) {
            putBoolean("is_favorite", !isFavorite)
            apply()
        }
    }
}