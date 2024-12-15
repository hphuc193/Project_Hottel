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

class ServiceDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_service_detail)
        // Nhận dữ liệu từ Intent
        val ServiceName = intent.getStringExtra("SERVICE_NAME")
        val ServicePrice = intent.getStringExtra("SERVICE_PRICE")
        val ServiceImage = intent.getIntExtra("SERVICE_IMAGE", 0)
        val ServiceDescription = intent.getStringExtra("SERVICE_DESCRIPTION")
        val ServiceCapacity = intent.getStringExtra("SERVICE_CAPACITY")

        // Hiển thị dữ liệu lên màn hình
        findViewById<TextView>(R.id.ServiceTextView).text = ServiceName
        findViewById<TextView>(R.id.ServicePriceTextView).text = ServicePrice
        findViewById<ImageView>(R.id.ServiceImageView).setImageResource(ServiceImage)
        findViewById<TextView>(R.id.ServiceDescriptionTextView).text = ServiceDescription
        findViewById<TextView>(R.id.ServiceCapacityTextView).text = ServiceCapacity

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
        val backButton = findViewById<Button>(R.id.backbtn19)

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