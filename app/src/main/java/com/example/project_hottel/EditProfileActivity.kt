package com.example.project_hottel

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    // Khai báo View
    private lateinit var imgAvatar: ImageView
    private lateinit var btnChangeAvatar: Button
    private lateinit var etName: EditText
    private lateinit var etPhoneNumber: EditText
    private lateinit var etEmail: EditText
    private lateinit var tvDofB: TextView
    private lateinit var tvAddress: TextView
    private lateinit var tvGender: TextView
    private lateinit var tvCCCD: TextView
    private lateinit var btnSave: Button
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private val PICK_IMAGE_REQUEST = 1 // Mã yêu cầu để chọn hình ảnh

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Ánh xạ View
        imgAvatar = findViewById(R.id.imgAvatar)
        btnChangeAvatar = findViewById(R.id.btnChangeAvatar)
        etName = findViewById(R.id.etName)
        etPhoneNumber = findViewById(R.id.phonenumberET)
        etEmail = findViewById(R.id.EmailET)
        tvDofB = findViewById(R.id.DofB)
        tvAddress = findViewById(R.id.AdressTV)
        tvGender = findViewById(R.id.Gender)
        tvCCCD = findViewById(R.id.cccdTV)
        btnSave = findViewById(R.id.btnSaveEditprofile)

        // Khởi tạo ActivityResultLauncher
        pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK && result.data != null) {
                val selectedImageUri: Uri? = result.data?.data
                imgAvatar.setImageURI(selectedImageUri)
            }
        }

        // Xử lý sự kiện
        tvDofB.setOnClickListener {
            showDatePickerDialog()
        }

        btnChangeAvatar.setOnClickListener {
            openGallery()
        }

        btnSave.setOnClickListener {
            saveProfile()
        }

        setupBottomNavigation()
        setupBackButton()
    }

    // Hiển thị Popup DatePicker
    private fun showDatePickerDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_date_picker)

        val numberPickerDay = dialog.findViewById<NumberPicker>(R.id.numberPickerDay)
        val numberPickerMonth = dialog.findViewById<NumberPicker>(R.id.numberPickerMonth)
        val numberPickerYear = dialog.findViewById<NumberPicker>(R.id.numberPickerYear)
        val btnConfirmDate = dialog.findViewById<Button>(R.id.btnConfirmDate)

        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH) + 1
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)

        // Cấu hình NumberPickers
        numberPickerDay.minValue = 1
        numberPickerDay.maxValue = 31
        numberPickerDay.value = currentDay

        numberPickerMonth.minValue = 1
        numberPickerMonth.maxValue = 12
        numberPickerMonth.value = currentMonth

        numberPickerYear.minValue = 1900
        numberPickerYear.maxValue = currentYear
        numberPickerYear.value = currentYear

        btnConfirmDate.setOnClickListener {
            val selectedDate = "${numberPickerDay.value}/${numberPickerMonth.value}/${numberPickerYear.value}"
            tvDofB.text = selectedDate
            dialog.dismiss()
        }

        dialog.show()
    }

    // Mở thư viện ảnh để đổi avatar
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        pickImageLauncher.launch(intent)
    }
    @Deprecated("startActivityForResult is deprecated, use ActivityResult API instead", ReplaceWith("pickImageLauncher.launch(intent)"))
    // Nhận hình ảnh từ thư viện
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            imgAvatar.setImageURI(selectedImageUri)
        }
    }

    // Lưu thông tin hồ sơ
    private fun saveProfile() {
        val name = etName.text.toString()
        val phoneNumber = etPhoneNumber.text.toString()
        val email = etEmail.text.toString()

        if (name.isBlank() || phoneNumber.isBlank() || email.isBlank()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Hồ sơ đã được lưu thành công!", Toast.LENGTH_SHORT).show()
        }
    }

    // Thiết lập Bottom Navigation
    private fun setupBottomNavigation() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.nav_profile

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_chat -> startActivity(Intent(this, ChatActivity::class.java))
                R.id.nav_other -> startActivity(Intent(this, OtherServicesActivity::class.java))
                R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.nav_history -> startActivity(Intent(this, HistoryBookingActivity::class.java))
                R.id.nav_profile -> startActivity(Intent(this, UserProfileActivity::class.java))
            }
            true
        }
    }

    // Xử lý nút quay lại
    private fun setupBackButton() {
        val backButton = findViewById<Button>(R.id.backbtn8)
        backButton.setOnClickListener { finish() }
    }
}
