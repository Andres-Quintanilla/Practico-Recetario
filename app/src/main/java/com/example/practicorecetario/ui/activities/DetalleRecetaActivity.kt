package com.example.recetario.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicorecetario.databinding.ActivityDetalleRecetaBinding

class DetalleRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetalleRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener los datos enviados
        val nombre = intent.getStringExtra("nombre") ?: "Sin nombre"
        val ingredientes = intent.getStringExtra("ingredientes") ?: "Sin ingredientes"
        val preparacion = intent.getStringExtra("preparacion") ?: "Sin preparación"

        // Mostrar datos en pantalla
        binding.tvNombreReceta.text = nombre
        binding.tvIngredientesReceta.text = "Ingredientes:\n$ingredientes"
        binding.tvPreparacionReceta.text = "Preparación:\n$preparacion"
    }
}
