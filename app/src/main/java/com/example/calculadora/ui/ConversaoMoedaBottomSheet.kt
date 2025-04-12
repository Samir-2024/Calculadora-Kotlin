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
    ): View {
        return inflater.inflate(R.layout.convesor_custom, container, false)
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
            if (valor != null) {
                val resultado = valor * 5.0 // valor fictício para teste
                tvResultado.text = "Resultado: $resultado"
            } else {
                tvResultado.text = "Digite um valor válido"
            }
        }
    }
}
