package com.simplx.apps.testnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat

object MyNotificationManager {

    fun displayNotification(context: Context, title: String?, body: String?) {

        val mBuilder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(body)

        val resultIntent = Intent(context, TestActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        mBuilder.setContentIntent(pendingIntent)
        val mNotifyMgr = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        mNotifyMgr.notify(1, mBuilder.build())
    }
}
