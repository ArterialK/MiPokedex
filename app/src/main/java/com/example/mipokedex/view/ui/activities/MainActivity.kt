package com.example.mipokedex.view.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.mipokedex.R
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inicio = timerTask {
            @Override
            fun run(){
                pasar()
            }
            run()
        }

        val tiempo = Timer()
        tiempo.schedule(inicio,5000)
    }

    private fun pasar(){
        val siguiente = Intent(this, MainActivity2::class.java)
        startActivity(siguiente)
        finish()
    }
}