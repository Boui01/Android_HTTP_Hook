import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exam_lastterm_v2.User
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class UserViewModel : ViewModel() {
    // ---- กำหนดค่าตัวแปรไปใช้หน้าหลัก --------
    var users: List<User> by mutableStateOf(emptyList())
        private set

    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    // ----- ให้ใช้งาน function --------
    init {
        getUsers()
    }
    // ----- function รับ และ check ข้อมูลที่ดึึงมา
    fun getUsers() {
        userUiState = UserUiState.Loading
        viewModelScope.launch {
            // ---- ผ่าน ----
            try {
                users = UserService.RetrofitClient.userService.getUsers()
                Log.d("UserViewModel", "Fetched users: $users")
                userUiState = UserUiState.Success // เปลี่ยน UI
            }
            // ---- ไม่ผ่าน ----
            catch (e: IOException) {
                Log.e("UserViewModel", "Network error", e)
                userUiState = UserUiState.Error // เปลี่ยน UI
            } catch (e: HttpException) {
                Log.e("UserViewModel", "HTTP error", e)
                userUiState = UserUiState.Error // เปลี่ยน UI
            } catch (e: Exception) {
                Log.e("UserViewModel", "Unexpected error", e)
                userUiState = UserUiState.Error // เปลี่ยน UI
            }
        }
    }
}

