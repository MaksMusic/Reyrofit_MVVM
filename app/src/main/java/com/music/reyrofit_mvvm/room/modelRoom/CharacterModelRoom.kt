package com.music.reyrofit_mvvm.room.modelRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity (tableName = "characterModelRoom")
data class CharacterModelRoom(
    @PrimaryKey(autoGenerate = true) val id :Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name ="species")val species: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "type")  val type: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "created") val created: String
) {
}