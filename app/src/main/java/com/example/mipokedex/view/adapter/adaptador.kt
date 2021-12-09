package com.example.mipokedex.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mipokedex.databinding.PokemonListBinding
import com.example.mipokedex.model.Pokemon
import com.example.mipokedex.model.Pokenombre

class adaptador(context: Context, pokemones: Pokemon, onPokeListener: OnItemListener): RecyclerView.Adapter<adaptador.ViewHolder>() {
    private val mPokemones = pokemones
    private val mOnPokeListener = onPokeListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adaptador.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = PokemonListBinding.inflate(layoutInflater)
        return ViewHolder(binding, mOnPokeListener)
    }

    override fun onBindViewHolder(holder: adaptador.ViewHolder, position: Int) {
        holder.bindData(mPokemones.pokeLista!![position], position+1)
    }

    override fun getItemCount(): Int {
        var count =mPokemones.pokeLista!!.size
        return count

    }

    interface OnItemListener{
        fun onPokeClick(poke: Pokemon)
    }

    class ViewHolder(binding: PokemonListBinding, onPokeListener: OnItemListener): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{

        private val tvPokeNombre = binding.tvPokeNombre
        private val tvPokeID = binding.tvPokeID
        private val context = binding.root.context
        private val onPokeListener = onPokeListener
        private lateinit var pokemon: Pokemon
        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onPokeListener.onPokeClick(pokemon)
        }

        fun bindData(item: Pokenombre, index: Int){
            tvPokeNombre.text = item.nombre.toString()
            tvPokeID.text = index.toString()
        }


    }

}
