package com.example.recetario.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.practicorecetario.databinding.ActivityMainBinding
import com.example.recetario.ui.adapters.IngredienteAdapter
import com.example.recetario.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var adapter: IngredienteAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        setupListeners()

        viewModel.cargarIngredientes(this)
    }

    private fun setupRecyclerView() {
        binding.rvIngredientes.layoutManager = GridLayoutManager(this, 2)
        adapter = IngredienteAdapter(emptyList()) {

        }
        binding.rvIngredientes.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.ingredientes.observe(this) { lista ->
            adapter = IngredienteAdapter(lista) {
                  Toast.makeText(this, "Seleccionado: ${it.nombre}", Toast.LENGTH_SHORT).show()
            }
            binding.rvIngredientes.adapter = adapter
        }
    }

    private fun setupListeners() {
        binding.btnBuscarReceta.setOnClickListener {
            val seleccionados = adapter?.getSeleccionados() ?: emptyList()

            if (seleccionados.isNotEmpty()) {
                val nombres = seleccionados.map { it.nombre }.toTypedArray()
                val intent = Intent(this, ListaRecetasActivity::class.java)
                intent.putExtra("ingredientes", nombres)
                startActivity(intent)
            } else {
               Toast.makeText(this, "Selecciona al menos un ingrediente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
