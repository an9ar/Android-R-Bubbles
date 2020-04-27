package com.kubsau.bubblesexample

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    private val messageText: TextView = itemView.findViewById(R.id.messageText)
    private val messageDate: TextView = itemView.findViewById(R.id.messageDate)

    fun bind(item: ChatMessage){
        messageText.text = item.message
        messageDate.text = item.date
    }
}