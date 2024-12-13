package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation1)

        // Đặt item được chọn là home
        bottomNavigationView.selectedItemId = R.id.nav_home

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
        val OtherSV: TextView = findViewById(R.id.otherTV)
        val RoomlistTV: TextView = findViewById(R.id.roomlistTV)

        // Thiết lập sự kiện click cho TextView
        OtherSV.setOnClickListener {
            // Tạo một Intent để chuyển sang SecondActivity
            val intent = Intent(this@HomeActivity, OtherServicesActivity::class.java)
            startActivity(intent)
        }
        // Thiết lập sự kiện click cho TextView
        RoomlistTV.setOnClickListener {
            // Tạo một Intent để chuyển sang SecondActivity
            val intent = Intent(this@HomeActivity, RoomListActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {

        super.onResume()
        // Đặt lại trạng thái chọn cho item tương ứng (ví dụ: Home)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation1)
        bottomNavigationView.menu.findItem(R.id.nav_home).isChecked = true
    }

}