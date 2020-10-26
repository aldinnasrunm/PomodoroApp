package com.quick.pomodorokt.Notif

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.quick.pomodorokt.Activity.PomoActivity
import com.quick.pomodorokt.R

class NotificationHelper {
   fun createNotificationManager(context: Context, importance : Int, showBadge : Boolean, name : String, description : String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun createSampleDataNotification(context: Context, title : String, message :  String, bigText : String, autoCancel : Boolean){
        // 1
        val channelId = "${context.packageName}-${context.getString(R.string.app_name)}"
// 2


        val notificationBuilder = NotificationCompat.Builder(context, channelId).apply {
            setSmallIcon(R.drawable.ic_av_timer) // 3
            setContentTitle(title) // 4
            setContentText(message) // 5
            setStyle(NotificationCompat.BigTextStyle().bigText(bigText))
            addAction(R.drawable.ic_av_timer, "aaaaa", null)
            priority = NotificationCompat.PRIORITY_DEFAULT // 7
            setAutoCancel(autoCancel) // 8
        }
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(1001, notificationBuilder.build())

    }

}