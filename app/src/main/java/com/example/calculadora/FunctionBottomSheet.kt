package com.example.calculadora

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.calculadora.databinding.BottomSheetLayoutBinding

class FunctionBottomSheet : DialogFragment() {

    private lateinit var binding: BottomSheetLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla o layout do BottomSheet
        binding = BottomSheetLayoutBinding.inflate(inflater, container, false)

        // Configurar o comportamento dos elementos da view
        val option1 = binding.textOption1
        val option2 = binding.textOption2

        option1.setOnClickListener {
            // Realiza alguma ação quando a primeira opção for clicada
            dismiss()  // Fecha o BottomSheet
        }

        option2.setOnClickListener {
            // Realiza alguma ação quando a segunda opção for clicada
            dismiss()  // Fecha o BottomSheet
        }

        return binding.root
    }

    companion object {
        fun newInstance(): FunctionBottomSheet {
            return FunctionBottomSheet()
        }
    }
}
