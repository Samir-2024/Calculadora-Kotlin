package com.example.calculadora.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "historico")
data class Historico(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val expressao: String,
    val resultado: String
)
