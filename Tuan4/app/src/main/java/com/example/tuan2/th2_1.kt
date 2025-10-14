package com.example.tuan2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign


@Composable
fun TH2_1_Screen() {
    var input by remember { mutableStateOf("") }
    var numbers by remember { mutableStateOf(listOf<Int>()) }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Thực hành 02", style = MaterialTheme.typography.headlineSmall)

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(
                value = input,
                onValueChange = {
                    input = it
                    error = false
                },
                label = { Text("Nhập vào số lượng") },
                isError = error,
                modifier = Modifier.weight(2f)
            )

            Button(
                onClick = {
                    val n = input.toIntOrNull()
                    if (n != null && n > 0) {
                        numbers = List(n) { it + 1 }
                        error = false
                    } else {
                        error = true
                        numbers = emptyList()
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp)
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.White
                )
            ) {
                Text("Tạo")
            }
        }

        Spacer(Modifier.height(16.dp))

        if (error) {
            Text(
                "Dữ liệu bạn nhập không hợp lệ",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(Modifier.height(16.dp))

        if (numbers.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.height(400.dp)
            ) {
                items(numbers) { num ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red,
                            contentColor = Color.Black
                        ),

                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Text(
                            text = "Hàng thứ $num",
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center

                        )
                    }
                }
            }
        }
    }
}