package com.example.project_hottel
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.project_hottel.HistoryBookingActivity
import com.example.project_hottel.OtherServicesActivity
import com.example.project_hottel.R
import com.example.project_hottel.UserProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PaymentInformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_information)

        // Tham chiếu đến BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.nav_profile

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
        val backButton = findViewById<Button>(R.id.backbtn5)

        backButton.setOnClickListener {
            finish()
        }

        // Lấy các view
        val cardHolderNameEditText = findViewById<EditText>(R.id.cardHolderNameEditText)
        val cardNumberEditText = findViewById<EditText>(R.id.cardNumberEditText)
        val expiryDateEditText = findViewById<EditText>(R.id.expiryDateEditText)
        val bankNameET = findViewById<EditText>(R.id.BankNameET)
        // Ánh xạ các view
        val cvvEditText: EditText = findViewById(R.id.cvvEditText)
//        val paymentMethodSpinner: Spinner = findViewById(R.id.paymentMethodSpinner)
        val saveButton: Button = findViewById(R.id.saveButton)

        val cardHolderNameTextView = findViewById<TextView>(R.id.cardHolderNameTextView)
        val cardNumberTextView = findViewById<TextView>(R.id.cardNumberTextView)
        val expiryDateTextView = findViewById<TextView>(R.id.expiryDateTextView)
        val bankNameTV = findViewById<TextView>(R.id.BankNameTV)

        // Thêm TextWatcher
        bankNameET.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                bankNameTV.text = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        cardHolderNameEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cardHolderNameTextView.text = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        cardNumberEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                cardNumberTextView.text = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        expiryDateEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                expiryDateTextView.text = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        // Xử lý sự kiện nút lưu
        saveButton.setOnClickListener {
//            val paymentMethod = paymentMethodSpinner.selectedItem.toString()
            val cardHolderName = cardHolderNameEditText.text.toString()
            val cardNumber = cardNumberEditText.text.toString()
            val expiryDate = expiryDateEditText.text.toString()
            val cvv = cvvEditText.text.toString()
            val bankname = bankNameET.text.toString()

            if ( cardHolderName.isEmpty() || cardNumber.isEmpty() || expiryDate.isEmpty() || cvv.isEmpty() || bankname.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            } else {
                // Xử lý lưu thông tin tại đây
                Toast.makeText(this, "Thông tin đã được lưu!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
