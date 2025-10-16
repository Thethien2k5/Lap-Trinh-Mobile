package com.example.tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
//import com.example.tuan4.BT3.bt3
import com.example.tuan4.BT1.bt1

//import com.example.tuan4.BT2.bt2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            bt3()
            bt1()
//               bt2()

        }

    }
}

