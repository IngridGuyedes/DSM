package edu.udb.loginfirebase16032024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var etRegisterEmail: EditText
    private lateinit var etRegisterPassword: EditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        etRegisterEmail = findViewById(R.id.etRegisterEmail)
        etRegisterPassword = findViewById(R.id.etRegisterPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            val email: String = etRegisterEmail.text.toString()
            val password: String = etRegisterPassword.text.toString()
            signUp(email, password)
        }
    }

    private fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registro exitoso, actualizar la interfaz de usuario con la información del usuario registrado
                    val user = auth.currentUser
                    Log.d(TAG, "signUpWithEmail:success, User: $user")
                    // Aquí puedes redirigir a la siguiente actividad o realizar otras operaciones después del registro exitoso
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Si el registro falla, mostrar un mensaje al usuario.
                    Log.w(TAG, "signUpWithEmail:failure", task.exception)
                    // Aquí puedes mostrar un mensaje de error al usuario, por ejemplo, Toast
                }
            }
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}
