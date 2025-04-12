package com.example.calculadora.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.calculadora.R

class ConversaoMoedaBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.conversor_custom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etValor = view.findViewById<EditText>(R.id.etValor)
        val spinnerOrigem = view.findViewById<Spinner>(R.id.spinnerMoedaOrigem)
        val spinnerDestino = view.findViewById<Spinner>(R.id.spinnerMoedaDestino)
        val btnConverter = view.findViewById<Button>(R.id.btnConverter)
        val tvResultado = view.findViewById<TextView>(R.id.tvResultado)

        val moedas = listOf("BRL", "USD", "EUR", "JPY")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, moedas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigem.adapter = adapter
        spinnerDestino.adapter = adapter

        btnConverter.setOnClickListener {
            val valor = etValor.text.toString().toDoubleOrNull()
            if (valor != null && valor > 0) {
                // Pegando as moedas selecionadas nos Spinners
                val moedaOrigem = spinnerOrigem.selectedItem.toString()
                val moedaDestino = spinnerDestino.selectedItem.toString()

                // Lógica fictícia de conversão: você pode substituir por uma API real.
                val taxaConversao = obterTaxaDeConversao(moedaOrigem, moedaDestino)
                val resultado = valor * taxaConversao

                tvResultado.text = "Resultado: $resultado $moedaDestino"
            } else {
                tvResultado.text = "Digite um valor válido maior que zero"
            }
        }
    }

    // Função fictícia para obter a taxa de conversão entre as moedas
    private fun obterTaxaDeConversao(moedaOrigem: String, moedaDestino: String): Double {
        // Exemplo de lógica fictícia, substitua por uma API real
        return when (moedaOrigem) {
            "BRL" -> when (moedaDestino) {
                "USD" -> 0.19 // Exemplo de taxa fictícia
                "EUR" -> 0.18
                "JPY" -> 25.0
                else -> 1.0
            }
            "USD" -> when (moedaDestino) {
                "BRL" -> 5.0
                "EUR" -> 0.85
                "JPY" -> 130.0
                else -> 1.0
            }
            "EUR" -> when (moedaDestino) {
                "BRL" -> 5.5
                "USD" -> 1.18
                "JPY" -> 150.0
                else -> 1.0
            }
            "JPY" -> when (moedaDestino) {
                "BRL" -> 0.04
                "USD" -> 0.007
                "EUR" -> 0.0067
                else -> 1.0
            }
            else -> 1.0
        }
    }
}
