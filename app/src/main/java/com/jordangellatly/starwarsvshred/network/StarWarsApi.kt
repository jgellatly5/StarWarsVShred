package com.jordangellatly.starwarsvshred.network

import com.jordangellatly.starwarsvshred.model.StarWarsResults
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsApi {
    @GET("people/?page=1")
    fun getCharacterResults(): Call<StarWarsResults>
}