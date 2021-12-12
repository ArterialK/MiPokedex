package com.example.mipokedex.view.ui.activities

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
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

    private lateinit var binding: ActivityMain2Binding
    private lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val urlBase = getString(R.string.urlPoke)
        val logtag = getString(R.string.logs)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mp = MediaPlayer.create(this, R.raw.sound1)
        val pokeApi: PokemonAPI = retrofit.create(PokemonAPI::class.java)
        val call: Call<Pokemon> = pokeApi.getPokemon("?limit=151")
        call.enqueue(object: Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
               //Si funciona
               Log.d(logtag,"Respuesta del servidor: $response")
               try{
                    val adaptador = Adaptador(this@MainActivity2,response.body()!!, this@MainActivity2)
                    with(binding){
                        rvListaPoke.layoutManager = LinearLayoutManager(this@MainActivity2)
                        rvListaPoke.adapter = adaptador
                    }
               }catch(e: NullPointerException){
                    Toast.makeText(this@MainActivity2,"No se logro adaptar", Toast.LENGTH_SHORT).show()
               }
                binding.pbConexion.visibility= View.INVISIBLE
            }

            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                //Si falla
                Log.d(logtag,"No hay respuesta del servidor:")
                Toast.makeText(this@MainActivity2,"Error en la conexion", Toast.LENGTH_SHORT).show()
                binding.pbConexion.visibility= View.INVISIBLE
            }

        })
    }

    override fun onStart() {
        super.onStart()
        mp.isLooping = true
        mp.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        mp.stop()
    }

    override fun onPokeClick(poke: Pokenombre) {
        val parametros = Bundle()
        parametros.putString("pokemon", poke.nombre.toString())
        val intent = Intent(this, MainActivity3::class.java)
        intent.putExtras(parametros)
        mp.pause()
        startActivity(intent)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode){
            KeyEvent.KEYCODE_BACK ->{
                mp.stop()
                finish()
                true
            }
            else -> {super.onKeyUp(keyCode, event)}
        }
    }

    fun swSonidoClick(view: android.view.View) {
        val swSonido = findViewById<SwitchCompat>(R.id.swSonido)
        if(swSonido.isChecked){
            mp.setVolume(0.0F,0.0F)
        }
        if(!swSonido.isChecked){
            mp.setVolume(1.0F,1.0F)
        }
    }
}






















