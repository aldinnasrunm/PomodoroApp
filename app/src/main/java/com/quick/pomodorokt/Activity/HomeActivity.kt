package com.quick.pomodorokt.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    lateinit var cardPomo : CardView
    lateinit var cardEco : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.setStatusBarColor(Color.TRANSPARENT)

        cardPomo = findViewById(R.id.cardPomo)
        cardEco = findViewById(R.id.cardEco)

        cardPomo.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PomoActivity::class.java))
            Toast.makeText(this, "Pomodoro selected", Toast.LENGTH_SHORT).show()
        }
        cardEco.setOnClickListener {
            startActivity(Intent(this@HomeActivity, EcoLandingActivity::class.java))
            Toast.makeText(this, "Eco selected", Toast.LENGTH_SHORT).show()

        }
    }
}