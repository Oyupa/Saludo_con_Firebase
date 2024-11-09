package com.example.saludo_con_firebase

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class PrimaryActivity : ComponentActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var rootLayout: LinearLayout
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)

        rootLayout = findViewById(R.id.rootLayout)
        sharedPreferences = getSharedPreferences("MyPreferences", MODE_PRIVATE)

        // Cargar el color de fondo guardado
        val savedColor = sharedPreferences.getInt("backgroundColor", Color.WHITE)
        rootLayout.setBackgroundColor(savedColor)

        val etUserName: EditText = findViewById(R.id.etUserName)
        val tvUserName: TextView = findViewById(R.id.tvUserName)
        val btnSaveLocal: Button = findViewById(R.id.btnSaveLocal)
        val btnSaveFirebase: Button = findViewById(R.id.btnSaveFirebase)
        val btnLoadFirebase: Button = findViewById(R.id.btnLoadFirebase)
        val btnSettings: Button = findViewById(R.id.btnSettings)


        // Cargar el nombre desde SharedPreferences al iniciar la actividad
        loadUserNameLocal(tvUserName)

        // Guardar el nombre de usuario en SharedPreferences
        btnSaveLocal.setOnClickListener {
            val userName = etUserName.text.toString()
            if (userName.isNotEmpty()) {
                saveUserNameLocal(userName)
                tvUserName.text = "Nombre guardado localmente: $userName"
            } else {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        // Guardar el nombre de usuario en Firebase
        btnSaveFirebase.setOnClickListener {
            val userName = etUserName.text.toString()
            if (userName.isNotEmpty()) {
                saveUserNameFirebase(User(userName,savedColor))
                tvUserName.text = "Nombre guardado en Firebase: $userName"
            } else {
                Toast.makeText(this, "Por favor ingresa un nombre", Toast.LENGTH_SHORT).show()
            }
        }

        // Cargar el nombre de usuario desde Firebase
        btnLoadFirebase.setOnClickListener {
            loadUserNameFirebase(tvUserName)
            loadBackgroundColor()

        }


        // Ir a la SettingsActivity
        btnSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }

    // Guardar nombre en SharedPreferences
    private fun saveUserNameLocal(userName: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userName", userName)
        editor.apply()
        Toast.makeText(this, "Nombre guardado localmente", Toast.LENGTH_SHORT).show()
    }

    // Cargar nombre desde SharedPreferences
    private fun loadUserNameLocal(tvUserName: TextView) {
        val userName = sharedPreferences.getString("userName", "No disponible")
        tvUserName.text = "Nombre cargado localmente: $userName"
    }

    // Guardar nombre en Firebase
    private fun saveUserNameFirebase(user: User) {
        db.collection("User")
            .document("userData")
            .set(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Nombre guardado en Firebase", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al guardar en Firebase: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    // Cargar nombre desde Firebase y guardarlo en SharedPreferences
    private fun loadUserNameFirebase(tvUserName: TextView) {
        db.collection("User")
            .document("userData")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject<User>()
                    val userName = user?.name ?: "No disponible"

                    // Mostrar el nombre cargado en el TextView
                    tvUserName.text = "Nombre cargado de Firebase: $userName"

                    // Guardar el nombre en SharedPreferences
                    saveUserNameLocal(userName)
                } else {
                    tvUserName.text = "No hay nombre guardado en Firebase."
                }
            }
            .addOnFailureListener { e ->
                tvUserName.text = "Error al cargar desde Firebase."
                Toast.makeText(this, "Error al cargar de Firebase: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    //Función para obtener el color de fondo guardado
    private fun loadBackgroundColor() {
        db.collection("User")
            .document("userData")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject<User>()
                    val color = user?.color ?: Color.WHITE
                    rootLayout.setBackgroundColor(color)
                    sharedPreferences.edit().putInt("backgroundColor", color).apply()
                }
            }
    }
}
