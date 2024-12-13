package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.project_hottel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//        val usernameEditText = findViewById<EditText>(R.id.editTextText)
//        val passwordEditText = findViewById<EditText>(R.id.passwordET)
//        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordET) // Sửa ID cho đúng
//        val togglePasswordVisibility = findViewById<ImageView>(R.id.togglePasswordVisibility)
//        val toggleConfirmPasswordVisibility = findViewById<ImageView>(R.id.iconPasswordVisibility)
//
//        fun setupPasswordToggle(editText: EditText, toggleView: ImageView) {
//            var isPasswordVisible = false
//
//            toggleView.setOnClickListener {
//                isPasswordVisible = !isPasswordVisible
//                if (isPasswordVisible) {
//                    editText.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
//                    toggleView.setImageResource(R.drawable.icon_visibility)
//                } else {
//                    editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
//                    toggleView.setImageResource(R.drawable.ic_visibility_off)
//                }
//                editText.setSelection(editText.text.length)
//            }
//        }
//
//        setupPasswordToggle(passwordEditText, togglePasswordVisibility)
//        setupPasswordToggle(confirmPasswordEditText, toggleConfirmPasswordVisibility)

        val loginButton = findViewById<Button>(R.id.loginBtn)
        val RegisterButton = findViewById<Button>(R.id.RegisterBtnMain)
        val googleButton = findViewById<Button>(R.id.ggBtn)
        val facebookButton = findViewById<Button>(R.id.fbBtn)

        loginButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }




        // Xử lý sự kiện khi nhấn nút Login

//        var binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//        binding.loginBtn.setOnClickListener {
//            val username = binding.editTextText.text.toString()
//            val password = binding.passwordET.text.toString()
//
//            if (username == "UMT" && password == "UMT") {
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
//            }
//        }

        googleButton.setOnClickListener {
            Toast.makeText(this, "Google login is under development", Toast.LENGTH_SHORT).show()
        }


        facebookButton.setOnClickListener {
            Toast.makeText(this, "Facebook login is under development", Toast.LENGTH_SHORT).show()
        }

        RegisterButton.setOnClickListener {
            // Chuyển sang RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}