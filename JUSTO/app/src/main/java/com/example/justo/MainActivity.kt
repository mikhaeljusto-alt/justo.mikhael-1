package com.example.justo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize Views
        val btnViewDetails = findViewById<CardView>(R.id.btnViewDetails)
        val cardStatus = findViewById<CardView>(R.id.cardStatus)
        val cardAlerts = findViewById<CardView>(R.id.cardAlerts)
        val btnLogout = findViewById<CardView>(R.id.btnLogout)

        // Navigation to details
        btnViewDetails.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        // Status Card Toast
        cardStatus.setOnClickListener {
            Toast.makeText(this, "System is fully protected!", Toast.LENGTH_SHORT).show()
        }

        // Alerts Card Toast
        cardAlerts.setOnClickListener {
            Toast.makeText(this, "No active security threats.", Toast.LENGTH_SHORT).show()
        }

        // Logout Button Logic
        btnLogout.setOnClickListener {
            auth.signOut()

            // Navigate back to LoginActivity and clear backstack
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}