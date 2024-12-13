package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation3)

        // Đặt item được chọn là Chat
        bottomNavigationView.selectedItemId = R.id.nav_profile

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

        val EditPF = findViewById<Button>(R.id.UserProfileTV)

        EditPF.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        val backbtnUser = findViewById<Button>(R.id.backbtn6)

        backbtnUser.setOnClickListener{
            finish()
        }

        val PaymentTV: TextView = findViewById(R.id.paymentIF)

        // Thiết lập sự kiện click cho TextView
        PaymentTV.setOnClickListener {
            // Tạo một Intent để chuyển sang SecondActivity
            val intent = Intent(this@UserProfileActivity, PaymentInformationActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {

        super.onResume()
        // Đặt lại trạng thái chọn cho item tương ứng (ví dụ: Home)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation3)
        bottomNavigationView.menu.findItem(R.id.nav_profile).isChecked = true
    }

}