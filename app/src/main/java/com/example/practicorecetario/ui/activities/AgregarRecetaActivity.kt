package com.example.recetario.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.practicorecetario.databinding.ActivityAgregarRecetaBinding
import com.example.practicorecetario.db.models.Receta
import com.example.recetario.repositorios.RecetaRepository
import kotlinx.coroutines.launch

class AgregarRecetaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAgregarRecetaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarRecetaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGuardarReceta.setOnClickListener {
            val nombre = binding.etNombreReceta.text.toString().trim()
            val ingredientesTexto = binding.etIngredientes.text.toString().trim()
            val preparacion = binding.etPreparacion.text.toString().trim()

            if (nombre.isNotEmpty() && ingredientesTexto.isNotEmpty() && preparacion.isNotEmpty()) {
                val receta = Receta(nombre = nombre, ingredientes = ingredientesTexto, preparacion = preparacion)

                lifecycleScope.launch {
                    RecetaRepository.guardar(this@AgregarRecetaActivity, receta)

                    val intent = Intent(this@AgregarRecetaActivity, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
