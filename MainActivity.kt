package com.example.hotel

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private val roomList = listOf("Phòng Deluxe", "Phòng Suite", "Phòng Standard", "Phòng Family")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listViewRooms)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, roomList)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, RoomDetailActivity::class.java)
            intent.putExtra("roomName", roomList[position])
            startActivity(intent)
        }
    }
}