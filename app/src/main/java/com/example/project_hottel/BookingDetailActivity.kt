package com.example.project_hottel


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookingDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking_detail)

        val backButton = findViewById<Button>(R.id.backbtn15)

        backButton.setOnClickListener {
            finish()
        }
        val payButton = findViewById<Button>(R.id.payButton)

        payButton.setOnClickListener {
            // Chuyển đến màn hình PaymentSuccessActivity
            val intent = Intent(this, PaymentSuccessActivity::class.java)
            startActivity(intent)
        }
    }
}