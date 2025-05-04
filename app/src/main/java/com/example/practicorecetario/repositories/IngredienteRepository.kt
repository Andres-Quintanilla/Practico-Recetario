package com.example.recetario.repositorios

import android.content.Context
import com.example.practicorecetario.db.BaseDeDatos
import com.example.practicorecetario.db.models.Ingrediente


object IngredienteRepository {
    suspend fun obtenerIngredientes(context: Context): List<Ingrediente> {
        return BaseDeDatos.getInstance(context).ingredienteDao().obtenerTodos()
    }

    suspend fun insertarEjemploIngredientes(context: Context) {
        val dao = BaseDeDatos.getInstance(context).ingredienteDao()

        val existentes = dao.obtenerTodos().map { it.nombre }

        val lista = listOf(
            Ingrediente(nombre = "Huevo", imagen = "huevo"),
            Ingrediente(nombre = "Tomate", imagen = "tomate"),
            Ingrediente(nombre = "Queso", imagen = "queso"),
            Ingrediente(nombre = "Lechuga", imagen = "lechuga"),
            Ingrediente(nombre = "Arroz", imagen = "arroz"),
            Ingrediente(nombre = "Pollo", imagen = "pollo"),
            Ingrediente(nombre = "Papa", imagen = "papa"),
            Ingrediente(nombre = "Cebolla", imagen = "cebolla"),
            Ingrediente(nombre = "Zanahoria", imagen = "zanahoria"),
            Ingrediente(nombre = "Pimiento", imagen = "pimiento"),
            Ingrediente(nombre = "Carne", imagen = "carne"),
            Ingrediente(nombre = "Pan", imagen = "pan"),
            Ingrediente(nombre = "Ajo", imagen = "ajo"),
            Ingrediente(nombre = "Perejil", imagen = "perejil"),
            Ingrediente(nombre = "Fideos", imagen = "fideos"),
            Ingrediente(nombre = "Yuca", imagen = "yuca"),
            Ingrediente(nombre = "Pescado", imagen = "pescado"),
            Ingrediente(nombre = "Sal", imagen = "sal"),
        )

        val nuevos = lista.filter { it.nombre !in existentes }

        dao.insertarTodos(nuevos)
    }

}
