package com.example.hotel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RoomDetailActivity : AppCompatActivity() {

    private lateinit var txtRoomName: TextView
    private lateinit var btnBook: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_detail)

        txtRoomName = findViewById(R.id.txtRoomName)
        btnBook = findViewById(R.id.btnBook)

        val roomName = intent.getStringExtra("roomName")
        txtRoomName.text = "Chi tiết về $roomName"

        btnBook.setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("roomName", roomName)
            startActivity(intent)
        }
    }
}
