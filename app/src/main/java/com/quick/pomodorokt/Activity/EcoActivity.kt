package com.quick.pomodorokt.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat
import com.airbnb.lottie.LottieAnimationView
import com.quick.pomodorokt.Notif.NotificationHelper
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_eco.*

class EcoActivity : AppCompatActivity() {
    private val START_MILI_SECONDS = 60000L
    private lateinit var coundownTime: CountDownTimer
    private var isRunning = false
    var TIME_IN_MILIS = 0L
    var nHelper = NotificationHelper()
    lateinit var bookAnim: LottieAnimationView
    lateinit var completeAnim: LottieAnimationView
    private lateinit var rg_time :RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_eco)
        supportActionBar?.hide()

        //notif channel
        nHelper.createNotificationManager(this, NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.app_name), "App Notification Channel")

        bookAnim = findViewById(R.id.bookAnimEco)
        completeAnim = findViewById(R.id.completeAnimEco)
        bookAnim.visibility = View.GONE
        completeAnim.visibility = View.GONE

        rg_time = findViewById(R.id.rg_time)
        val bundle = intent.extras
        var time = bundle?.getInt("time", 0)

        btn_startEco.setOnClickListener {
            tv_ecoDesc.visibility = View.GONE
            var selectedID = rg_time.checkedRadioButtonId
            var btnId = findViewById<RadioButton>(selectedID)
            if (isRunning) {
                pauseTimer()

            } else {
                completeAnim.visibility = View.GONE
                bookAnim.visibility = View.VISIBLE
                val time = time.toString()
                TIME_IN_MILIS = time.toLong() * 1000L // ini juga
                startTimer(TIME_IN_MILIS)
            }
        }

        btn_restartEco.setOnClickListener {

            resetTimer()
        }
    }



    private fun resetTimer() {
        TIME_IN_MILIS = START_MILI_SECONDS
        updateTextUI()
        btn_restartEco.visibility = View.INVISIBLE
    }

    private fun startTimer(timeInMilis: Long) {
        coundownTime = object : CountDownTimer(timeInMilis, 1000) {
            override fun onFinish() {
                Toast.makeText(this@EcoActivity, "Finish", Toast.LENGTH_LONG).show()
                nHelper.createSampleDataNotification(this@EcoActivity,"YUHUU", "Weheheheh", "Alooo", true)
                bookAnim.visibility = View.GONE
                completeAnim.visibility = View.VISIBLE
                btn_startEco.text = "START"
                tv_countEco.text = "FINISH"
                isRunning = false
                resetTimer()
            }

            override fun onTick(millisUntilFinished: Long) {
                TIME_IN_MILIS = millisUntilFinished
                updateTextUI()
            }

        }
        coundownTime.start()
        isRunning = true
        btn_startEco.text = "PAUSE"
        btn_restartEco.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minutes = (TIME_IN_MILIS / 1000) / 60
        val seconds = (TIME_IN_MILIS / 1000) % 60
        tv_countEco.text = "$minutes:$seconds"
    }

    private fun pauseTimer() {
        btn_startEco.text = "Start"
        coundownTime.cancel()
        isRunning = false
        btn_restartEco.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if (isRunning) {
            moveTaskToBack(true)
            Toast.makeText(this, "Tunggu Dulu", Toast.LENGTH_SHORT).show()
        } else {
            onPause()
            super.onBackPressed()
        }
    }



}
