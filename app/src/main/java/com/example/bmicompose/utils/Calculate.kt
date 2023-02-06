package com.example.bmicompose.utils

import kotlin.math.pow

fun calculateBmi(weight: Float, height: Float) = weight / ((height / 100).pow(2))
