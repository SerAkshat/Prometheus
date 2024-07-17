package com.example.prometheus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class loginactivity : AppCompatActivity() {
    lateinit var emailInput : EditText
    lateinit var passwordInput : EditText
    lateinit var loginButton : Button
    lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_loginactivity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        emailInput=findViewById(R.id.Email_input)
        passwordInput = findViewById(R.id.password_input)
        loginButton = findViewById(R.id.login_button)
        db = DBHelper(this)

        loginButton.setOnClickListener {
            val password = passwordInput.text.toString()
            val email = emailInput.text.toString()
            val checklog: Boolean = db.checklogin(email,password)
            if(checklog==true) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, home::class.java)
                intent.putExtra("user_email", email)
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Please check your id or password", Toast.LENGTH_SHORT).show()
            }
        }

    }
}