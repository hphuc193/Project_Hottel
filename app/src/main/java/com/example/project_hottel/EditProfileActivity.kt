package com.example.project_hottel
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.project_hottel.HistoryBookingActivity
import com.example.project_hottel.OtherServicesActivity
import com.example.project_hottel.R
import com.example.project_hottel.UserProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var imgAvatar: ImageView
    private lateinit var btnChangeAvatar: Button
    private lateinit var etName: EditText
    private lateinit var etUsername: EditText
    private lateinit var etDateOfBirth: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Initialize Views
        imgAvatar = findViewById(R.id.imgAvatar)
        btnChangeAvatar = findViewById(R.id.btnChangeAvatar)
        etName = findViewById(R.id.etName)
        etUsername = findViewById(R.id.etUsername)
        etDateOfBirth = findViewById(R.id.etDateOfBirth)
        btnSave = findViewById(R.id.btnSaveEditprofile)

        // Change Avatar Action
        btnChangeAvatar.setOnClickListener {
            Toast.makeText(this, "Change Avatar clicked!", Toast.LENGTH_SHORT).show()
            // TODO: Add functionality to pick an image from gallery
        }

        // Date Picker for Date of Birth
        etDateOfBirth.setOnClickListener {
            showDatePicker()
        }

        // Save Button Action
        btnSave.setOnClickListener {
            saveProfile()
        }
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
        val backButton = findViewById<Button>(R.id.backbtn8)

        backButton.setOnClickListener {
            finish()
        }
    }


    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val formattedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear)
            etDateOfBirth.setText(formattedDate)
        }, year, month, day)
        datePicker.show()
    }

    private fun saveProfile() {
        val name = etName.text.toString()
        val username = etUsername.text.toString()
        val dateOfBirth = etDateOfBirth.text.toString()

        if (name.isBlank() || username.isBlank() || dateOfBirth.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        } else {
            // TODO: Save the profile information to the database or API
            Toast.makeText(this, "Profile Saved!", Toast.LENGTH_SHORT).show()
        }
    }
}
