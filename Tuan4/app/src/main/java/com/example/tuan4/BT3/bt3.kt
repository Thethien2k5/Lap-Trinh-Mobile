package com.example.tuan4.BT3

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.example.tuan4.R
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign

@Composable
@Preview(
    name = "Màn hình chính",
    showBackground = true,
    showSystemUi = false,
    widthDp = 365,
    heightDp = 815
)
fun PreviewDSS() {
    bt3() // Gọi hàm Composable
}


@Composable
fun bt3() {
    val customText = CustomText()
    val customImage = CustomImage()
    val customButton = CustomButton()

    var target by remember { mutableStateOf(0) }

    if (target == 0) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            customImage.Basic(
                painter = painterResource(R.drawable.reze_movie),
                contentDescription = "Reze Movie",
                size = 400.dp
            )

            customText.title(
                text = "Chainsaw Man \n The Movie: Reze Arc",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            customText.Subtitle(
                text = "Chainsaw Man – The Movie: Reze Arc , " +
                        "also known simply as Chainsaw Man: Reze Arc, is an upcoming " +
                        "Japanese animated dark fantasy action film based on Tatsuki " +
                        "Fujimoto's manga series Chainsaw Man",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            customButton.Large(
                text = "Next",
                onClick = { target = 1 },
                backgroundColor = Color(0xFFFF5757),
                textColor = Color(0xFF5757)
            )
        }
    } else if (target == 1) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            customImage.Basic(
                painter = painterResource(R.drawable.rac),
                contentDescription = "Gachiakuta",
                size = 500.dp
            )

            customText.title(
                text = "Gachiakuta",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            customText.Subtitle(
                text = "Gachiakuta is a Japanese manga series written and illustrated by Kei Urana. " +
                        "It began serialization in Kodansha's shōnen manga magazine Weekly Shōnen " +
                        "Magazine in February 2022. As of June 2025, the series' individual chapters" +
                        " have been collected in 15 tankōbon volumes. An anime television series" +
                        " adaptation produced by Bones Film premiered in July 2025",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            customButton.Large(
                text = "next",
                onClick = { target = 2 },
                backgroundColor = Color(0xFFffc0ad),
                textColor = Color(0xFF5757)
            )
        }
    }
    if (target == 2) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            customImage.Basic(
                painter = painterResource(R.drawable.dandadan),
                contentDescription = "Dandadan",
                size = 400.dp
            )

            customText.title(
                text = "Dandadan",
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )
            customText.Subtitle(
                text = "Ghosts, monsters, aliens, teen romance, battles...and the kitchen sink! " +
                        "This series has it all! Takakura, an occult maniac who doesn't believe in " +
                        "ghosts, and Ayase, a girl who doesn't believe in aliens, try to overcome " +
                        "their differences when they encounter the paranormal! This manga is out " +
                        "of this world!",
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )

            customButton.Large(
                text = "Next",
                onClick = { target = 0 },
                backgroundColor = Color(0xFFFF8E3C),
                textColor = Color(0xFF5757)
            )
        }
    }
}