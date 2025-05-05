package com.example.recetario.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicorecetario.db.models.Receta
import com.example.recetario.repositorios.RecetaRepository
import kotlinx.coroutines.launch

class RecetaViewModel : ViewModel() {
    val recetas = MutableLiveData<List<Receta>>()

    fun cargarRecetas(context: Context, ingredientesSeleccionados: List<String>) {
        viewModelScope.launch {
            RecetaRepository.insertarRecetasEjemplo(context)
            val todas = RecetaRepository.obtenerTodas(context)

            val filtradas = if (ingredientesSeleccionados.isEmpty()) {
                todas
            } else {
                val recetasFiltradas = todas.filter { receta ->
                    val lista = receta.ingredientes.split(",").map { it.trim() }
                    ingredientesSeleccionados.all { it in lista } && lista.size == ingredientesSeleccionados.size
                }

                if (recetasFiltradas.isEmpty()) {
                    // Si no hay una receta exacta y hay más de 1 ingrediente seleccionado, no mostrar nada
                    if (ingredientesSeleccionados.size > 1) emptyList()
                    else {
                        // Si seleccionó solo un ingrediente, entonces sí mostramos todas las que lo contengan
                        todas.filter { receta ->
                            val lista = receta.ingredientes.split(",").map { it.trim() }
                            ingredientesSeleccionados.any { it in lista }
                        }
                    }
                } else recetasFiltradas
            }

            recetas.postValue(filtradas)
        }
    }

}

