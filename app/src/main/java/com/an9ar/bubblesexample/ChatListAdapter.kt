package com.an9ar.bubblesexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ChatListAdapter(val messages: ArrayList<ChatMessage>) : RecyclerView.Adapter<ChatListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.chat_list_item, parent, false)
        return ChatListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        if (messages.isNotEmpty()){
            holder.bind(messages[position])
        }
    }
}