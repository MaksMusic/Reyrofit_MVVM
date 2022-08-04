package com.music.reyrofit_mvvm.data.model


import com.google.gson.annotations.SerializedName


data class ResponseDataCharacters(
    @SerializedName("info") val info:Info,
    @SerializedName("results") val results: List<Character>

)

data class Character(
    @SerializedName("id")val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("species")val species: String,
    @SerializedName("status") val status: String,
    @SerializedName("type")  val type: String,
    @SerializedName("url") val url: String,
    @SerializedName("created") val created: String
)

data class Info(
    @SerializedName("count") val  count:Int,
    @SerializedName("pages") val  pages:Int)