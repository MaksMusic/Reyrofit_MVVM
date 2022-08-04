package com.music.reyrofit_mvvm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.music.reyrofit_mvvm.Adapter.CharsHeroIzAdapter
import com.music.reyrofit_mvvm.data.model.Character
import com.music.reyrofit_mvvm.databinding.ActivityFavoritesBinding
import com.music.reyrofit_mvvm.room.App.App
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import com.music.reyrofit_mvvm.room.modelRoom.CharacterModelRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.CharacterData
import kotlin.jvm.internal.AdaptedFunctionReference

@SuppressLint("NotifyDataSetChanged")
class FavoritesActivity : AppCompatActivity(),CharsHeroIzAdapter.OnClickListener {
    lateinit var characterDao: CharacterDao
    lateinit var binding: ActivityFavoritesBinding
    lateinit var adapter: CharsHeroIzAdapter
    lateinit var list: ArrayList<CharacterModelRoom>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        characterDao = (application as App).getDataBase().characterDao()
        lifecycleScope.launch(Dispatchers.IO) {
            list = characterDao.getAllCharacter() as ArrayList
            withContext(Dispatchers.Main) {
                adapter = CharsHeroIzAdapter(list,this@FavoritesActivity)
                binding.recycler.adapter = adapter

                for (i in list){
                    Log.d("LIST", i.name)
                }
            }
        }

    }

    override fun onClickRemoveCharacter(characterModelRoom: CharacterModelRoom) {
      lifecycleScope.launch(Dispatchers.IO){
          characterDao.deleteUser(characterModelRoom)

      }
    }
}