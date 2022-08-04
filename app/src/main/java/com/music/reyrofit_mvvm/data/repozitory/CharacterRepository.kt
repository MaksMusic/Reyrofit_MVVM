package com.music.reyrofit_mvvm.data.repozitory

import com.music.reyrofit_mvvm.data.RetrofitServices

class CharacterRepository constructor(private val retrofitServices: RetrofitServices) {
    suspend fun getCharacter()  = retrofitServices.getCharacter()
}