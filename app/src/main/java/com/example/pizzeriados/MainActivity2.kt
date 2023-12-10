package com.example.pizzeriados

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val textview  = findViewById<TextView>(R.id.textview)

        val intent = intent

        val textorecibido = intent.getStringExtra("Ticket")
        textview.text = textorecibido

    }
}