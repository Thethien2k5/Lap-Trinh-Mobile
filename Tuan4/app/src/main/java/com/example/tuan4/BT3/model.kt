package com.example.tuan4.BT3

import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.MutableState
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavController

class CustomText {

    @Composable
    fun title(
        text: String,
        modifier: Modifier = Modifier,
        color: Color = Color.Black,
        fontSize: TextUnit = 32.sp,
        fontWeight: FontWeight = FontWeight.Bold,
        textAlign: TextAlign = TextAlign.Start,
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign,
        )
    }

    @Composable
    fun Subtitle(
        text: String,
        modifier: Modifier = Modifier,
        fontSize: TextUnit = 14.sp,
        color: Color = Color.Gray,
        textAlign: TextAlign = TextAlign.Start,
    ) {
        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            textAlign = textAlign,
        )
    }
}

class CustomImage {
    @Composable
    fun Basic(
        painter: Painter,
        contentDescription: String? = null,
        modifier: Modifier = Modifier,
        size: Dp = 100.dp,
        contentScale: ContentScale = ContentScale.Fit
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = modifier.size(size),
            contentScale = contentScale
        )
    }
}

class CustomButton {

    @Composable
    fun Navigation(
        text: String,
        navController: NavController,
        destination: String,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        textColer: Color = Color.Black
    ) {
        Button(
            onClick = { navController.navigate(destination) },
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(backgroundColor, textColer)
        ) {
            CustomText().title(text = text)
        }
    }

    @Composable
    fun Large(
        text: String = "",
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        backgroundColor: Color = MaterialTheme.colorScheme.primary,
        textColor: Color = Color.Black
    ) {
        Button(
            //var target by remember { mutableStateOf(0) }
            onClick = onClick,
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(backgroundColor, textColor)
        ) {
            CustomText().title(text = text)
        }
    }
}

