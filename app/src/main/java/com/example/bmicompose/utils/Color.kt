package com.example.bmicompose.utils

import androidx.compose.ui.graphics.Color

fun getColor(bmi: Float): Color {
    return if (bmi < 18.5 || bmi > 30) Color.Red else if (bmi > 25 && bmi < 29.99) Color.Yellow else Color.White
}