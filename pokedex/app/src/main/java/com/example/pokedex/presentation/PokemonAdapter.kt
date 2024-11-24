package com.example.pokedex.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokedex.R
import com.example.pokedex.databinding.PokemonItemBinding
import com.example.pokedex.domain.entities.Pokemon

class PokemonAdapter(private val list: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(private val binding: PokemonItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemon: Pokemon) {
            binding.name.text = pokemon.name
            binding.number.text = pokemon.formattedNumber()
            Glide.with(binding.root.context)
                .load(pokemon.getImage())
                .error(R.drawable.image_not_supported)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PokemonItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
