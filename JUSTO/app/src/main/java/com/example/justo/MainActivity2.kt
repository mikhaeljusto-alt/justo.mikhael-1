package com.example.justo

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        // Close Activity 2 and return back to MainActivity
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnBackHome = findViewById<CardView>(R.id.btnBackHome)

        btnBack.setOnClickListener { finish() }
        btnBackHome.setOnClickListener { finish() }
    }
}