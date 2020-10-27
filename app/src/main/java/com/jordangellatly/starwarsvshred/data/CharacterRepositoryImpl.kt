package com.jordangellatly.starwarsvshred.data

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepositoryImpl : CharacterRepository {
    override fun loadCharacters(callback: CharacterRepository.LoadCharactersCallback) {
        val request = RetrofitBuilder.buildService(StarWarsApi::class.java)
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