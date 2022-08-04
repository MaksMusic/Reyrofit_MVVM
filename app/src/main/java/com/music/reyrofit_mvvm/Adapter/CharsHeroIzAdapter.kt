package com.music.reyrofit_mvvm.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.music.reyrofit_mvvm.data.model.Character
import com.music.reyrofit_mvvm.databinding.CharsFavoriteItemBinding
import com.music.reyrofit_mvvm.databinding.CharsHeroItemBinding
import com.music.reyrofit_mvvm.room.App.App
import com.music.reyrofit_mvvm.room.modelRoom.CharacterDao
import com.music.reyrofit_mvvm.room.modelRoom.CharacterModelRoom
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharsHeroIzAdapter(
    private val list: ArrayList<CharacterModelRoom>,
    var listener: OnClickListener
) : RecyclerView.Adapter<CharsHeroIzAdapter.ViewHolder>() {

    interface OnClickListener {
        fun onClickRemoveCharacter(characterModelRoom: CharacterModelRoom)
    }

    inner class ViewHolder(var binding: CharsFavoriteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun addHero(result: CharacterModelRoom) {
            binding.name.text = result.name
            Picasso.get().load(result.image).into(binding.image)

            binding.btnRemove.setOnClickListener {
                listener.onClickRemoveCharacter(result)
                list.remove(result)
                notifyDataSetChanged()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            CharsFavoriteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.addHero(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}