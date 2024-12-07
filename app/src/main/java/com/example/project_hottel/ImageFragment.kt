import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.project_hottel.R

class ImageFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "position"

        // Tạo phương thức khởi tạo fragment với vị trí ảnh
        fun newInstance(position: Int): ImageFragment {
            val fragment = ImageFragment()
            val args = Bundle()
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout cho fragment
        val rootView = inflater.inflate(R.layout.fragment_image1, container, false)

        val imageView = rootView.findViewById<ImageView>(R.id.imageView)

        // Lấy vị trí ảnh từ arguments
        val position = arguments?.getInt(ARG_POSITION) ?: 0

        // Đặt ảnh dựa trên vị trí
        when (position) {
            0 -> imageView.setImageResource(R.drawable.hotel_page1) // Ảnh 1
            1 -> imageView.setImageResource(R.drawable.hotel_page2) // Ảnh 2
            2 -> imageView.setImageResource(R.drawable.hotel_page3) // Ảnh 3
        }

        return rootView
    }
}
