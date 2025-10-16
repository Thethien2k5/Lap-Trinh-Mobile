package com.example.tuan4.BT1.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

// Dữ liệu lưu trong JSON
data class Student(
    val name: String,
    val borrowedBooks: MutableList<String> = mutableListOf()
)

class SaveManager(private val context: Context) {

    private val GSON = Gson()
    private val FILE_NAME = "data.json"

    // Tạo file json lần đầu đếu ch có
    fun ensureDataFileExists(context: Context) {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) {
            file.createNewFile()
            file.writeText("[]") // ghi mảng trống mặc định
        }
    }

    // Đọc file JSON và trả về danh sách sinh viên
    fun readData(): MutableList<Student> {
        val file = File(context.filesDir, FILE_NAME)
        if (!file.exists()) return mutableListOf()
        val json = file.readText()
        if (json.isEmpty()) return mutableListOf()
        val type = object : TypeToken<MutableList<Student>>() {}.type
        return GSON.fromJson(json, type)
    }

    // Ghi danh sách sinh viên vào file JSON
    fun saveData(students: MutableList<Student>) {
        val file = File(context.filesDir, FILE_NAME)
        val json = GSON.toJson(students)
        file.writeText(json)
    }

    // Thêm sinh viên mới
    fun addStudent(name: String) {
        val list = readData()
        if (list.any { it.name == name }) return
        list.add(Student(name))
        saveData(list)
    }

    // Tìm sinh viên theo tên
    fun findStudent(name: String): Student? {
        return readData().find { it.name.equals(name, ignoreCase = true) }
    }
}
