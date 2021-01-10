package com.simplx.apps.testnotification

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService


class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {

    companion object {
        private const val TAG = "MyFirebaseInstanceIdSer"
    }

    override fun onTokenRefresh() {
        super.onTokenRefresh()

        val token = FirebaseInstanceId.getInstance().token

        Log.d(TAG, "onTokenRefresh: $token")
    }
}