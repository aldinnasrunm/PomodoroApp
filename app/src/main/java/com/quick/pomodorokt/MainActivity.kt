package com.quick.pomodorokt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quick.pomodorokt.Activity.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        GlobalScope.launch {
            delay(2000)
            startActivity(Intent(this@MainActivity, HomeActivity::class.java))
        }
    }
}
