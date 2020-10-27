package com.jordangellatly.starwarsvshred.network

import com.jordangellatly.starwarsvshred.model.StarWarsResults
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val serviceBuilder: ServiceBuilder) : CharacterRepository {
    override fun loadCharacters(callback: CharacterRepository.LoadCharactersCallback) {
        val request = serviceBuilder.buildService(StarWarsApi::class.java)
        val call = request.getCharacterResults()

        call.enqueue(object : Callback<StarWarsResults> {
            override fun onResponse(
                call: Call<StarWarsResults>,
                response: Response<StarWarsResults>
            ) {
                if (response.isSuccessful) {
                    val characters = response.body()!!.results
                    callback.onCharactersLoaded(characters)
                }
            }

            override fun onFailure(call: Call<StarWarsResults>, t: Throwable) {
                callback.onDataNotAvailable()
            }
        })
    }

    companion object {
        private const val TAG = "CharacterRepositoryImpl"
    }
}