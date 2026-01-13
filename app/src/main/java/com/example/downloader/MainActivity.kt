package com.example.downloader

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.schabi.newpipe.extractor.NewPipe
import org.schabi.newpipe.extractor.ServiceList
import org.schabi.newpipe.extractor.services.youtube.YoutubeService
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar NewPipe (obligatorio)
        NewPipe.init(YouTubeDownloaderDownloader.getInstance()) 

        val urlInput = findViewById<EditText>(R.id.urlInput)
        val downloadBtn = findViewById<Button>(R.id.downloadBtn)
        val statusText = findViewById<TextView>(R.id.statusText)

        downloadBtn.setOnClickListener {
            val url = urlInput.text.toString()
            statusText.text = "Analizando video..."

            // La extracción debe ir en un hilo separado para no trabar la app
            thread {
                try {
                    val service = ServiceList.YouTube
                    val extractor = service.getStreamExtractor(url)
                    extractor.fetchPage()
                    
                    val title = extractor.name
                    
                    runOnUiThread {
                        statusText.text = "Video encontrado: $title\nIniciando extracción de links..."
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        statusText.text = "Error: Link no válido o sin conexión"
                    }
                }
            }
        }
    }
}
