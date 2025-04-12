package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class tivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isDarkTheme = false // Estado do tema

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val calculo = binding.calculo

        fun adicionarAoCalculo(texto: String) {
            calculo.append(texto)
        }

        // Números
        binding.zero.setOnClickListener { adicionarAoCalculo("0") }
        binding.um.setOnClickListener { adicionarAoCalculo("1") }
        binding.dois.setOnClickListener { adicionarAoCalculo("2") }
        binding.tres.setOnClickListener { adicionarAoCalculo("3") }
        binding.quatro.setOnClickListener { adicionarAoCalculo("4") }
        binding.cinco.setOnClickListener { adicionarAoCalculo("5") }
        binding.seis.setOnClickListener { adicionarAoCalculo("6") }
        binding.sete.setOnClickListener { adicionarAoCalculo("7") }
        binding.oito.setOnClickListener { adicionarAoCalculo("8") }
        binding.nove.setOnClickListener { adicionarAoCalculo("9") }

        // Operadores
        binding.paren1.setOnClickListener { adicionarAoCalculo("(") }
        binding.paren2.setOnClickListener { adicionarAoCalculo(")") }
        binding.divisao.setOnClickListener { adicionarAoCalculo("/") }
        binding.vezes.setOnClickListener { adicionarAoCalculo("*") }
        binding.menos.setOnClickListener { adicionarAoCalculo("-") }
        binding.mais.setOnClickListener { adicionarAoCalculo("+") }
        binding.ponto.setOnClickListener { adicionarAoCalculo(".") }

        // Apagar e Limpar
        binding.apagar.setOnClickListener {
            calculo.text = calculo.text.dropLast(1)
        }

        binding.ce.setOnClickListener {
            calculo.text = ""
            binding.resultado.text = ""
        }

        // ABRIR MODAL DE CONFIGURAÇÃO
        binding.btnConfig.setOnClickListener {
            mostrarModalCustom(this)
        }

        // Calcular
        binding.igual.setOnClickListener {
            try {
                val resultadoCalculado = Expression(calculo.text.toString()).calculate()
                if (resultadoCalculado.isNaN()) {
                    binding.resultado.text = "Expressão inválida"
                } else {
                    binding.resultado.text = resultadoCalculado.toString()
                }
            } catch (e: Exception) {
                binding.resultado.text = "Erro no cálculo"
            }
        }
    }

    private fun mostrarModalCustom(context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.modal_custom, null)
        val builder = AlertDialog.Builder(context)
            .setView(dialogView)

        val alertDialog = builder.create()

        val fecharButton = dialogView.findViewById<Button>(R.id.modalButton)
        fecharButton.setOnClickListener {
            alertDialog.dismiss()
        }

        val toggleThemeButton = dialogView.findViewById<Button>(R.id.btnToggleTheme)
        toggleThemeButton.setOnClickListener {
            isDarkTheme = !isDarkTheme
            if (isDarkTheme) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        alertDialog.show()
    }
}
