package com.example.fifteen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifteen.FifteenEngine.Companion.DIM

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
        }
    }
}

@Composable
fun FifteenGrid(engine: FifteenEngine = FifteenEngineImpl()) {
    val state = engine.getInitialState()

    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (iRow in 0..<DIM) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                for (iCol in 0..<DIM) {
                    Cell(
                        FifteenEngine.formatCell(
                            state[ix(iRow, iCol)]
                        )
                    )
                }
            }
        }
    }

}

@Composable
fun Cell(number: String, modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(90.dp)
            .padding(1.dp)
            .then(modifier),
//        shape = MaterialTheme.shapes.small
        shape = RectangleShape
    ) {
        Text(
            number,
            fontSize = 60.sp,
            fontWeight = FontWeight.Black,
            fontFamily = FontFamily.Monospace,
            color = Color.Red
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CellPreview() {
    Cell("11")
}

@Preview(showBackground = true)
@Composable
fun CellPreview2() {
    Cell("1")
}

@Preview(showSystemUi = true)
@Composable
fun GridPreview() {
    FifteenGrid()
}