package com.example.calculadora.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment // Certifique-se de importar esta classe
import com.example.calculadora.R

class HistoricoBottomSheet(private val historico: List<String>, private val onItemSelecionado: (String) -> Unit) : BottomSheetDialogFragment() {

    // Sobrescreve o método onCreateView da classe BottomSheetDialogFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.historico_custom, container, false)
    }

    // Sobrescreve o método onViewCreated para configurar a interação do BottomSheet
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutHistorico = view.findViewById<LinearLayout>(R.id.listaHistorico)

        // Adiciona os itens ao bottom sheet
        for (item in historico.reversed()) {
            val tv = TextView(context).apply {
                text = item
                textSize = 16f
                setPadding(8, 8, 8, 8)
                setOnClickListener {
                    onItemSelecionado(item)
                    dismiss() // Fecha o bottom sheet ao selecionar um item
                }
            }
            layoutHistorico.addView(tv)
        }
    }
}
