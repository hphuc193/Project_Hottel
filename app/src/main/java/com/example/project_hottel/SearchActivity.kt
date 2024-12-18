package com.example.project_hottel

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.query

class SearchActivity : AppCompatActivity() {

    private lateinit var searchAdapter: RoomAdapter // Sử dụng lại adapter của Room
    private lateinit var originalRoomList: List<Room> // Danh sách phòng gốc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val searchAutoComplete: AutoCompleteTextView = findViewById(R.id.atSearch)
        val spinnerFilter = findViewById<Spinner>(R.id.spinnerFilter)
        val recyclerViewSearchResults = findViewById<RecyclerView>(R.id.recyclerViewSearchResults)

        // Dữ liệu mẫu cho gợi ý tìm kiếm (có thể lấy từ cơ sở dữ liệu hoặc danh sách phòng)
        val allRoomNames = listOf("Deluxe Room", "Standard Room", "Family Room", "$100", "$70", "$150", "2 Adults", "4 Adults", "1 Adult")

        // ArrayAdapter cho gợi ý
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, allRoomNames)
        searchAutoComplete.setAdapter(adapter)


        // Lắng nghe sự kiện tìm kiếm
        searchAutoComplete.setOnItemClickListener { parent, view, position, id ->
            val query = parent.getItemAtPosition(position).toString()
            val selectedFilter = spinnerFilter.selectedItem.toString()
            filterRooms(query, selectedFilter)
        }


        // Dữ liệu mẫu cho danh sách phòng
        originalRoomList = listOf(
            Room("Deluxe Room", "$100", R.drawable.hotel_image, "Luxury room", "2 Adults"),
            Room("Standard Room", "$70", R.drawable.hotel_image2, "Affordable room", "1 Adult"),
            Room("Family Room", "$150", R.drawable.hotel_page3, "Spacious for family", "4 Adults"),
        )

        // Thiết lập RecyclerView
        searchAdapter = RoomAdapter(originalRoomList) { room ->
            // Chuyển đến RoomDetailActivity và truyền dữ liệu phòng
            val intent = Intent(this, RoomDetailActivity::class.java).apply {
                putExtra("ROOM_NAME", room.name)
                putExtra("ROOM_PRICE", room.price)
                putExtra("ROOM_IMAGE", room.image)
                putExtra("ROOM_CAPACITY", room.capacity)
                putExtra("ROOM_DESCRIPTION", room.description)
            }
            startActivity(intent)

        }
        recyclerViewSearchResults.adapter = searchAdapter
        recyclerViewSearchResults.layoutManager = GridLayoutManager(this, 2)

        // Thiết lập bộ lọc trong Spinner
        val filterOptions = arrayOf("All", "Name", "Price", "Capacity")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, filterOptions)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFilter.adapter = spinnerAdapter

        // Lắng nghe sự kiện thay đổi văn bản (TextWatcher)
        searchAutoComplete.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString() // Lấy giá trị người dùng nhập vào
                val selectedFilter = spinnerFilter.selectedItem.toString()

                // Lọc danh sách phòng dựa trên ký tự người dùng nhập vào
                val filteredRoomNames = allRoomNames.filter { it.contains(query, ignoreCase = true) }
                val filteredAdapter = ArrayAdapter(this@SearchActivity, android.R.layout.simple_dropdown_item_1line, filteredRoomNames)

                // Cập nhật lại Adapter cho AutoCompleteTextView với các tên phòng đã lọc
                searchAutoComplete.setAdapter(filteredAdapter)

                // Lọc phòng dựa trên bộ lọc đã chọn và từ khóa người dùng nhập
                filterRooms(query, selectedFilter)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        val backButton = findViewById<Button>(R.id.backbtn7)

        backButton.setOnClickListener {
            finish()
        }
    }


    // Hàm lọc danh sách phòng
    private fun filterRooms(query: String, filter: String) {
        val filteredList = when (filter) {
            "Name" -> originalRoomList.filter { it.name.contains(query, ignoreCase = true) }
            "Price" -> originalRoomList.filter { it.price.contains(query, ignoreCase = true) }
            "Capacity" -> originalRoomList.filter { it.capacity.contains(query, ignoreCase = true) }
            else -> originalRoomList // All
        }
        searchAdapter.updateRoomList(filteredList)
    }
}