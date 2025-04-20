package com.liautetlin.termuxapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import com.liautetlin.termuxapplication.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var outputReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.sendButton.setOnClickListener {
            val command = binding.edtCommand.text.toString()
            sendCommandBroadcast(command)
        }

        outputReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val receivedCommand = intent.getStringExtra("command") ?: return
                Toast.makeText(context, "Received command: $receivedCommand", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val filter = IntentFilter("com.liautetlin.termuxapplication.COMMAND_BROADCAST")
        registerReceiver(outputReceiver, filter, RECEIVER_NOT_EXPORTED)
    }

    private fun sendCommandBroadcast(command: String) {
        val intent = Intent("com.liautetlin.termuxapplication.COMMAND_BROADCAST")
        intent.putExtra("command", command)
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(outputReceiver)
    }
}