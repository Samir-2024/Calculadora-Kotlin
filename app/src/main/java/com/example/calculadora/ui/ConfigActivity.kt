package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.calculadora.databinding.ActivityConfigBinding

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private var isDarkTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFechar.setOnClickListener {
            finish()
        }

        binding.btnToggleTheme.setOnClickListener {
            isDarkTheme = !isDarkTheme
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.btnHistorico.setOnClickListener {
            val historico = intent.getStringArrayListExtra("HISTORICO") ?: arrayListOf()
            val intent = Intent(this, HistoricoActivity::class.java)
            intent.putStringArrayListExtra("HISTORICO", historico)
            startActivity(intent)
        }

        binding.converterMoeda.setOnClickListener {
            startActivity(Intent(this, ConversorActivity::class.java))
        }
    }
}
