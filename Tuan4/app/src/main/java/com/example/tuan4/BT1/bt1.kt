package com.example.tuan4.BT1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tuan4.BT1.data.SaveManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.tuan4.BT2.bt2

@Composable
@Preview(
    name = "Màn hình chính",
    showBackground = true,
    showSystemUi = false,
    widthDp = 365,
    heightDp = 815
)
fun PreviewDSS() {
    bt1()
}
@Composable
fun bt1() {
    val saveManager = SaveManager(LocalContext.current)
    saveManager.ensureDataFileExists(LocalContext.current)// Tạo file data nếu ch có

    var currentTab by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFb8c1ec)
            ) {
                NavigationBarItem(
                    selected = currentTab == 0,
                    onClick = { currentTab = 0 },
                    label = { Text("Quản lý") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Quản lý"
                        )
                    }
                )
                NavigationBarItem(
                    selected = currentTab == 1,
                    onClick = { currentTab = 1 },
                    label = { Text("Sinh viên") },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Sinh viên"
                        )
                    }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentTab) {
                0 -> QuanLyScreen(saveManager)
                1 -> SinhVienScreen(saveManager)
            }
        }
    }
}

// ------------------------- MÀN HÌNH QUẢN LÝ -------------------------
@Composable
fun QuanLyScreen(saveManager: SaveManager) {
    var inputName by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    var borrowedBooks by remember { mutableStateOf<List<String>>(emptyList()) }
    var selectedStudent by remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var newBookName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Phần Sinh viên
        Text(
            text = "Sinh viên",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Hàng nhập tên sinh viên
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, RoundedCornerShape(8.dp))
            ) {
                BasicTextField(
                    value = inputName,
                    onValueChange = { inputName = it },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                        .fillMaxWidth(),

                    )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Nút Thay đổi
            Button(
                onClick = {
                    val student = saveManager.findStudent(inputName)
                    if (student == null) {
                        message = "Không có tên trong danh sách"
                        borrowedBooks = emptyList()
                        selectedStudent = null
                    } else {
                        selectedStudent = student.name
                        if (student.borrowedBooks.isEmpty()) {
                            message = "Bạn chưa mượn quyển sách nào"
                            borrowedBooks = emptyList()
                        } else {
                            message = ""
                            borrowedBooks = student.borrowedBooks
                        }
                    }
                },
                modifier = Modifier.height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Thay đổi")
            }
        }

        // Hiển thị thông báo
        if (message.isNotEmpty()) {
            Text(
                text = message,
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Danh sách sách
        Text(
            text = "Danh sách sách",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Container cho danh sách sách
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE8E8E8), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            if (borrowedBooks.isEmpty() && selectedStudent != null) {
                Text(
                    text = "Chưa có sách nào được mượn",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.TopStart)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(borrowedBooks) { book ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = true,
                                onCheckedChange = null,
                                modifier = Modifier.size(24.dp),
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFFE91E63)
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = book,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Nút Thêm ở giữa
        Button(
            onClick = {
                if (selectedStudent != null) {
                    showDialog = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = selectedStudent != null
        ) {
            Text("Thêm", fontSize = 16.sp)
        }
    }

    // Dialog để thêm sách
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Thêm sách mới") },
            text = {
                Column {
                    Text("Nhập tên sách muốn đăng ký:")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = newBookName,
                        onValueChange = { newBookName = it },
                        label = { Text("Tên sách") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newBookName.isNotBlank() && selectedStudent != null) {
                            val students = saveManager.readData()
                            val student = students.find { it.name == selectedStudent }
                            student?.let {
                                if (!it.borrowedBooks.contains(newBookName)) {
                                    it.borrowedBooks.add(newBookName)
                                    saveManager.saveData(students)
                                    borrowedBooks = it.borrowedBooks
                                    message = ""
                                }
                            }
                            newBookName = ""
                            showDialog = false
                        }
                    }
                ) {
                    Text("Thêm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    newBookName = ""
                }) {
                    Text("Hủy")
                }
            }
        )
    }
}

// ------------------------- MÀN HÌNH SINH VIÊN -------------------------
@Composable
fun SinhVienScreen(saveManager: SaveManager) {
    var studentName by remember { mutableStateOf("") }
    var students by remember { mutableStateOf(saveManager.readData()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Tiêu đề
        Text(
            text = "Hệ thống\nQuản lý Thư viện",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 24.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Phần Sinh viên
        Text(
            text = "Sinh viên",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Hàng nhập tên sinh viên
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            // TextField với border
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, RoundedCornerShape(8.dp))
            ) {
                BasicTextField(
                    value = studentName,
                    onValueChange = { studentName = it },
                    textStyle = TextStyle(fontSize = 16.sp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 14.dp)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Nút Thêm
            Button(
                onClick = {
                    if (studentName.isNotBlank()) {
                        saveManager.addStudent(studentName)
                        students = saveManager.readData()
                        studentName = ""
                    }
                },
                modifier = Modifier.height(50.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Thêm")
            }
        }

        // Danh sách sinh viên
        Text(
            text = "Danh sách sinh viên:",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Hiển thị danh sách sinh viên
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(students) { student ->
                Text(
                    text = "- ${student.name}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}