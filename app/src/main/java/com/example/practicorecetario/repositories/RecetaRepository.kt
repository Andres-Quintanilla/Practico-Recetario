package com.example.recetario.repositorios

import android.content.Context
import com.example.practicorecetario.db.BaseDeDatos
import com.example.practicorecetario.db.models.Receta

object RecetaRepository {

    suspend fun guardar(context: Context, receta: Receta) {
        BaseDeDatos.getInstance(context).recetaDao().insertar(receta)
    }

    suspend fun obtenerTodas(context: Context): List<Receta> {
        return BaseDeDatos.getInstance(context).recetaDao().obtenerTodas()
    }

    suspend fun insertarRecetasEjemplo(context: Context) {
        val dao = BaseDeDatos.getInstance(context).recetaDao()
        val existentes = dao.obtenerTodas().map { it.nombre.trim().lowercase() }

        val lista = listOf(
            Receta(nombre = "Majadito", ingredientes = "Arroz, Pollo, Huevo", preparacion = "Cocer arroz, mezclar con pollo y huevo."),
            Receta(nombre = "Arroz con huevo", ingredientes = "Arroz, Huevo", preparacion = "Hervir arroz y freír huevo."),
            Receta(nombre = "Sandwich", ingredientes = "Pan, Queso, Tomate", preparacion = "Unir los ingredientes entre dos panes."),
            Receta(nombre = "Ensalada fresca", ingredientes = "Lechuga, Tomate, Zanahoria, Sal", preparacion = "Cortar los vegetales y mezclarlos con sal."),
            Receta(nombre = "Puré de papa", ingredientes = "Papa, Sal", preparacion = "Hervir papas, machacar y sazonar con sal."),
            Receta(nombre = "Fideos con carne", ingredientes = "Fideos, Carne, Ajo", preparacion = "Cocer los fideos y saltear con carne y ajo."),
            Receta(nombre = "Sopa de verduras", ingredientes = "Zanahoria, Papa, Cebolla, Pimiento", preparacion = "Hervir todos los vegetales en agua con sal."),
            Receta(nombre = "Pescado al ajo", ingredientes = "Pescado, Ajo, Perejil", preparacion = "Cocinar el pescado con ajo y espolvorear perejil."),
            Receta(nombre = "Yuca frita", ingredientes = "Yuca, Sal", preparacion = "Hervir la yuca y luego freírla."),
        ).filter { it.nombre.trim().lowercase() !in existentes }

        lista.forEach { dao.insertar(it) }
    }
}
