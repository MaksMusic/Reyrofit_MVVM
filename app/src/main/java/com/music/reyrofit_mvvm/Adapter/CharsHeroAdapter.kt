package com.music.reyrofit_mvvm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.music.reyrofit_mvvm.data.model.Character
import com.music.reyrofit_mvvm.databinding.CharsHeroItemBinding
import com.music.reyrofit_mvvm.room.App.App
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import com.music.reyrofit_mvvm.room.modelRoom.CharacterModelRoom
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharsHeroAdapter(var listenner: OnClickListener) :
    RecyclerView.Adapter<CharsHeroAdapter.ViewHolder>() {
    lateinit var characterDao: CharacterDao

    interface OnClickListener {
        fun onClickAddCharacter(characterModelRoom: CharacterModelRoom)
    }


    private val heroList: ArrayList<Character> = arrayListOf()

    fun addHerosList(list: List<Character>) {
        heroList.addAll(list)
        notifyDataSetChanged()
    }


    inner class ViewHolder(var binding: CharsHeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun addHero(result: Character) {
            binding.name.text = result.name
            Picasso.get().load(result.image).into(binding.image)

            binding.addBaza.setOnClickListener() {
                listenner.onClickAddCharacter(
                    CharacterModelRoom(
                        0,
                        result.name,
                        result.image,
                        result.species,
                        result.status,
                        result.type,
                        result.url,
                        result.created
                    )
                )

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharsHeroItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addHero(heroList[position])
    }

    override fun getItemCount(): Int {
        return heroList.size
    }


}