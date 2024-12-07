import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.project_hottel.R

class HistoryBookingAdapter(
    private val context: Context,
    private val bookings: List<Booking>
) : ArrayAdapter<Booking>(context, 0, bookings) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_history_booking, parent, false)

        // Lấy thông tin đặt phòng hiện tại
        val booking = bookings[position]

        // Gắn thông tin vào layout
        val typeRoom = view.findViewById<TextView>(R.id.tvTyperoom)
        val bookingDate = view.findViewById<TextView>(R.id.tvBookingDate)
        val roomDetails = view.findViewById<TextView>(R.id.tvRoomDetails)
        val totalPrice = view.findViewById<TextView>(R.id.tvTotalPrice)
        val cancelButton = view.findViewById<Button>(R.id.btnCancelBooking)

        typeRoom.text = booking.typeRoom
        bookingDate.text = "Ngày đặt: ${booking.bookingDate}"
        roomDetails.text = "Phòng: ${booking.roomDetails}"
        totalPrice.text = "Tổng tiền: ${booking.totalPrice} VND"

        // Xử lý sự kiện hủy
        cancelButton.setOnClickListener {
            Toast.makeText(context, "Đã hủy đặt phòng: ${booking.typeRoom}", Toast.LENGTH_SHORT).show()
            // Xử lý logic hủy đặt phòng tại đây
        }

        return view
    }
}
