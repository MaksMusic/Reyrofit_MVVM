package com.music.reyrofit_mvvm.data

import com.music.reyrofit_mvvm.data.model.Character
import com.music.reyrofit_mvvm.data.model.ResponseDataCharacters
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {

@GET("character")
suspend fun getCharacter(): Response<ResponseDataCharacters>


companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/api/"
        var retrofitServices: RetrofitServices? = null

        fun getInstance(): RetrofitServices {
            if (retrofitServices == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitServices = retrofit.create(RetrofitServices::class.java)
            }

            return retrofitServices!!
        }
    }
}