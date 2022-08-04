package com.music.reyrofit_mvvm.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.music.reyrofit_mvvm.data.repozitory.CharacterRepository
import com.music.reyrofit_mvvm.viewModel.CharacterViewModel
import java.lang.IllegalArgumentException

class CharacterViewModelFactory(private val characterRepository: CharacterRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            CharacterViewModel(this.characterRepository) as T
        } else {
            throw IllegalArgumentException("exeption view model not found")
        }
    }
}