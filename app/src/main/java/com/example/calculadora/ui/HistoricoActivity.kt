package com.example.calculadora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityHistoricoBinding

class HistoricoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoricoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Recupera o histórico enviado para a Activity sem argumentos de tipo genérico
        val historico = intent.getStringArrayListExtra("HISTORICO") ?: ArrayList()

        // Exibe o histórico na TextView dentro do ScrollView
        binding.listaHistorico.text = historico.joinToString("\n")
    }
}
