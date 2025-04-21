// HistoricoActivity.kt
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

        val historico = intent.getStringArrayListExtra("HISTORICO") ?: arrayListOf()
        binding.tvHistorico.text = historico.joinToString("\n")
    }
}
