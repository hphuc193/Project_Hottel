package com.example.project_hottel

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatapp.Message
import com.example.chatapp.MessageAdapter
import com.example.project_hottel.R
import com.example.project_hottel.databinding.ActivityChatBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class ChatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChatBinding
    private val messages = mutableListOf<Message>()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cấu hình RecyclerView
        adapter = MessageAdapter(messages)
        binding.messageRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.messageRecyclerView.adapter = adapter

        // Xử lý gửi tin nhắn
        binding.sendMessageButton.setOnClickListener {
            val messageText = binding.messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                // Thêm tin nhắn của khách hàng
                messages.add(Message(messageText, true))
                adapter.notifyItemInserted(messages.size - 1)
                binding.messageRecyclerView.scrollToPosition(messages.size - 1)

                // Clear thanh nhập tin nhắn
                binding.messageEditText.text.clear()

                // Thêm tin nhắn phản hồi từ nhân viên hỗ trợ
                addReply("Cảm ơn bạn đã liên hệ! Chúng tôi sẽ phản hồi sớm.")
            }
        }
        val backButton = findViewById<Button>(R.id.backbtn11)

        backButton.setOnClickListener {
            finish()
        }

    }

    private fun addReply(replyText: String) {
        messages.add(Message(replyText, false))
        adapter.notifyItemInserted(messages.size - 1)
        binding.messageRecyclerView.scrollToPosition(messages.size - 1)
    }
}
