package com.example.downloader

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btnTest)

        button.setOnClickListener {
            Toast.makeText(this, "La app funciona correctamente 🚀", Toast.LENGTH_SHORT).show()
        }
    }
}