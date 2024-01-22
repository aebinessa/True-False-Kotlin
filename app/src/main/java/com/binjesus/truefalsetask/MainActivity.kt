package com.binjesus.truefalsetask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.binjesus.truefalsetask.ui.theme.TrueFalseTaskTheme

class MainActivity : ComponentActivity() {
    val questions = listOf("android is an operating sysytem","kotlin is a programming language for IOS",
        "Kotlin is supported for android development")
    val answers = listOf(true,false,true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrueFalseTaskTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    gameScreen()
                }
            }
        }
    }
}

@Composable
fun gameScreen() {
    val questions = listOf("android is an operating sysytem","kotlin is a programming language for IOS",
        "Kotlin is supported for android development")
    val answers = listOf(true,false,true)
    //state variable to keep track of the current question index and update it when the "Next Question" button is pressed.
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    //state variable to hold the feedback message (e.g., "Correct!" or "Wrong!").
    var feedbackMessage by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(questions[currentQuestionIndex], modifier = Modifier, fontSize = 30.sp, fontWeight = FontWeight.Medium)
box(feedbackMessage)

        Button(modifier = Modifier.width(300.dp),onClick = { currentQuestionIndex = (currentQuestionIndex + 1) % questions.size// to repeat
            feedbackMessage = "" }) {
            Text("next question")
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Button(modifier = Modifier.width(180.dp),onClick = {
                if (answers[currentQuestionIndex]) {
                    feedbackMessage = "Correct"
                } else {
                    feedbackMessage = "Wrong"
                }
            }) {
                Text("True")
            }
            Spacer(modifier = Modifier.width(40.dp))
            Button(modifier = Modifier.width(180.dp), onClick = {
                if (!answers[currentQuestionIndex]) {
                    feedbackMessage = "Correct"
                } else {
                    feedbackMessage = "Wrong"
                }
            }) {
                Text("False")
            }
        }
    }
}

@Composable
fun box(feedbackMessage: String) {

    if (feedbackMessage.isNotEmpty()) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(if (feedbackMessage == "Correct") Color.Green else Color.Red)
        ) {
            Text(
                feedbackMessage, modifier = Modifier.align(
                    Alignment.Center
                ), fontSize = 30.sp, fontWeight = FontWeight.Medium
            )
        }
    }
}