package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.calculadora.databinding.ActivityConfigBinding
import com.example.calculadora.model.AppDatabase
import kotlinx.coroutines.launch

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding
    private var isDarkTheme = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fechar a Activity de Configurações
        binding.btnFechar.setOnClickListener {
            finish()
        }

        // Alternar entre os temas
        binding.btnToggleTheme.setOnClickListener {
            isDarkTheme = !isDarkTheme
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        // Buscar histórico diretamente do Room
        binding.btnHistorico.setOnClickListener {
            // Acessar o banco de dados
            val db = AppDatabase.getDatabase(this)
            val dao = db.historicoDao()

            lifecycleScope.launch {
                val historicoSalvo = dao.listar()  // Listar todos os itens do histórico

                // Passar o histórico como lista de Strings
                val listaStrings = historicoSalvo.map { "${it.expressao} = ${it.resultado}" }

                // Criar o intent para a activity do histórico
                val intent = Intent(this@ConfigActivity, HistoricoActivity::class.java)
                intent.putStringArrayListExtra("HISTORICO", ArrayList(listaStrings)) // Passar a lista de Strings
                startActivity(intent)
            }
        }

        // Abrir a tela de conversor de moeda
        binding.converterMoeda.setOnClickListener {
            startActivity(Intent(this, ConversorActivity::class.java))
        }
    }
}
