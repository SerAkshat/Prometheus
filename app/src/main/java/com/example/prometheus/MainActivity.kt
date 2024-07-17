package com.example.prometheus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput : EditText
    lateinit var emailInput : EditText
    lateinit var passwordInput : EditText
    lateinit var alreadyUserButton : Button
    lateinit var signupButton : Button
    lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        usernameInput=findViewById(R.id.username_input)
        emailInput=findViewById(R.id.Email_input)
        passwordInput = findViewById(R.id.password_input)
        alreadyUserButton = findViewById(R.id.already_user)
        signupButton = findViewById(R.id.Signup_button)
        db = DBHelper(this)

        signupButton.setOnClickListener {

            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val email = emailInput.text.toString()
            Log.i("Credentials", "Email : $email and Username : $username and Password : $password")
            if (email.isEmpty() || password.isEmpty() || username.isEmpty()) {
                Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            } else {
                val checkUser = db.checkemail(email)
                if (!checkUser) {
                    val checkInsert = db.insertData(email, password)
                    if (checkInsert) {
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, home::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "User Already Exists", Toast.LENGTH_SHORT).show()
                }
            }
        }

        alreadyUserButton.setOnClickListener {
            val intent = Intent(this, loginactivity::class.java)
            startActivity(intent)
            }
        }


    }
