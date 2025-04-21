package com.example.calculadora

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConversorActivity : AppCompatActivity() {

    private lateinit var edtValor: EditText
    private lateinit var btnConverter: Button
    private lateinit var resultadoConversao: TextView
    private lateinit var spinnerOrigem: Spinner
    private lateinit var spinnerDestino: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversor)

        // Inicializando as views
        edtValor = findViewById(R.id.edtValor)
        btnConverter = findViewById(R.id.btnConverter)
        resultadoConversao = findViewById(R.id.resultadoConversao)
        spinnerOrigem = findViewById(R.id.spinnerOrigem)
        spinnerDestino = findViewById(R.id.spinnerDestino)

        // Preenchendo os Spinners com as opções de moedas
        val moedas = arrayOf("Real", "Dólar", "Euro", "Libra")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, moedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigem.adapter = adapter
        spinnerDestino.adapter = adapter

        // Configurando o clique do botão para realizar a conversão
        btnConverter.setOnClickListener {
            val valorEmReal = edtValor.text.toString()

            if (valorEmReal.isNotEmpty()) {
                val valor = valorEmReal.toDoubleOrNull()
                if (valor != null) {
                    // Pegando o valor das moedas selecionadas
                    val moedaOrigem = spinnerOrigem.selectedItem.toString()
                    val moedaDestino = spinnerDestino.selectedItem.toString()

                    // Realizando a conversão
                    val valorConvertido = realizarConversao(moedaOrigem, moedaDestino, valor)

                    // Exibindo o resultado
                    resultadoConversao.text = "Valor convertido: $valorConvertido $moedaDestino"
                } else {
                    Toast.makeText(this, "Por favor, insira um valor válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, insira um valor", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Função para realizar a conversão com base nas moedas selecionadas
    private fun realizarConversao(origem: String, destino: String, valor: Double): Double {
        return when (origem) {
            "Real" -> when (destino) {
                "Dólar" -> valor * 0.20
                "Euro" -> valor * 0.18
                "Libra" -> valor * 0.16
                else -> valor
            }
            "Dólar" -> when (destino) {
                "Real" -> valor * 5.0
                "Euro" -> valor * 0.90
                "Libra" -> valor * 0.80
                else -> valor
            }
            "Euro" -> when (destino) {
                "Real" -> valor * 5.5
                "Dólar" -> valor * 1.10
                "Libra" -> valor * 0.88
                else -> valor
            }
            "Libra" -> when (destino) {
                "Real" -> valor * 6.2
                "Dólar" -> valor * 1.25
                "Euro" -> valor * 1.14
                else -> valor
            }
            else -> valor
        }
    }
}
