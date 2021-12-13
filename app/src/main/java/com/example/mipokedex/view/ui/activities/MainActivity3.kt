package com.example.mipokedex.view.ui.activities

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.mipokedex.R
import com.example.mipokedex.databinding.ActivityMain2Binding
import com.example.mipokedex.databinding.ActivityMain3Binding
import com.example.mipokedex.model.Pokedetalle
import com.example.mipokedex.model.PokemonAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity3 : AppCompatActivity() {

    private lateinit var binding: ActivityMain3Binding
    private lateinit var  mp: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val urlBase = getString(R.string.urlPoke)
        val logtag = getString(R.string.logs)
        val bundle = intent.extras
        val pokeNombre = bundle?.getString("pokemon","null")
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlBase)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mp = MediaPlayer.create(this, R.raw.sound2)
        val pokeAPI: PokemonAPI = retrofit.create(PokemonAPI::class.java)
        val call: Call<Pokedetalle> = pokeAPI.getDetalles(pokeNombre.toString())
        call.enqueue(object: Callback<Pokedetalle>{
            override fun onResponse(call: Call<Pokedetalle>, response: Response<Pokedetalle>) {
                Log.d(logtag,"Respuesta del servidor: $response")
                with(binding){
                    tvExperiencia.text = response.body()?.experiencia.toString()
                    tvID.text = response.body()?.numero.toString()
                    tvNombre.text = pokeNombre.toString()
                    tvPeso.text = response.body()?.peso.toString()
                    tvTamano.text = response.body()?.tamano.toString()
                    Glide.with(this@MainActivity3)
                        .load(response.body()?.imagen?.frente.toString())
                        .into(ivPokeDetalle)
                    tvHPS.text = response.body()?.stats?.get(0)?.estadoBase?.toString()
                    tvAttackS.text = response.body()?.stats?.get(1)?.estadoBase?.toString()
                    tvDefenseS.text = response.body()?.stats?.get(2)?.estadoBase?.toString()
                    tvSpecialAS.text = response.body()?.stats?.get(3)?.estadoBase?.toString()
                    tvSpecialDS.text = response.body()?.stats?.get(4)?.estadoBase?.toString()
                    tvSpeedS.text = response.body()?.stats?.get(5)?.estadoBase?.toString()
                    val tipo1: String? = response.body()?.tipos?.get(0)?.tipo?.nombreTipo
                    try{
                        val tipo2: String? = response.body()?.tipos?.get(1)?.tipo?.nombreTipo
                        definirTipo(tipo1, tipo2)
                    }catch (e: Exception){
                        val tipo2: String? = null
                        definirTipo(tipo1, tipo2)
                    }
                    pbDetalles.visibility = View.INVISIBLE
                    ibBoton.setOnClickListener {
                        finish()
                        mp.stop()
                    }
                    btEstadisticas.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<Pokedetalle>, t: Throwable) {
                //Si falla
                with(binding) {
                    Log.d(logtag, "No hay respuesta del servidor:")
                    Toast.makeText(this@MainActivity3, "Error en la conexion", Toast.LENGTH_SHORT).show()
                    println("error; ${call.request()}")
                    pbDetalles.visibility = View.INVISIBLE
                }
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

    override fun onPause() {
        super.onPause()
        if(mp.isPlaying){
            mp.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        mp.start()
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

    private fun definirTipo(tipo1: String?, tipo2: String?){
        val ivTipo1 = findViewById<ImageView>(R.id.ivTipo1)
        val ivTipo2 = findViewById<ImageView>(R.id.ivTipo2)
        val tvTipo1 = findViewById<TextView>(R.id.tvTipo1)
        val tvTipo2 = findViewById<TextView>(R.id.tvTipo2)
        val tvDivisor = findViewById<TextView>(R.id.tvDivisor)
        when (tipo1){
            "grass" ->{
                tvTipo1.text = getString(R.string.hierba)
                ivTipo1.setImageResource(R.drawable.hierba)
            }
            "fire" ->{
                tvTipo1.text = getString(R.string.fuego)
                ivTipo1.setImageResource(R.drawable.fuego)
            }
            "rock" ->{
                tvTipo1.text = getString(R.string.roca)
                ivTipo1.setImageResource(R.drawable.roca)
            }
            "flying" ->{
                tvTipo1.text = getString(R.string.volador)
                ivTipo1.setImageResource(R.drawable.volador)
            }
            "poison" ->{
                tvTipo1.text = getString(R.string.veneno)
                ivTipo1.setImageResource(R.drawable.veneno)
            }
            "water" ->{
                tvTipo1.text = getString(R.string.agua)
                ivTipo1.setImageResource(R.drawable.agua)
            }
            "bug" ->{
                tvTipo1.text = getString(R.string.bicho)
                ivTipo1.setImageResource(R.drawable.bicho)
            }
            "normal" ->{
                tvTipo1.text = getString(R.string.normal)
                ivTipo1.setImageResource(R.drawable.normal)
            }
            "electric" ->{
                tvTipo1.text = getString(R.string.electrico)
                ivTipo1.setImageResource(R.drawable.electrico)
            }
            "ground" ->{
                tvTipo1.text = getString(R.string.tierra)
                ivTipo1.setImageResource(R.drawable.tierra)
            }
            "fairy" ->{
                tvTipo1.text = getString(R.string.hada)
                ivTipo1.setImageResource(R.drawable.hada)
            }
            "fighting" ->{
                tvTipo1.text = getString(R.string.peleador)
                ivTipo1.setImageResource(R.drawable.peleador)
            }
            "psychic" ->{
                tvTipo1.text = getString(R.string.psiquico)
                ivTipo1.setImageResource(R.drawable.psiquico)
            }
            "ice" ->{
                tvTipo1.text = getString(R.string.hielo)
                ivTipo1.setImageResource(R.drawable.hielo)
            }
            "ghost" ->{
                tvTipo1.text = getString(R.string.fantasma)
                ivTipo1.setImageResource(R.drawable.fantasma)
            }
            "dragon" ->{
                tvTipo1.text = getString(R.string.dragon)
                ivTipo1.setImageResource(R.drawable.dragon)
            }
            "dark" ->{
                tvTipo1.text = getString(R.string.siniestro)
                ivTipo1.setImageResource(R.drawable.siniestro)
            }
            "steel" ->{
                tvTipo1.text = getString(R.string.acero)
                ivTipo1.setImageResource(R.drawable.acero)
            }
        }
        if(!tipo2.isNullOrEmpty()){
            tvDivisor.visibility = View.VISIBLE
            when (tipo2){
                "grass" ->{
                    tvTipo2.text = getString(R.string.hierba)
                    ivTipo2.setImageResource(R.drawable.hierba)
                }
                "fire" ->{
                    tvTipo2.text = getString(R.string.fuego)
                    ivTipo2.setImageResource(R.drawable.fuego)
                }
                "rock" ->{
                    tvTipo2.text = getString(R.string.roca)
                    ivTipo2.setImageResource(R.drawable.roca)
                }
                "flying" ->{
                    tvTipo2.text = getString(R.string.volador)
                    ivTipo2.setImageResource(R.drawable.volador)
                }
                "poison" ->{
                    tvTipo2.text = getString(R.string.veneno)
                    ivTipo2.setImageResource(R.drawable.veneno)
                }
                "water" ->{
                    tvTipo2.text = getString(R.string.agua)
                    ivTipo2.setImageResource(R.drawable.agua)
                }
                "bug" ->{
                    tvTipo2.text = getString(R.string.bicho)
                    ivTipo2.setImageResource(R.drawable.bicho)
                }
                "normal" ->{
                    tvTipo2.text = getString(R.string.normal)
                    ivTipo2.setImageResource(R.drawable.normal)
                }
                "electric" ->{
                    tvTipo2.text = getString(R.string.electrico)
                    ivTipo2.setImageResource(R.drawable.electrico)
                }
                "ground" ->{
                    tvTipo2.text = getString(R.string.tierra)
                    ivTipo2.setImageResource(R.drawable.tierra)
                }
                "fairy" ->{
                    tvTipo2.text = getString(R.string.hada)
                    ivTipo2.setImageResource(R.drawable.hada)
                }
                "fighting" ->{
                    tvTipo2.text = getString(R.string.peleador)
                    ivTipo2.setImageResource(R.drawable.peleador)
                }
                "psychic" ->{
                    tvTipo2.text = getString(R.string.psiquico)
                    ivTipo2.setImageResource(R.drawable.psiquico)
                }
                "ice" ->{
                    tvTipo2.text = getString(R.string.hielo)
                    ivTipo2.setImageResource(R.drawable.hielo)
                }
                "ghost" ->{
                    tvTipo2.text = getString(R.string.fantasma)
                    ivTipo2.setImageResource(R.drawable.fantasma)
                }
                "dragon" ->{
                    tvTipo2.text = getString(R.string.dragon)
                    ivTipo2.setImageResource(R.drawable.dragon)
                }
                "dark" ->{
                    tvTipo2.text = getString(R.string.siniestro)
                    ivTipo2.setImageResource(R.drawable.siniestro)
                }
                "steel" ->{
                    tvTipo2.text = getString(R.string.acero)
                    ivTipo2.setImageResource(R.drawable.acero)
                }
            }
        }else{
            ivTipo2.visibility = View.INVISIBLE
            tvDivisor.visibility = View.INVISIBLE
        }

    }

    fun swSonidoClick(view: android.view.View) {
        val swSonido = findViewById<SwitchCompat>(R.id.swSonidoDetalles)
        if(swSonido.isChecked){
            mp.setVolume(0.0F,0.0F)
        }
        if(!swSonido.isChecked){
            mp.setVolume(1.0F,1.0F)
        }

    }

    fun verStats(view: android.view.View) {
        val tlDetalles = findViewById<TableLayout>(R.id.tlDetalles)
        val tlStats = findViewById<TableLayout>(R.id.tlStats)
        val btDetalles = findViewById<Button>(R.id.btEstadisticas)
        if(tlDetalles.isVisible){
            btDetalles.text = getString(R.string.detalles)
            tlDetalles.visibility = View.INVISIBLE
            tlStats.visibility = View.VISIBLE
        }else{
            btDetalles.text = getString(R.string.stats)
            tlDetalles.visibility = View.VISIBLE
            tlStats.visibility = View.INVISIBLE
        }
    }
}
