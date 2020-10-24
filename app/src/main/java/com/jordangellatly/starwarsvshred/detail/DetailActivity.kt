package com.jordangellatly.starwarsvshred.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jordangellatly.starwarsvshred.R

class DetailActivity : AppCompatActivity(), DetailContract.View {

    override var presenter: DetailContract.Presenter = DetailPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun displayCharacterDetails() {
        // TODO display single character details
    }
}