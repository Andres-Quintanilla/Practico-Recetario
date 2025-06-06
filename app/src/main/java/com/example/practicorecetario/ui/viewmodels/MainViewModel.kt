package com.example.recetario.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicorecetario.db.models.Ingrediente
import com.example.recetario.repositorios.IngredienteRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val ingredientes = MutableLiveData<List<Ingrediente>>()

    fun cargarIngredientes(context: Context) {
        viewModelScope.launch {
            IngredienteRepository.insertarEjemploIngredientes(context)

            ingredientes.postValue(IngredienteRepository.obtenerIngredientes(context))
        }
    }


}
