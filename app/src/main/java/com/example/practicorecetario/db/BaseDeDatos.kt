package com.example.practicorecetario.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicorecetario.db.dao.IngredienteDao
import com.example.practicorecetario.db.dao.RecetaDao
import com.example.practicorecetario.db.models.Ingrediente
import com.example.practicorecetario.db.models.Receta


@Database(
    entities = [Ingrediente::class, Receta::class],
    version = 2
)
abstract class BaseDeDatos: RoomDatabase() {

    abstract fun ingredienteDao(): IngredienteDao
    abstract fun recetaDao(): RecetaDao

    companion object {
        @Volatile private var INSTANCE: BaseDeDatos? = null

        fun getInstance(context: Context): BaseDeDatos{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    BaseDeDatos::class.java,
                    "recetario_db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}