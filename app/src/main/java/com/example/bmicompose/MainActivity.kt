package com.example.bmicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicompose.ui.theme.BMIComposeTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMIComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BMICalculator()
                }
            }
        }
    }
}

@Composable
fun BMICalculator() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Header()

        Form()

        //Footer()
    }
}

@Composable
fun Header() {
    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.bmi_icon), contentDescription = "App icon")

        Text(
            text = stringResource(id = R.string.app_title),
            modifier = Modifier.padding(top = 10.dp),
            color = MaterialTheme.colors.primary,
            fontSize = 32.sp,
            letterSpacing = 4.sp
        )
    }
}

@Composable
fun Form() {
    
    var weightState by rememberSaveable() {
        mutableStateOf("")
    }

    var heightState by rememberSaveable() {
        mutableStateOf("")
    }

    var bmi by rememberSaveable() {
        mutableStateOf(0f)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(text = stringResource(id = R.string.weight_label), modifier = Modifier.padding(10.dp))

        OutlinedTextField(value = weightState,
            onValueChange = { weightState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp),
            label = { Text("Kg") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Text(text = stringResource(id = R.string.height_label), modifier = Modifier.padding(10.dp))

        OutlinedTextField(value = heightState,
            onValueChange = { heightState = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp),
            label = { Text("Cm") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )

        Button(onClick = { bmi = calculateBmi(weightState.toFloat(), heightState.toFloat()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp, top = 20.dp, bottom = 20.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(Color(116, 179, 110, 255))
        ) {
            Text(text = stringResource(id = R.string.calculate), color = Color.White)
        }

        Footer(bmi)
    }
}

fun calculateBmi(weight: Float, height: Float): Float {
    return weight / (height.pow(2))
}


@Composable
fun TextFieldForm(label: String, medida: String, id: String) {

    var textState by rememberSaveable() {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = label, modifier = Modifier.padding(top = 20.dp))

        OutlinedTextField(value = textState,
            onValueChange = { textState = it },
            modifier = Modifier
                .fillMaxWidth()
                .layoutId(id),
            label = { Text(medida) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            singleLine = true,
            shape = RoundedCornerShape(10.dp)
        )
    }
}

@Composable
fun Footer(bmi: Float) {
    Card(modifier = Modifier.fillMaxSize(), backgroundColor = MaterialTheme.colors.primary, shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)) {
        Column(modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.your_score), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = "$bmi", fontSize = 48.sp, fontWeight = FontWeight.Bold)
            Text(text = "Congratulations", modifier = Modifier.fillMaxWidth(),fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)

            Row() {
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
                    Text(text = "Reset")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
                    Text(text = "Share")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    BMICalculator ()
}
