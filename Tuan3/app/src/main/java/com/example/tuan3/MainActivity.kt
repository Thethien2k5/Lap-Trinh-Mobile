// File: MainActivity.kt
package com.example.tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.navigation.compose.rememberNavController

import com.example.tuan3.navigation.Address

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo và hiển thị giao diện
        setContent {
            // Theme cho toàn bộ ứng dụng
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    TH1()
                    val navController = rememberNavController()
                    Address(navController = navController)
                }

            }
        }
    }
}

