package com.example.practicorecetario.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Receta(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val ingredientes: String, // Texto separado por comas
    val preparacion: String
)
