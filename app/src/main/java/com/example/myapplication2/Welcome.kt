package com.example.myapplication2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class Welcome: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        findViewById<MaterialButton>(R.id.registerButton).setOnClickListener {
            startActivity(Intent(this, Registration::class.java))
        }

        findViewById<MaterialButton>(R.id.loginButton).setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }
    }
}