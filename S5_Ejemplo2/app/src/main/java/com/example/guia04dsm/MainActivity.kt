package com.example.guia04dsm
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Declaración de variables para los elementos de la interfaz de usuario
    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var buttonAdd: Button
    private lateinit var buttonSubtract: Button
    private lateinit var buttonMultiply: Button
    private lateinit var buttonDivide: Button
    private lateinit var textViewResult: TextView
    // Método que se llama cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Establece el archivo de diseño para esta actividad
// Inicialización de los elementos de la interfaz de usuario utilizando findViewById
        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonSubtract = findViewById(R.id.buttonSubtract)
        buttonMultiply = findViewById(R.id.buttonMultiply)
        buttonDivide = findViewById(R.id.buttonDivide)
        textViewResult = findViewById(R.id.textViewResult)
// Configuración de los listeners para los botones, que llaman al método calculate con la operación correspondiente
        buttonAdd.setOnClickListener { calculate(Operation.ADD) }
        buttonSubtract.setOnClickListener { calculate(Operation.SUBTRACT) }
        buttonMultiply.setOnClickListener { calculate(Operation.MULTIPLY) }
        buttonDivide.setOnClickListener { calculate(Operation.DIVIDE) }
    }
// Método que realiza el cálculo según la operación proporcionada
private fun calculate(operation: Operation) {
// Obtención de los valores de entrada como cadenas
    val input1Text = input1.text.toString()
    val input2Text = input2.text.toString()
// Verificación de que ambos campos de entrada no estén vacíos
    if (input1Text.isEmpty() || input2Text.isEmpty()) {
        Toast.makeText(this, "Por favor, ingresa ambos números",
            Toast.LENGTH_SHORT).show()
        return
    }
// Conversión de las entradas de texto a números de tipo Double
    val num1 = input1Text.toDouble()
    val num2 = input2Text.toDouble()
    var result = 0.0 // Variable para almacenar el resultado
// Realización de la operación correspondiente
    when (operation) {
        Operation.ADD -> result = num1 + num2
        Operation.SUBTRACT -> result = num1 - num2
        Operation.MULTIPLY -> result = num1 * num2
        Operation.DIVIDE -> {
// Manejo del caso de división por cero
            if (num2 == 0.0) {
                Toast.makeText(this, "No se puede dividir por cero",
                    Toast.LENGTH_SHORT).show()
                return
            }
            result = num1 / num2
        }
    }
// Actualización del TextView para mostrar el resultado
    textViewResult.text = getString(R.string.result) + result.toString()
}
    // Enumeración para representar las operaciones matemáticas
    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}