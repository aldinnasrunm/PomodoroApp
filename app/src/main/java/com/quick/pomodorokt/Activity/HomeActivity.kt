package com.quick.pomodorokt.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        btn_pomo.setOnClickListener {
            startActivity(Intent(this@HomeActivity, PomoActivity::class.java))
            Toast.makeText(this, "Pomodoro selected", Toast.LENGTH_SHORT).show()
        }
        btn_eco.setOnClickListener {
            Toast.makeText(this, "Eco selected", Toast.LENGTH_SHORT).show()

        }
    }
}