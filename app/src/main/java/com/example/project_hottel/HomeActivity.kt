package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_hottel.OtherServicesActivity.OtherServices
import com.example.project_hottel.OtherServicesActivity.ServicesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Thiết lập BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation1)
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
                R.id.nav_home -> true
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

        // Thiết lập TextView sự kiện click
        val otherSV: TextView = findViewById(R.id.otherTV)
        val roomlistTV: TextView = findViewById(R.id.roomlistTV)

        otherSV.setOnClickListener {
            startActivity(Intent(this, OtherServicesActivity::class.java))
        }

        roomlistTV.setOnClickListener {
            startActivity(Intent(this, RoomListActivity::class.java))
        }

        // Tạo danh sách phòng
        val roomList = listOf(
            Room(
                name = "Deluxe Room",
                price = "$100/night",
                image = R.drawable.hotel_image,
                description = "A luxurious room with a beautiful view and all modern amenities.",
                capacity = "2 Adults, 1 Child"
            ),
            Room(
                name = "Standard Room",
                price = "$80/night",
                image = R.drawable.hotel_image2,
                description = "A cozy room with all the basic facilities.",
                capacity = "2 Adults"
            ),
            Room(
                name = "King of Room",
                price = "$800/night",
                image = R.drawable.hotel_page3,
                description = "Room of King",
                capacity = "11 Adults"
            )
        )

        // Thiết lập RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.horizontalRecyclerView)
        val roomAdapter = RoomAdapter(roomList) { room ->
            // Mở RoomDetailActivity khi nhấn vào item
            val intent = Intent(this, RoomDetailActivity::class.java).apply {
                putExtra("ROOM_NAME", room.name)
                putExtra("ROOM_PRICE", room.price)
                putExtra("ROOM_IMAGE", room.image)
                putExtra("ROOM_DESCRIPTION", room.description)
                putExtra("ROOM_CAPACITY", room.capacity)
            }
            startActivity(intent)
        }

        recyclerView.adapter = roomAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val Otherlist = listOf(
            OtherServices(
                name = "Hamburger",
                price = "$10",
                image = R.drawable.hamburger,
                description = "A hamberger is veryy mlemm",
                capacity = "Adults"
            ),
            OtherServices(
                name = "Spaghetti",
                price = "$8",
                image = R.drawable.spaghetti,
                description = "A Spaghetti is supper mlemm",
                capacity = "Adults"
            ),
            OtherServices(
                name = "Pho",
                price = "$2",
                image = R.drawable.pho,
                description = "King of food",
                capacity = "Adults"
            )
        )

        val OtherSercices = findViewById<RecyclerView>(R.id.horizontalRecyclerView2)

        val servicesAdapter = ServicesAdapter(Otherlist) { Services ->
            // Chuyển đến RoomDetailActivity và truyền dữ liệu phòng
            val intent = Intent(this, ServiceDetailActivity::class.java).apply {
                putExtra("SERVICE_NAME", Services.name)
                putExtra("SERVICE_PRICE", Services.price)
                putExtra("SERVICE_IMAGE", Services.image)
                putExtra("SERVICE_DESCRIPTION", Services.description)
                putExtra("SERVICE_CAPACITY", Services.capacity)
            }
            startActivity(intent)
        }

        OtherSercices.adapter = servicesAdapter
        OtherSercices.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val SearchAT = findViewById<Button>(R.id.searchBtnHome)

        SearchAT.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation1)
        bottomNavigationView.menu.findItem(R.id.nav_home).isChecked = true
    }
}
