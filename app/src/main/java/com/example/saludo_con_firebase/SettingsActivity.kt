package com.example.saludo_con_firebase

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.ComponentActivity

class SettingsActivity : ComponentActivity() {

    private lateinit var rootLayout: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        rootLayout = findViewById(R.id.rootLayout)
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        // Cargar el color de fondo guardado
        val savedColor = sharedPreferences.getInt("backgroundColor", Color.WHITE)
        rootLayout.setBackgroundColor(savedColor)

        // Botones para cambiar el color de fondo
        findViewById<Button>(R.id.buttonWhite).setOnClickListener {
            changeBackgroundColor(Color.WHITE)
        }

        findViewById<Button>(R.id.buttonGray).setOnClickListener {
            changeBackgroundColor(Color.GRAY)
        }

        findViewById<Button>(R.id.buttonRed).setOnClickListener {
            changeBackgroundColor(Color.parseColor("#FF6347"))
        }

        findViewById<Button>(R.id.buttonGreen).setOnClickListener {
            changeBackgroundColor(Color.parseColor("#32CD32"))
        }

        findViewById<Button>(R.id.buttonBlue).setOnClickListener {
            changeBackgroundColor(Color.parseColor("#4682B4"))
        }

        // Botón para volver a la MainActivity
        findViewById<Button>(R.id.buttonBackToMain).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

        }
    }

    private fun changeBackgroundColor(color: Int) {
        rootLayout.setBackgroundColor(color)
        // Guardar el color en SharedPreferences
        sharedPreferences.edit().putInt("backgroundColor", color).apply()
    }
}

