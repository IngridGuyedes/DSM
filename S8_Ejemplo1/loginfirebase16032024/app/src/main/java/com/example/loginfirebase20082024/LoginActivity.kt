package com.example.loginfirebase20082024

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class LoginActivity: AppCompatActivity() {
    // Creamos la referencia del objeto FirebaseAuth private lateinit var auth: FirebaseAuth
//Referencia a componentes de nuestro Layout private lateinit var btnLogin: Button
    private lateinit var textViewRegister: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) setContentView (R.layout.activity_login)
// Inicializamos el objeto FirebaseAuth auth = FirebaseAuth.getInstance()
    }


    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
            }
            val intent = Intent(packageContext: this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}