package com.example.downloader

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList
import kotlin.concurrent.thread
import org.schabi.newpipe.extractor.downloader.Downloader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos NewPipe con un "Downloader" básico
        try {
            NewPipe.init(null) 
        } catch (e: Exception) {
            // Ya estaba inicializado o error leve
        }

        val urlInput = findViewById<EditText>(R.id.urlInput)
        val downloadBtn = findViewById<Button>(R.id.downloadBtn)
        val statusText = findViewById<TextView>(R.id.statusText)

        downloadBtn.setOnClickListener {
            val url = urlInput.text.toString()
            if (url.isEmpty()) {
                statusText.text = "Por favor, pega un link"
                return@setOnClickListener
            }

            statusText.text = "Analizando video..."

            thread {
                try {
                    val service = ServiceList.YouTube
                    val extractor = service.getStreamExtractor(url)
                    extractor.fetchPage()
                    
                    val title = extractor.name
                    
                    runOnUiThread {
                        statusText.text = "Encontrado: $title"
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        statusText.text = "Error al leer el video"
                    }
                }
            }
        }
    }
}
