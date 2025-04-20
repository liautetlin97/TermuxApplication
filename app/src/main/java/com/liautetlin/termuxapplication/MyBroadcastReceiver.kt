package com.liautetlin.termuxapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val command = intent.getStringExtra("command") ?: return
        Toast.makeText(context, "Received command: $command", Toast.LENGTH_SHORT).show()
    }
}