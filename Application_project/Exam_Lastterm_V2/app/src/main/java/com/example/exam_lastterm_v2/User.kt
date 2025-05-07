package com.example.exam_lastterm_v2
import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")

@Serializable // คำสั่งประกาศว่า class นี้ สามารถแปลงและทำเป็น JSON
data class User(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
    // add more fields as needed
) {
    fun info(): String {
        return "$id: $name $username ($email) "
    }
}
