package com.example.justo

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        val etRegEmail = findViewById<EditText>(R.id.etRegEmail)
        val etRegPassword = findViewById<EditText>(R.id.etRegPassword)
        val etRegConfirmPassword = findViewById<EditText>(R.id.etRegConfirmPassword)
        val btnRegister = findViewById<CardView>(R.id.btnRegister)
        val tvGoToLogin = findViewById<TextView>(R.id.tvGoToLogin)

        btnRegister.setOnClickListener {
            val email = etRegEmail.text.toString().trim()
            val password = etRegPassword.text.toString().trim()
            val confirmPassword = etRegConfirmPassword.text.toString().trim()

            // Email Validation
            if (email.isEmpty()) {
                etRegEmail.error = "Email is required"
                etRegEmail.requestFocus()
                return@setOnClickListener
            }

            // Password Length Validation
            if (password.length < 6) {
                etRegPassword.error = "Password must be at least 6 characters"
                etRegPassword.requestFocus()
                return@setOnClickListener
            }

            // Confirm Password Match Validation
            if (password != confirmPassword) {
                etRegConfirmPassword.error = "Passwords do not match"
                etRegConfirmPassword.requestFocus()
                return@setOnClickListener
            }

            // Temporarily disable button to avoid accidental duplicate submissions
            btnRegister.isEnabled = false

            // Create User with Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    btnRegister.isEnabled = true
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()

                        // Navigate to MainActivity and clear activity stack
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Registration failed: ${task.exception?.localizedMessage}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }

        // Return back to Login screen
        tvGoToLogin.setOnClickListener {
            finish()
        }
    }
}