package com.example.calculadora.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoricoDao {
    @Insert
    suspend fun inserir(historico: Historico)

    @Query("SELECT * FROM historico ORDER BY id DESC")
    suspend fun listar(): List<Historico>
}
