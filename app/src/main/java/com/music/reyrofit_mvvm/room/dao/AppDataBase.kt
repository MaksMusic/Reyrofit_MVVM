package com.music.reyrofit_mvvm.room.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import com.music.reyrofit_mvvm.room.modelRoom.CharacterModelRoom


@Database(entities = [CharacterModelRoom::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun characterDao() : CharacterDao

}