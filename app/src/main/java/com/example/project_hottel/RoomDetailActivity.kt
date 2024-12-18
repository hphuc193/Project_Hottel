package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class RoomDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_detail)

        // Nhận dữ liệu từ Intent
        val roomName = intent.getStringExtra("ROOM_NAME")
        val roomPrice = intent.getStringExtra("ROOM_PRICE")
        val roomImage = intent.getIntExtra("ROOM_IMAGE", 0)
        val roomDescription = intent.getStringExtra("ROOM_DESCRIPTION")
        val roomCapacity = intent.getStringExtra("ROOM_CAPACITY")

        // Hiển thị dữ liệu lên màn hình
        findViewById<TextView>(R.id.roomTypeTextView).text = roomName
        findViewById<TextView>(R.id.roomPriceTextView).text = roomPrice
        findViewById<ImageView>(R.id.roomImageView).setImageResource(roomImage)
        findViewById<TextView>(R.id.roomDescriptionTextView).text = roomDescription
        findViewById<TextView>(R.id.roomCapacityTextView).text = roomCapacity

        // Tham chiếu đến BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Lắng nghe sự kiện chọn item
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_chat -> {
                    startActivity(Intent(this, ChatActivity::class.java))
                    true
                }
                R.id.nav_other -> {
                    startActivity(Intent(this, OtherServicesActivity::class.java))
                    true
                }
                R.id.nav_home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this, HistoryBookingActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, UserProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
        val backButton = findViewById<Button>(R.id.backbtn9)

        backButton.setOnClickListener {
            finish()
        }

        val BookingAC = findViewById<Button>(R.id.bookingButton)

        BookingAC.setOnClickListener {
            val intent = Intent(this, BookingDetailActivity::class.java)
            startActivity(intent)
        }
    }
}