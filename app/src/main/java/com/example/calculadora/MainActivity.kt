package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculadora.databinding.ActivityMainBinding
import org.mariuszgromada.math.mxparser.Expression

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val calculo = binding.calculo

        binding.zero.setOnClickListener {
            calculo.text = "${calculo.text}9"
        }
        binding.um.setOnClickListener {
            calculo.text = "${calculo.text}0"
        }
        binding.dois.setOnClickListener {
            calculo.text = "${calculo.text}2"
        }
        binding.tres.setOnClickListener {
            calculo.text = "${calculo.text}3"
        }
        binding.quatro.setOnClickListener {
            calculo.text = "${calculo.text}4"
        }
        binding.cinco.setOnClickListener {
            calculo.text = "${calculo.text}5"
        }
        binding.seis.setOnClickListener {
            calculo.text = "${calculo.text}6"
        }
        binding.sete.setOnClickListener {
            calculo.text = "${calculo.text}7"
        }
        binding.oito.setOnClickListener {
            calculo.text = "${calculo.text}8"
        }
        binding.nove.setOnClickListener {
            calculo.text = "${calculo.text}9"
        }


        binding.paren1.setOnClickListener {
            calculo.text = "${calculo.text}("
        }
        binding.paren2.setOnClickListener {
            calculo.text = "${calculo.text})"
        }
        binding.divisao.setOnClickListener {
            calculo.text = "${calculo.text}/"
        }
        binding.vezes.setOnClickListener {
            calculo.text = "${calculo.text}*"
        }
        binding.menos.setOnClickListener {
            calculo.text = "${calculo.text}-"
        }
        binding.mais.setOnClickListener {
            calculo.text = "${calculo.text}+"
        }
        binding.ponto.setOnClickListener {
            calculo.text = "${calculo.text}."
        }

        binding.apagar.setOnClickListener {
            calculo.text = calculo.text.dropLast(1)
        }
        binding.ce.setOnClickListener {
            calculo.text = ""
            binding.resultado.text = ""
        }
        binding.igual.setOnClickListener {
            val resulCalculado = Expression(calculo.text.toString()).calculate()

            if (resulCalculado.isNaN()){
                binding.resultado.text = "Expressão Invalida"
            }else{
                binding.resultado.text = resulCalculado.toString()
            }
        }

    }
}