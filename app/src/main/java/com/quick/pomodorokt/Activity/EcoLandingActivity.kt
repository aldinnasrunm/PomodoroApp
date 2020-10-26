package com.quick.pomodorokt.Activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.quick.pomodorokt.R
import kotlinx.android.synthetic.main.activity_eco_landing.*

class EcoLandingActivity : AppCompatActivity() {
    lateinit var rgSelectTime : RadioGroup
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eco_landing)
        supportActionBar?.hide()

        rgSelectTime = findViewById(R.id.rg_selectTime)
        
        rgSelectTime.setOnCheckedChangeListener { group, checkedId ->
            cekRadio()
        }

        cekRadio()
        btn_mulaiEco.setOnClickListener {
            val cek = cekRadio()
            if (cek != null&& cek !=0) {
                startEco(cek)
            }
//            Toast.makeText(this, "Time : "+cek, Toast.LENGTH_SHORT).show()
        }

    }

    private fun startEco(time : Int) {
        startActivity(Intent(this@EcoLandingActivity, EcoTimerActivity::class.java).putExtra("time",time))
        finish()
    }

     fun cekRadio(): Int? {
        val time : Int?
        if (rb_time15.isChecked){
//            Toast.makeText(this, "selected 15", Toast.LENGTH_SHORT).show()
            uiUpdate("15", rb_time15)
            time = 15
        }else if (rb_time30.isChecked){
//            Toast.makeText(this, "selected 30", Toast.LENGTH_SHORT).show()
            uiUpdate("30", rb_time30)
            time = 30
        }else if(rb_time45.isChecked){
//            Toast.makeText(this, "selected 45", Toast.LENGTH_SHORT).show()
            uiUpdate("45", rb_time45)
            time = 45

        }else{
            Toast.makeText(this, "no selected", Toast.LENGTH_SHORT).show()
            time = 0
        }
        return time
    }

    private fun uiUpdate(timeText:String, id: RadioButton){
        tv_time.text = timeText
        id.setTextColor(Color.WHITE)
    }

}