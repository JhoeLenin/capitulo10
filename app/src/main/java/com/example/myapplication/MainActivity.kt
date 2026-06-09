package com.example.myapplication

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var exoPlayer: ExoPlayer? = null
    private val videos = listOf(
        "Video 1" to R.raw.video1,
        "Video 2" to R.raw.video2,
        "Video 3" to R.raw.video3,
        "Video 4" to R.raw.video4
    )
    private val itemsOriginales = listOf(
        "Android Studio", "Kotlin", "Java", "Firebase", "Retrofit", "Glide", "Jetpack"
    )
    private var itemsActuales = itemsOriginales.toMutableList()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ImageView con Glide
        val imageUrl = "https://posgrado.unaj.net.pe/assets/img/UPG.e.png"
        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_logo)
            .error(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(binding.ivPerfil)

        binding.ivLogo.setOnClickListener {
            Toast.makeText(this, "Imagen seleccionada", Toast.LENGTH_SHORT).show()
        }

        binding.ivLogo.setOnLongClickListener {
            Toast.makeText(this, "Mantén presionado para cambiar foto", Toast.LENGTH_LONG).show()
            true
        }

        // Lista de Videos con ExoPlayer
        exoPlayer = ExoPlayer.Builder(this).build()
        binding.pvReproductor.player = exoPlayer

        val videoAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, videos.map { it.first })
        binding.lvVideos.adapter = videoAdapter
        binding.lvVideos.setOnItemClickListener { _, _, position, _ ->
            val (_, resId) = videos[position]
            val mediaItem = MediaItem.fromUri("android.resource://${packageName}/$resId")
            exoPlayer?.setMediaItem(mediaItem)
            exoPlayer?.prepare()
            exoPlayer?.play()
            Toast.makeText(this, "Reproduciendo: ${videos[position].first}", Toast.LENGTH_SHORT).show()
        }

        // WebView
        val webSettings = binding.wvContenido.settings
        webSettings.javaScriptEnabled = true
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.wvContenido.webViewClient = WebViewClient()
        val htmlContent = """
            <html>
                <body style="font-family: Arial;">
                    <h1>Términos y Condiciones</h1>
                    <p>Aquí van los términos...</p>
                </body>
            </html>
        """.trimIndent()
        binding.wvContenido.loadData(htmlContent, "text/html; charset=utf-8", "UTF-8")

        // CalendarView
        val today = Calendar.getInstance()
        binding.cvCalendario.minDate = today.timeInMillis

        binding.cvCalendario.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val fechaSeleccionada = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale("es", "ES"))
            val fechaFormato = dateFormat.format(fechaSeleccionada.time)
            binding.tvFecha.text = "Fecha seleccionada: $fechaFormato"
            Log.d("CALENDAR", "Fecha seleccionada: $fechaFormato")
            Toast.makeText(this, "Fecha: $fechaFormato", Toast.LENGTH_SHORT).show()
        }

        // ProgressBar horizontal con simulación de descarga
        binding.btnIniciarProgreso.setOnClickListener {
            binding.btnIniciarProgreso.isEnabled = false
            lifecycleScope.launch(Dispatchers.Default) {
                for (i in 0..100) {
                    delay(50)
                    withContext(Dispatchers.Main) {
                        binding.pbHorizontal.progress = i
                        binding.tvPorcentaje.text = "$i%"
                    }
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Progreso completado", Toast.LENGTH_SHORT).show()
                    binding.btnIniciarProgreso.isEnabled = true
                }
            }
        }

        // SeekBar
        binding.sbVolumen.progress = 50
        binding.sbVolumen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    binding.tvVolumen.text = "Volumen: $progress%"
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(this@MainActivity, "Volumen guardado: ${seekBar?.progress}%", Toast.LENGTH_SHORT).show()
            }
        })

        // RatingBar
        binding.rbCalificacion.setOnRatingBarChangeListener { _, rating, fromUser ->
            if (fromUser) {
                binding.tvRating.text = "Calificación: ${String.format("%.1f", rating)} / 5"
                Toast.makeText(this, "Calificación: $rating", Toast.LENGTH_SHORT).show()
            }
        }

        // SearchView con ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsActuales)
        binding.lvResultados.adapter = adapter

        binding.svBusqueda.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.svBusqueda.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                itemsActuales = if (newText.isNullOrEmpty()) {
                    itemsOriginales.toMutableList()
                } else {
                    itemsOriginales.filter { it.contains(newText, ignoreCase = true) }.toMutableList()
                }
                adapter.clear()
                adapter.addAll(itemsActuales)
                adapter.notifyDataSetChanged()
                return true
            }
        })

        binding.lvResultados.setOnItemClickListener { _, _, position, _ ->
            val seleccionado = itemsActuales[position]
            Toast.makeText(this, "Seleccionaste: $seleccionado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.release()
        exoPlayer = null
    }
}
