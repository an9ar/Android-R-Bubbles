package com.an9ar.bubblesexample

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bubble.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BubbleActivity : AppCompatActivity() {

    var messageList = ArrayList<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble)

        val adapter = ChatListAdapter(messages = messageList)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        rv_chatScroll.layoutManager = layoutManager
        rv_chatScroll.adapter = adapter

        sendButton.setOnClickListener {
            hideKeyBoard()
            messageList.add(ChatMessage(message = sendInput.text.toString(), date = SimpleDateFormat("hh:mm").format(Date())))
            adapter.notifyDataSetChanged()
            sendInput.text.clear()
        }
    }

    private fun hideKeyBoard(){
        val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}