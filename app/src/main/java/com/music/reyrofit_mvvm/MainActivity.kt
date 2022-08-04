package com.music.reyrofit_mvvm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.music.reyrofit_mvvm.Adapter.CharsHeroAdapter
import com.music.reyrofit_mvvm.data.RetrofitServices
import com.music.reyrofit_mvvm.data.factory.CharacterViewModelFactory

import com.music.reyrofit_mvvm.data.repozitory.CharacterRepository
import com.music.reyrofit_mvvm.databinding.ActivityMainBinding
import com.music.reyrofit_mvvm.room.App.App
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import com.music.reyrofit_mvvm.room.modelRoom.CharacterModelRoom
import com.music.reyrofit_mvvm.viewModel.CharacterViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), CharsHeroAdapter.OnClickListener {
    @SuppressLint("NotifyDataSetChanged")
    lateinit var binding: ActivityMainBinding
    private lateinit var characterViewModel: CharacterViewModel
    private lateinit var repository: CharacterRepository
    private lateinit var retrofitServices: RetrofitServices
    lateinit var characterDao: CharacterDao

    lateinit var adapter: CharsHeroAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = CharsHeroAdapter(this)
        //room
        characterDao = (application as App).getDataBase().characterDao()
        //adapter
        binding.recycler.adapter = adapter

        binding.btnToolBar.setOnClickListener() {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }


        //retrofit
        retrofitServices = RetrofitServices.getInstance()
        repository = CharacterRepository(retrofitServices)

        characterViewModel = ViewModelProvider(
            this,
            CharacterViewModelFactory(repository)
        ).get(CharacterViewModel::class.java)


        getLoadData(characterViewModel)
    }


    fun getLoadData(viewModel: CharacterViewModel) {
        viewModel.charsetsList.observe(this) { it ->
            adapter.addHerosList(it.results)
        }
        viewModel.getHeros()
    }


    override fun onClickAddCharacter(characterModelRoom: CharacterModelRoom) {
        lifecycleScope.launch(Dispatchers.IO) {
            characterDao.insertUser(characterModelRoom)
        }
    }
}