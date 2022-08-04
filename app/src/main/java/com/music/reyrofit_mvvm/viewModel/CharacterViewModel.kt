package com.music.reyrofit_mvvm.viewModel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.music.reyrofit_mvvm.data.model.ResponseDataCharacters
import com.music.reyrofit_mvvm.data.repozitory.CharacterRepository
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import kotlinx.coroutines.*

class CharacterViewModel constructor(private val characterRepository: CharacterRepository) :
    ViewModel(){


    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()
    val charsetsList = MutableLiveData<ResponseDataCharacters>()
    var job: Job? = null


    private fun exceptionHandler() = CoroutineExceptionHandler { _, thowable ->
        onError("Exception handler ${thowable.localizedMessage})")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    fun getHeros() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler()).launch {
            loading.postValue(true)
            val call = characterRepository.getCharacter()

            withContext(Dispatchers.Main) {
                if (call.isSuccessful) { //если запрос успешен
                    charsetsList.postValue(call.body())
                    loading.value = false
                } else {
                    onError("ERROR: ${call.message()}")
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}






