package com.example.tuan2

//import android.R
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

//import androidx.compose.ui.text.style.TextAlign
//import kotlin.collections.isNotEmpty


@Composable
fun TH2_2_Screen() {
    var input by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) } // Theo dõi ẩn hiện thông báo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // CĂN GIỮA NGANG
        verticalArrangement = Arrangement.Center            // CĂN GIỮA DỌC
    ) {
        Text("Thực hành 02", style = MaterialTheme.typography.headlineSmall)

//        Spacer(Modifier.height(16.dp))

        // Ô nhập liệu
        OutlinedTextField(
            value = input,
            onValueChange = { newValue ->
                input = newValue
                isError = false
            },
            label = { Text("Email") },
        )

        Spacer(Modifier.height(16.dp))
        if (isError) {
            if (!input.isNotEmpty()) {
                Text("Email không hợp lệ", color = Color.Red)
            } else if (input.contains('@')) {
                Text("Bạn đã nhập Email hợp lệ", color = Color.Green)
            } else {
                Text("Email không đúng định dạng", color = Color.Red)
            }
        }

        Button(
            onClick = {
                isError = true
            },
            modifier = Modifier
//                .width(1.dp)
                .fillMaxWidth(0.9f)
                .height(60.dp)
                .padding(top = 10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Cyan,
                contentColor = Color.White
            )
        ) {
            Text("Kiểm tra")
        }

    }
}