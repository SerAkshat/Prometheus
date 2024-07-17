package com.example.prometheus
import android.widget.TextView

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home : AppCompatActivity() {

    lateinit var db: DBHelper
    lateinit var email: TextView
    lateinit var name: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        db = DBHelper(this)
        email = findViewById(R.id.user_email)
        val userEmail = intent.getStringExtra("user_email")
        email.text = "Welcome, $userEmail!"
        val userData = db.getUserData(userEmail ?: "")
        name.text = "Welcome, $userData!"

        /*val userData = db.getUserData(email.toString())*/



    }
}