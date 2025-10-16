package com.example.mau


import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color


@Composable
fun BTTuan2() {
    var Name by remember { mutableStateOf("") }
    var Year_Old by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) } // Theo dõi ẩn hiện thông báo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally, // CĂN GIỮA NGANG
//        verticalArrangement = Arrangement.Center            // CĂN GIỮA DỌC
    ) {
        Text("Thực hành 01", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(20.dp))

        // Ô nhập liệu
        Text("Họ và Tên")
        OutlinedTextField(
            value = Name,
            onValueChange = { newValue ->
                Name = newValue
                isError = false
            },
        )

        Spacer(Modifier.height(16.dp))
        Text("Tuổi")
        OutlinedTextField(
            value = Year_Old,
            onValueChange = { newValue ->
                Year_Old = newValue
                isError = false
            },
        )

        Spacer(Modifier.height(16.dp))
        if (isError) {
            val n = Year_Old.toIntOrNull()

            if (Name.isEmpty() || Year_Old.isEmpty() || n == null || n <= 0) {
                Text("Dữ liệu không hợp lệ", color = Color.Red)
            } else {
                val My_String = "Bạn tên $Name, $Year_Old tuổi. Bạn"

                when {
                    n > 65 -> Text("$My_String là người già", color = Color.Green)
                    n > 65 -> Text("$My_String là người lớn", color = Color.Green)
                    n > 6 -> Text("$My_String là trẻ em", color = Color.Green)
                    else -> Text("$My_String là em bé", color = Color.Green)
                }
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