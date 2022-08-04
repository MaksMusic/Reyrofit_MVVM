package com.music.reyrofit_mvvm.room.modelRoom

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert
    fun insertUser(characterModelRoom: CharacterModelRoom) //добавляет объект

    @Delete
    fun deleteUser(characterModelRoom: CharacterModelRoom) //delete объект

    @Query("SELECT * FROM characterModelRoom") //получить весь список
    fun getAllCharacter(): List<CharacterModelRoom>

}