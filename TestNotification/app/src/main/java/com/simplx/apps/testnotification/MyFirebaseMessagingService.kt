package com.simplx.apps.testnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseMessagingService : FirebaseMessagingService() {

    companion object {
        private const val TAG = "MyFirebaseMessagingServ"
        const val INTENT_FILTER = "INTENT_FILTER_BELL"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val intentFilter = Intent(INTENT_FILTER)
        sendBroadcast(intentFilter)

        val map: Map<String, String> = remoteMessage.data

        val title: String? = map["title"]
        val body: String? = map["content"]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            val importance = NotificationManager.IMPORTANCE_HIGH

            val mChannel =
                NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance)

            mChannel.description = Constants.CHANNEL_DESCRIPTION
            mChannel.enableLights(true)
            mChannel.lightColor = Color.RED
            mChannel.enableVibration(true)
            mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

            mNotificationManager.createNotificationChannel(mChannel)
        }

        MyNotificationManager.displayNotification(this, title, body)
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d(TAG, "ahmed $p0")
    }

}