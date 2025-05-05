package com.example.recetario.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicorecetario.databinding.ActivityListaRecetasBinding
import com.example.recetario.ui.adapters.RecetaAdapter
import com.example.recetario.ui.viewmodel.RecetaViewModel

class ListaRecetasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaRecetasBinding
    private lateinit var recetaAdapter: RecetaAdapter
    private val recetaViewModel: RecetaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaRecetasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        val ingredientesSeleccionados = intent.getStringArrayExtra("ingredientes")?.toList() ?: emptyList()

        recetaViewModel.recetas.observe(this) { recetas ->
            if (recetas.isEmpty()) {
                binding.tvMensaje.visibility = android.view.View.VISIBLE
                binding.btnAgregarReceta.visibility = android.view.View.VISIBLE
            } else {
                recetaAdapter.setData(recetas)
            }
        }

        recetaViewModel.cargarRecetas(this, ingredientesSeleccionados)

        binding.btnAgregarReceta.setOnClickListener {
             startActivity(Intent(this, AgregarRecetaActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        recetaAdapter = RecetaAdapter(emptyList()) { }
        binding.rvRecetas.layoutManager = LinearLayoutManager(this)
        binding.rvRecetas.adapter = recetaAdapter
    }
}
