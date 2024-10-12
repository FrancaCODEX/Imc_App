package com.example.imc_app

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextWeight = findViewById<EditText>(R.id.editTextWeight)
        val editTextHeight = findViewById<EditText>(R.id.editTextHeight)
        val radioGroupSex = findViewById<RadioGroup>(R.id.radioGroupSex)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCalculate.setOnClickListener {
            // Captura os valores de peso e altura
            val weight = editTextWeight.text.toString().toFloatOrNull()
            val height = editTextHeight.text.toString().toFloatOrNull()

            // Captura o sexo selecionado
            val selectedSexId = radioGroupSex.checkedRadioButtonId
            val sex = if (selectedSexId == R.id.radioMale) "Masculino" else "Feminino"

            // Valida os dados
            if (weight != null && height != null && height > 0) {
                // Calcula o IMC
                val imc = weight / (height * height)

                // Exibe o resultado com base no sexo
                val imcResult = String.format("Seu IMC é: %.2f", imc)
                val interpretation = when {
                    imc < 18.5 -> "Abaixo do peso"
                    imc in 18.5..24.9 -> "Peso normal"
                    imc in 25.0..29.9 -> "Sobrepeso"
                    else -> "Obesidade"
                }

                textViewResult.text = "$imcResult\nInterpretação ($sex): $interpretation"
            } else {
                textViewResult.text = "Por favor, insira valores válidos"
            }
        }
    }
}
