package com.example.mipokedex.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mipokedex.R
import com.example.mipokedex.databinding.ActivityMain2Binding
import com.example.mipokedex.model.Pokemon
import com.example.mipokedex.model.PokemonAPI
import com.example.mipokedex.view.adapter.adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity(), adaptador.OnItemListener {

    //private val urlBase = "https://pokeapi.co/"

    private val logtag = "LOGS"

    private lateinit var binding: ActivityMain2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val urlBase = getString(R.string.urlPoke)
       val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokeApi: PokemonAPI = retrofit.create(PokemonAPI::class.java)
        val call: Call<Pokemon> = pokeApi.getPokemon("pokemon?limit=151")
        call.enqueue(object: Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
               //Si funciona
               Log.d(logtag,"Respuesta del servidor: $response")
                binding.pbConexion.visibility= View.INVISIBLE
                try{
                    val adaptador = adaptador(this@MainActivity2,response.body()!!, this@MainActivity2)
                    binding.rvListaPoke.layoutManager = LinearLayoutManager(this@MainActivity2)
                    binding.rvListaPoke.adapter = adaptador
                }catch(e: NullPointerException){
                    Toast.makeText(this@MainActivity2,"No se logro adaptar", Toast.LENGTH_SHORT).show()
                }

                /*val poketemp : Pokemon
                var index: Int = 0
                for (poketemp in response.body()!!.toString()){
                    Log.d(LOGTAG,"Respuesta del servidor: ${response.body()!!.pokeLista?.get(index)?.nombre.toString()}")
                    index ++
                }
                Log.d(LOGTAG,"Respuesta del servidor: ${response.body()!!.pokeLista?.size.toString()}")
                */

            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                //Si falla
                Log.d(logtag,"No hay respuesta del servidor:")
                Toast.makeText(this@MainActivity2,"Error en la conexion", Toast.LENGTH_SHORT).show()
                binding.pbConexion.visibility= View.INVISIBLE
            }

        })
    }

    override fun onPokeClick(poke: Pokemon) {

    }
}