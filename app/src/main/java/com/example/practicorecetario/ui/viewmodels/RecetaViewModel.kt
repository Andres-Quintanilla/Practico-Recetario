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
                todas.filter { receta ->
                    val ingredientesReceta = receta.ingredientes.split(",").map { it.trim() }
                    ingredientesSeleccionados.any { it in ingredientesReceta }
                }.sortedByDescending { receta ->
                    val ingredientesReceta = receta.ingredientes.split(",").map { it.trim() }
                    ingredientesSeleccionados.count { it in ingredientesReceta }
                }
            }

            recetas.postValue(filtradas)
        }
    }
}

