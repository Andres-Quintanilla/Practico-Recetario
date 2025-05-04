package com.example.practicorecetario.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practicorecetario.db.models.Receta

@Dao
interface RecetaDao {
    @Query("SELECT * FROM Receta")
    suspend fun obtenerTodas(): List<Receta>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(receta: Receta)
}
