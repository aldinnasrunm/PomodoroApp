package com.quick.pomodorokt.Activity

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.airbnb.lottie.LottieAnimationView
import com.quick.pomodorokt.Notif.NotificationHelper
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_pomo.*

class PomoActivity : AppCompatActivity() {
    private val START_MILI_SECONDS = 0L
    private lateinit var coundownTime: CountDownTimer
    private var isRunning = false
    var TIME_IN_MILIS = 0L
    var nHelper = NotificationHelper()

    lateinit var bookAnim: LottieAnimationView
    lateinit var completeAnim: LottieAnimationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_pomo)

        supportActionBar?.hide()


        nHelper.createNotificationManager(this, NotificationManagerCompat.IMPORTANCE_HIGH, false,
            getString(R.string.app_name), "App Notification Channel")

        bookAnim = findViewById(R.id.bookAnim)
        completeAnim = findViewById(R.id.completeAnim)
        bookAnim.visibility = View.GONE
        iv_circle.visibility = View.GONE
        completeAnim.visibility = View.GONE
        btn_restart.visibility = View.GONE


        btn_start.setOnClickListener {
            if (isRunning) {
                pauseTimer()
                bookAnim.pauseAnimation()

            } else {
                tv_welcome.visibility = View.GONE
                bookAnim.playAnimation()
                completeAnim.visibility = View.GONE
                bookAnim.visibility = View.VISIBLE
                iv_circle.visibility = View.VISIBLE
                val time = 25 //jan lupa diganti
                TIME_IN_MILIS = time.toLong() * 60000L // ini juga
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
                nHelper.createSampleDataNotification(this@PomoActivity,"YUHUU", "Weheheheh", "Alooo", true)
                bookAnim.visibility = View.GONE
                completeAnim.visibility = View.VISIBLE
                completeAnim.playAnimation()
                btn_start.setImageDrawable(resources.getDrawable(R.drawable.ic_start))
                tv_count.text = "FINISH"
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
        btn_start.setImageDrawable(resources.getDrawable(R.drawable.ic_pause))
        btn_restart.visibility = View.INVISIBLE
    }

    private fun updateTextUI() {
        val minutes = (TIME_IN_MILIS / 1000) / 60
        val seconds = (TIME_IN_MILIS / 1000) % 60
        tv_count.text = "$minutes:$seconds"
    }

    private fun pauseTimer() {
        btn_start.setImageDrawable(resources.getDrawable(R.drawable.ic_start))
        coundownTime.cancel()
        isRunning = false
        btn_restart.visibility = View.VISIBLE
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




