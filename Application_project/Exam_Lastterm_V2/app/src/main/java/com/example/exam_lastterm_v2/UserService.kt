import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.exam_lastterm_v2.User

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.http.GET
import java.io.IOException
import retrofit2.converter.gson.GsonConverterFactory

// -------- function รับค่าข้อมูล -----------------------
class UserService {

    // --------------------- เชื่อมและส่งข้อมูล ---------------------
    object RetrofitClient {
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"

        val json = Json { ignoreUnknownKeys = true }

        // ------- ส่งข้อมูลเชื่อม ---------
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(
                Json {
                    ignoreUnknownKeys = true
                }.asConverterFactory("application/json".toMediaType())
            )
            .baseUrl(BASE_URL)
            .build()

        // ------ สร้างค่าไป UserSeviceApi ------
        val userService: UserServiceApi by lazy {
            retrofit.create(UserServiceApi::class.java)
        }
    }

    // ------ รับข้อมูล User -------------
    interface UserServiceApi {
        @GET("users")
        suspend fun getUsers(): List<User> // รับค่าจากเน็ตมาแปลงเป็น List
    }
}

// -------------- แสดงข้อมูล ----------------------

    /*
    interface ApiService {
        @GET("users") // relative endpoint
        suspend fun getUsers(): List<User>
    }

    object RetrofitClient {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        val instance: ApiService by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }*/


sealed interface UserUiState {
    object Success : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}