package com.example.calculadora

import android.os.Bundle
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression
import android.content.Intent


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isDarkTheme = false // Estado do tema
    private var alertDialog: AlertDialog? = null // Variável para controlar o AlertDialog
    private val historico = mutableListOf<String>()

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

        binding.btnConfig.setOnClickListener {
            val intent = Intent(this, ConfigActivity::class.java)
            intent.putStringArrayListExtra("HISTORICO", ArrayList(historico))
            startActivity(intent)
        }

        binding.igual.setOnClickListener {
            try {
                val expressao = calculo.text.toString()
                val resultadoCalculado = Expression(expressao).calculate()
                if (resultadoCalculado.isNaN()) {
                    binding.resultado.text = "Expressão inválida"
                } else {
                    val resultadoTexto = resultadoCalculado.toString()
                    binding.resultado.text = resultadoTexto
                    historico.add("$expressao = $resultadoTexto")
                }
            } catch (e: Exception) {
                binding.resultado.text = "Erro no cálculo"
            }
        }
    }

    override fun onPause() {
        super.onPause()
        // Fecha o alertDialog se ele estiver aberto ao pausar a Activity
        alertDialog?.dismiss()
    }
}
