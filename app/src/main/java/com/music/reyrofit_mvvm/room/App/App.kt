package com.music.reyrofit_mvvm.room.App

import android.app.Application
import androidx.room.Room
import com.music.reyrofit_mvvm.room.dao.AppDataBase

class App : Application() {
    private lateinit var appDataBase: AppDataBase

    override fun onCreate() {
        super.onCreate()

        appDataBase =
            Room.databaseBuilder(applicationContext, AppDataBase::class.java, "character_databaze")
                .build()
    }

    fun getDataBase() : AppDataBase{
        return appDataBase
    }
}