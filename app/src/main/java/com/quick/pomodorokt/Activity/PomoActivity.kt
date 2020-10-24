package com.quick.pomodorokt.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import com.airbnb.lottie.LottieAnimationView
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_pomo.*

class PomoActivity : AppCompatActivity() {
    val START_MILI_SECONDS = 60000L
    lateinit var coundownTime: CountDownTimer
    var isRunning: Boolean = false
    var TIME_IN_MILIS = 0L
    var i = 0
    lateinit var bookAnim: LottieAnimationView
    lateinit var completeAnim: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_pomo)

        supportActionBar?.hide()

        bookAnim = findViewById(R.id.bookAnim)
        completeAnim = findViewById(R.id.completeAnim)
        bookAnim.visibility = View.GONE
        completeAnim.visibility = View.GONE


        btn_start.setOnClickListener {
            if (isRunning) {
                pauseTimer()

            } else {
                completeAnim.visibility = View.GONE
                bookAnim.visibility = View.VISIBLE
                val time = 1
                TIME_IN_MILIS = time.toLong() * 60000L
                startTimer(TIME_IN_MILIS)
            }
        }

        btn_restart.setOnClickListener {

            resetTimer()
        }
    }

    private fun resetTimer() {
        TIME_IN_MILIS = START_MILI_SECONDS
        updateTextUI()
        btn_restart.visibility = View.INVISIBLE
    }

    private fun startTimer(timeInMilis: Long) {
        coundownTime = object : CountDownTimer(timeInMilis, 1000) {
            override fun onFinish() {
                Toast.makeText(this@PomoActivity, "Finish", Toast.LENGTH_LONG).show()
                bookAnim.visibility = View.GONE
                completeAnim.visibility = View.VISIBLE
                btn_start.text = "START"
                tv_count.text = "FINISH"
                resetTimer()
            }

            override fun onTick(millisUntilFinished: Long) {
                TIME_IN_MILIS = millisUntilFinished
                updateTextUI()
            }

        }
        coundownTime.start()
        isRunning = true
        btn_start.text = "PAUSE"
        btn_restart.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minutes = (TIME_IN_MILIS / 1000) / 60
        val seconds = (TIME_IN_MILIS / 1000) % 60
        tv_count.text = "$minutes:$seconds"
    }

    private fun pauseTimer() {
        btn_start.text = "Start"
        coundownTime.cancel()
        isRunning = false
        btn_restart.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        if(isRunning){
            Toast.makeText(this, "Tunggu Dulu", Toast.LENGTH_SHORT).show()
        }else{
            onPause()
            super.onBackPressed()
        }
    }
}