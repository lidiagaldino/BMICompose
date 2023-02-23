package com.example.bmicompose.utils

import com.example.bmicompose.R

fun getText(bmi: Float): Int {
    return if (bmi < 18.5) R.string.underweight else if (bmi > 18.5 && bmi < 25) R.string.normal else if(bmi > 24.9 && bmi < 30) R.string.overweight else R.string.obesity
}