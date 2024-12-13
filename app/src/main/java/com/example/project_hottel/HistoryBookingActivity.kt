package com.example.project_hottel

import Booking
import HistoryBookingAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HistoryBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_booking)

        // Tham chiếu đến BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Đặt item được chọn là Chat
        bottomNavigationView.selectedItemId = R.id.nav_history

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

        // Dữ liệu mẫu
        val bookings = listOf(
            Booking("Standard Room", "01/12/2024", "2 người", "2,500,000"),
            Booking("Superior Room", "15/11/2024", "4 người", "4,200,000"),
        )

        // Gắn Adapter vào ListView
        val listView = findViewById<ListView>(R.id.ListView1)
        val adapter = HistoryBookingAdapter(this, bookings)
        listView.adapter = adapter
    }

    override fun onResume() {

        super.onResume()
        // Đặt lại trạng thái chọn cho item tương ứng (ví dụ: Home)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.menu.findItem(R.id.nav_history).isChecked = true
    }
}