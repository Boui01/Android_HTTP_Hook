package com.example.exam_lastterm_v2



import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.exam_lastterm_v2.ui.theme.Exam_Lastterm_V2Theme
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.exam_lastterm_v2.ui.theme.Exam_Lastterm_V2Theme
import com.example.exam_lastterm_v2.User
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.internal.format
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Streaming
import java.net.HttpURLConnection
import java.net.URL
import java.util.stream.Stream
import UserViewModel
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Exam_Lastterm_V2Theme  {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // create objects: user, users
                   /* val user1 = User(1, "Tee", "L.","Tee@gmail.com" , "1234")
                    val user2 = User(2, "Mon", "T.","Mon@gmail.com" , "1234")
                    val user3 = User(3, "Tree", "K.","Tree@gmail.com", "1234")
                    val user4 = User(4, "Sunti", "S.","Sunti@gmail.com", "1234")
                    val users = listOf(user1, user2, user3, user4)
                    // Serializing objects
                    val usersJsonStr = Json.encodeToString(users)
                    val objs = Json.decodeFromString<List<User>>(usersJsonStr)
                    UserList(objs)*/





                    // ------- เรียก function loop จาก Service --------
                    UserListScreen()
                }
            }
        }

    }

}

// ---------------- function แสดงข้อมูล และ เช็คข้อมูล จาก Hook ------------------
@Composable
fun UserListScreen(viewModel: UserViewModel = viewModel()) {
    // ----- เรียก function service จาก viewModel --------
    when (viewModel.userUiState) {
        is UserUiState.Loading -> {
            CircularProgressIndicator()
        }
        is UserUiState.Success -> {
            // -------- แสดงข้อมูล -------------
            LazyColumn {
                items(viewModel.users) { user ->
                    Text(text = "${user.id} - ${user.name} - ${user.email}")
                }
            }
        }
        is UserUiState.Error -> {
            Text(text = "Failed to load users", color = Color.Red)
        }
    }
}
/*@Composable
fun UserScreen() {
    val users = remember { mutableStateOf<List<User>>(emptyList()) }

    LaunchedEffect(Unit) {
        try {
            users.value =  UserService.RetrofitClient.instance.getUsers()
        } catch (e: Exception) {
            Log.e("API_ERROR", "Error: ${e.message}")
        }
    }

    UserList(users.value)
}*/




// ------ function แสดงข้อมูล User --------
@Composable
fun UserList(users: List<User>){
    LazyColumn(){
        items(users) {
            Text(it.info())
        }
    }
}


//  ------- function ส่วนดึงข้อมูลจากเน็ต -------
@Composable
fun UserList2(userViewModel: UserViewModel) {
    val users = userViewModel.users

    LazyColumn {
        items(users) {
            Text(it.info())
        }
    }
}


/*
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Exam_Lastterm_V2Theme  {
        Greeting("Android")
    }
}*/