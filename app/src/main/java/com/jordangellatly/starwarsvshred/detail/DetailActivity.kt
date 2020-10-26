package com.jordangellatly.starwarsvshred.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jordangellatly.starwarsvshred.R
import com.jordangellatly.starwarsvshred.data.StarWarsCharacter
import kotlinx.android.synthetic.main.activity_detail.*
import org.parceler.Parcels

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var characterFromIntent: StarWarsCharacter

    override var presenter: DetailContract.Presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        characterFromIntent = Parcels.unwrap(intent.getParcelableExtra("character"))
        character_name.text = characterFromIntent.name
        height_value.text = characterFromIntent.height
        mass_value.text = characterFromIntent.mass
        hair_color_value.text = characterFromIntent.hairColor
        skin_color_value.text = characterFromIntent.skinColor
        eye_color_value.text = characterFromIntent.eyeColor
        birth_year_value.text = characterFromIntent.birthYear
        gender_value.text = characterFromIntent.gender

        favorite_button.setOnClickListener {
            Toast.makeText(this@DetailActivity, "This character is now a favorite", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun displayCharacterDetails() {
        // TODO display single character details
    }
}