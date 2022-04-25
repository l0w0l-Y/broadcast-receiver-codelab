package com.kaleksandra.broadcasts.codelab

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.kaleksandra.broadcasts.codelab.CustomReceiver.Companion.ACTION_CUSTOM_BROADCAST
import com.kaleksandra.broadcasts.codelab.CustomReceiver.Companion.RANDOM_EXTRA
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val mReceiver: CustomReceiver = CustomReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        filter.addAction(Intent.ACTION_POWER_CONNECTED)
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                mReceiver,
                IntentFilter(ACTION_CUSTOM_BROADCAST)
            )
        setContentView(R.layout.activity_main)
        // Register the receiver using the activity context.
        this.registerReceiver(mReceiver, filter)
    }

    override fun onStart() {
        super.onStart()
        findViewById<Button>(R.id.sendBroadcast).setOnClickListener {
            val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
            val rand = (0..10).random(Random(System.currentTimeMillis()))
            customBroadcastIntent.putExtra(RANDOM_EXTRA, rand)
            LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent)
        }
    }

    override fun onDestroy() {
        //Unregister the receiver
        this.unregisterReceiver(mReceiver)
        LocalBroadcastManager.getInstance(this)
            .unregisterReceiver(mReceiver)
        super.onDestroy()
    }
}