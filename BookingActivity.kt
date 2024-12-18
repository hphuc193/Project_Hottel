package com.example.hotel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtDate: EditText
    private lateinit var btnConfirm: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        edtName = findViewById(R.id.edtName)
        edtDate = findViewById(R.id.edtDate)
        btnConfirm = findViewById(R.id.btnConfirm)

        val roomName = intent.getStringExtra("roomName")

        btnConfirm.setOnClickListener {
            val name = edtName.text.toString()
            val date = edtDate.text.toString()
            Toast.makeText(this, "Đặt phòng $roomName thành công cho $name vào ngày $date", Toast.LENGTH_LONG).show()
        }
    }
}
