package com.example.mipokedex.view.ui.activities

import android.content.Intent
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
import com.example.mipokedex.model.Pokenombre
import com.example.mipokedex.view.adapter.Adaptador
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity2 : AppCompatActivity(), Adaptador.OnItemListener {
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
        val call: Call<Pokemon> = pokeApi.getPokemon("?limit=151")
        call.enqueue(object: Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
               //Si funciona
               Log.d(logtag,"Respuesta del servidor: $response")
                binding.pbConexion.visibility= View.INVISIBLE
                try{
                    val adaptador = Adaptador(this@MainActivity2,response.body()!!, this@MainActivity2)
                    with(binding){
                        rvListaPoke.layoutManager = LinearLayoutManager(this@MainActivity2)
                        rvListaPoke.adapter = adaptador
                    }
                }catch(e: NullPointerException){
                    Toast.makeText(this@MainActivity2,"No se logro adaptar", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                //Si falla
                Log.d(logtag,"No hay respuesta del servidor:")
                Toast.makeText(this@MainActivity2,"Error en la conexion", Toast.LENGTH_SHORT).show()
                binding.pbConexion.visibility= View.INVISIBLE
            }

        })
    }

    override fun onPokeClick(poke: Pokenombre) {
        val parametros = Bundle()
        parametros.putString("pokemon", poke.nombre.toString())
        val intent = Intent(this, MainActivity3::class.java)
        intent.putExtras(parametros)
        startActivity(intent)
    }
}






















