package com.example.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_hottel.R

data class Message(val text: String, val isCustomer: Boolean)

class MessageAdapter(private val messages: List<Message>) :
    RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val leftMessage: TextView = view.findViewById(R.id.leftMessageTextView)
        val rightMessage: TextView = view.findViewById(R.id.rightMessageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        if (message.isCustomer) {
            holder.rightMessage.visibility = View.VISIBLE
            holder.leftMessage.visibility = View.GONE
            holder.rightMessage.text = message.text
        } else {
            holder.leftMessage.visibility = View.VISIBLE
            holder.rightMessage.visibility = View.GONE
            holder.leftMessage.text = message.text
        }
    }

    override fun getItemCount(): Int = messages.size
}
