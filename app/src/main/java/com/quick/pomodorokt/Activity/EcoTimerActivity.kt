package com.quick.pomodorokt.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.airbnb.lottie.LottieAnimationView
import com.quick.pomodorokt.Notif.NotificationHelper
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_eco.*
import kotlinx.android.synthetic.main.activity_eco_timer.*

class EcoTimerActivity : AppCompatActivity() {
    var nHelper = NotificationHelper()
    private val START_MILI_SECONDS = 60000L
    private lateinit var coundownTime: CountDownTimer
    private var isRunning = false
    var TIME_IN_MILIS = 0L
    lateinit var ecoAnim: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(1)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_eco_timer)
        supportActionBar?.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        window.setStatusBarColor(Color.TRANSPARENT)

        nHelper.createNotificationManager(
            this, NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.app_name), "App Notification Channel"
        )

        val bundle = intent.extras
        var time = bundle?.getInt("time", 0)
        val count = time.toString()
        TIME_IN_MILIS = count.toLong() * 1000L // ini juga
        startTimer(TIME_IN_MILIS)


    }

    private fun startTimer(timeInMilis: Long) {
        coundownTime = object : CountDownTimer(timeInMilis, 1000) {
            override fun onFinish() {
                nHelper.ecoNotification(
                    this@EcoTimerActivity,
                    "YUHUU",
                    "Weheheheh",
                    "Alooo",
                    true
                )
                isRunning = false
                startActivity(Intent(this@EcoTimerActivity, EcoLandingActivity::class.java))
                finish()
            }

            override fun onTick(millisUntilFinished: Long) {
                TIME_IN_MILIS = millisUntilFinished
                updateTextUI()
            }

        }
        coundownTime.start()
        isRunning = true
    }

    private fun updateTextUI() {
        val minutes = (TIME_IN_MILIS / 1000) / 60
        val seconds = (TIME_IN_MILIS / 1000) % 60
        tv_timeCount.text = "$minutes:$seconds"
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Hayoo jangan kaburr", Toast.LENGTH_SHORT).show()
    }

}