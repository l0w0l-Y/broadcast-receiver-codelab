package com.kaleksandra.broadcasts.codelab

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.kaleksandra.broadcasts.codelab.CustomReceiver.Companion.ACTION_CUSTOM_BROADCAST

class CustomReceiver : BroadcastReceiver() {
    companion object {
        const val ACTION_CUSTOM_BROADCAST =
            BuildConfig.APPLICATION_ID + ".ACTION_CUSTOM_BROADCAST"
        const val RANDOM_EXTRA = "RANDOM_EXTRA"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val intentAction = intent.action
        if (intentAction != null) {
            var toastMessage = "unknown intent action"
            when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> toastMessage = "Power connected!"
                Intent.ACTION_POWER_DISCONNECTED -> toastMessage = "Power disconnected!"
                ACTION_CUSTOM_BROADCAST -> {
                    toastMessage = intent.getIntExtra(RANDOM_EXTRA, 0).toString()
                }
            }
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
        }
    }
}