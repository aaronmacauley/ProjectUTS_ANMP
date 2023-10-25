package com.example.waroengujang_sembarangwes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.waroengujang_sembarangwes.R
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private val hardcodedUsername = "sembarangwes"
    private val hardcodedPassword = "321321"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInput = findViewById<TextInputEditText>(R.id.txtInputUsername)
        val passwordInput = findViewById<TextInputEditText>(R.id.txtInputPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)

        loginButton.setOnClickListener {
            val enteredUsername = usernameInput.text.toString()
            val enteredPassword = passwordInput.text.toString()

            if (enteredUsername == hardcodedUsername && enteredPassword == hardcodedPassword) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Snackbar.make(loginButton, "Username atau Password anda Salah", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}