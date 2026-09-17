package com.example.theappthatdecidesyourfate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.theappthatdecidesyourfate.ui.theme.TheAppThatDecidesYourFateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheAppThatDecidesYourFateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheAppThatDecidesYourFateTheme {
        Greeting("Android")
    }
}

/* --- New code starts here --- */

private fun makeDecision(chance: Float): Boolean {
    return Math.random() < chance
}

private fun changePrompt(choiceApproved: Boolean): String {
    return if (choiceApproved) "GET GOING TWIN!!" else "Nah find smn else bro"
}

@Composable
fun BottomText(name: String, studentID: String, ccid: String, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Credits to : $name",
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "CCID: $ccid",
            modifier = Modifier.padding(2.dp)
        )
        Text(
            text = "ID #: $studentID",
            modifier = Modifier.padding(2.dp)
        )
    }
}

@Composable
fun TheButtonsOfFate(whenPressed: (Float) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Button(
            onClick = { whenPressed(0.5f) },
            modifier = Modifier.width(130.dp)
        ) {
            Text("HELL YEAH")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { whenPressed(0.25f) },
            modifier = Modifier.width(130.dp)
        ) {
            Text("Yeah maybe")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = { whenPressed(0.1f) },
            modifier = Modifier.width(140.dp) // text is slightly wider
        ) {
            Text("Nah ts mid")
        }
    }
}

@Composable
fun ClickCounters(
    leftClicks: Int,
    centerClicks: Int,
    rightClicks: Int,
    modifier: Modifier = Modifier
)   {
    Row(modifier = modifier) {
        Text(
            text = leftClicks.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.width(130.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = centerClicks.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.width(130.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = rightClicks.toString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.width(140.dp)
        )
    }
}

@Composable
fun Prompt(text: String = "You wanna go?", modifier: Modifier) {
    Text(
        text = text,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun MainScreen(modifier: Modifier) {
    var promptText by remember { mutableStateOf("You wanna go?") }
    var leftClicks by remember { mutableIntStateOf(0) }
    var centerClicks by remember { mutableIntStateOf(0) }
    var rightClicks by remember { mutableIntStateOf(0) }
    Column(modifier = modifier.fillMaxSize()) {
        Prompt(
            text = promptText,
            modifier = Modifier.padding(32.dp)
        )
        TheButtonsOfFate(
            whenPressed = { chance ->
                val choiceApproved = makeDecision(chance)
                promptText = changePrompt(choiceApproved)
                when (chance) {
                    0.5f -> leftClicks++
                    0.25f -> centerClicks++
                    0.1f -> rightClicks++
                }
            },
            modifier = Modifier.padding(8.dp)
        )
        ClickCounters(
            leftClicks = leftClicks,
            centerClicks = centerClicks,
            rightClicks = rightClicks,
            modifier = Modifier.padding(8.dp)
        )
        BottomText(
            name = "Nathaniel Edillon",
            studentID = "1826864",
            ccid = "nedillon",
            modifier = Modifier.padding(32.dp)
        )
    }
}