package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class RoomListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room_list)

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

        val roomAdapter = RoomAdapter(roomList) { room ->
            // Chuyển đến RoomDetailActivity và truyền dữ liệu phòng
            val intent = Intent(this, RoomDetailActivity::class.java).apply {
                putExtra("ROOM_NAME", room.name)
                putExtra("ROOM_PRICE", room.price)
                putExtra("ROOM_IMAGE", room.image)
                putExtra("ROOM_DESCRIPTION", room.description)
                putExtra("ROOM_CAPACITY", room.capacity)
            }
            startActivity(intent)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = roomAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 là số cột


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


        val backButton = findViewById<Button>(R.id.backbtn14)

        backButton.setOnClickListener {
            finish()
        }


//        val LNitemAC: LinearLayout = findViewById(R.id.LNitem1)
//
//        // Thiết lập sự kiện click cho LinearLayout
//        LNitemAC.setOnClickListener {
//            // Tạo một Intent để chuyển sang SecondActivity
//            val intent = Intent(this@RoomListActivity, RoomDetailActivity::class.java)
//            startActivity(intent)
//        }
    }
}

data class Room(
    val name: String,
    val price: String,
    val image: Int,
    val description: String,
    val capacity: String
)


// Adapter
class RoomAdapter(
    private val roomList: List<Room>,
    private val onItemClick: (Room) -> Unit
) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val roomImage: ImageView = view.findViewById(R.id.roomImage)
        val roomName: TextView = view.findViewById(R.id.roomName)
        val roomPrice: TextView = view.findViewById(R.id.roomPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = roomList[position]
        holder.roomName.text = room.name
        holder.roomPrice.text = room.price
        holder.roomImage.setImageResource(room.image)

        holder.itemView.setOnClickListener {
            onItemClick(room)
        }
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}
