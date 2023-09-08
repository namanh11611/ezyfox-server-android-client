package com.tvd12.ezyfoxserver.client.example.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.tvd12.ezyfoxserver.client.example.R
import com.tvd12.ezyfoxserver.client.example.data.MessageListAdapter
import com.tvd12.ezyfoxserver.client.example.socket.SocketClientProxy

class MessageListActivity : AppCompatActivity() {

    private lateinit var messageList: ListView
    private lateinit var messageListAdapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_list)
        val loading = findViewById<ProgressBar>(R.id.loading)
        messageListAdapter = MessageListAdapter(this)
        messageList = findViewById(R.id.messageList)
        messageList.adapter = messageListAdapter

        SocketClientProxy.apply {
            onDisconnectedCallback {
            }
            onMessage {
                messageListAdapter.add(it)
            }
            onDisconnectedCallback {
                loading.visibility = View.VISIBLE
            }
            onConnectionFailedCallback {
                val loginIntent = Intent(this@MessageListActivity, LoginActivity::class.java)
                loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(loginIntent)
            }
            sendGreetRequest()
        }
    }
}
