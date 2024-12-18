package com.example.project_hottel


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class OtherServicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_other_services)

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

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerView.adapter = servicesAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 là số cột

        // Tham chiếu đến BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        // Đặt item được chọn là Services
        bottomNavigationView.selectedItemId = R.id.nav_other
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
        val backButton = findViewById<Button>(R.id.backbtn13)

        backButton.setOnClickListener {
            finish()
        }
    }

    data class OtherServices(
        val name: String,
        val price: String,
        val image: Int,
        val description: String,
        val capacity: String
    )

    // Adapter
    class ServicesAdapter(
        private val OtherList: List<OtherServices>,
        private val onItemClick: (OtherServices) -> Unit
    ) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

        class ServiceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val ServiceImage: ImageView = view.findViewById(R.id.ServiceImage)
            val ServiceName: TextView = view.findViewById(R.id.ServiceName)
            val ServicePrice: TextView = view.findViewById(R.id.ServicePrice)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.otherservices_item, parent, false)
            return ServiceViewHolder(view)
        }

        override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
            val OtherServices = OtherList[position]
            holder.ServiceName.text = OtherServices.name
            holder.ServicePrice.text = OtherServices.price
            holder.ServiceImage.setImageResource(OtherServices.image)

            holder.itemView.setOnClickListener {
                onItemClick(OtherServices)
            }
        }

        override fun getItemCount(): Int {
            return OtherList.size
        }
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.menu.findItem(R.id.nav_other).isChecked = true
    }
}