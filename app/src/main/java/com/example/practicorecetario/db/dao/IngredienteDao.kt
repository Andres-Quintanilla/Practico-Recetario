package com.example.practicorecetario.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicorecetario.db.models.Ingrediente


@Dao
interface IngredienteDao {
   @Query("select * from Ingrediente")
   suspend fun obtenerTodos(): List<Ingrediente>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(ingrediente: Ingrediente): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertarTodos(ingredientes: List<Ingrediente>)

}