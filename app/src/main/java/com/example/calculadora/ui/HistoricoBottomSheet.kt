package com.example.calculadora.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.calculadora.R

class HistoricoBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(historico: List<String>): HistoricoBottomSheet {
            val fragment = HistoricoBottomSheet()
            val bundle = Bundle()
            bundle.putStringArrayList("historico", ArrayList(historico))
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_historico, container, false)
        val historicoLista = arguments?.getStringArrayList("historico") ?: arrayListOf()

        val historicoTextView = view.findViewById<TextView>(R.id.historicoTextView)
        historicoTextView.text = historicoLista.joinToString("\n")

        return view
    }
}
