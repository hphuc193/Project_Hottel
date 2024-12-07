package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment_success)
        val bookingHistoryButton = findViewById<Button>(R.id.bookingHistoryButton)

        bookingHistoryButton.setOnClickListener {
            // Chuyển đến màn hình BookingHistoryActivity
            val intent = Intent(this, HistoryBookingActivity::class.java)
            startActivity(intent)
        }
        val backButton = findViewById<Button>(R.id.backbtn16)

        backButton.setOnClickListener {
            finish()
        }
        val BookingHisAC = findViewById<Button>(R.id.bookingHistoryButton)

        BookingHisAC.setOnClickListener {
            val intent = Intent(this, HistoryBookingActivity::class.java)
            startActivity(intent)
        }
    }
}