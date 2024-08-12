package com.example.fifteen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
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
            Surface(
                color = Color(0xFF673AB7)
            ) {
                FifteenGridStateHolder()
            }
        }
    }
}

@Composable
fun FifteenGridStateHolder(engine: FifteenEngine = FifteenEngine) {
    var state by rememberSaveable { mutableStateOf(engine.getInitialState()) }

    fun onReset() {
        state = engine.getInitialState()
    }
    fun onCellClick(cellValue: Byte) {
        state = engine.transitionState(state, cellValue)
    }

    FifteenGrid(
        state,
        engine.isWin(state),
        FifteenEngine::formatCell,
        onReset = ::onReset,
        onClick = ::onCellClick
    )
}


@Composable
fun FifteenGrid(
    state: ByteArray,
    isVictory: Boolean = false,
    formatText: (Byte) -> String = { it.toString() },
    onReset: () -> Unit = {},
    onClick: (Byte) -> Unit = {}
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol

    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (iRow in 0..<DIM) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    for (iCol in 0..<DIM) {
                        val id = ix(iRow, iCol)
                        Cell(
                            formatText(state[id]),
                            onClick = { onClick(state[id]) }
                        )
                    }
                }
            }
        }
        if (isVictory) {
            VictoryBanner(
                Modifier.align(Alignment.Center),
                onReset
            )
        }
    }
}

@Composable
fun VictoryBanner(
    modifier: Modifier = Modifier,
    onReset: () -> Unit = {}
) {
    Text(
        "VICTORY!",
        modifier = Modifier
            .clickable(onClick = onReset)
            .border(width = 2.dp, color = Color.Blue)
            .background(
                brush = Brush.verticalGradient(
                    0f to Color.Blue.copy(alpha = 0.95f),
                    0.5f to Color.Blue.copy(alpha = 0.2f),
                    0.5f to Color.Yellow.copy(alpha = 0.2f),
                    1f to Color.Yellow.copy(alpha = 0.95f)
                )
            )
            .padding(
                vertical = 150.dp,
                horizontal = 50.dp
            )
            .then(modifier),
        style = TextStyle(
            color = Color.Blue,
            fontSize = 60.sp,
            fontWeight = FontWeight.W900
        )
    )
}

@Composable
fun Cell(number: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    val isVisible = number.isNotBlank()
    ElevatedButton(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(90.dp)
            .alpha(if (isVisible) 1f else 0f)
            .padding(2.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.small
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


//@Preview(showBackground = true)
//@Composable
//fun CellPreview() {
//    Cell("11")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun CellPreview2() {
//    Cell("1")
//}
//@Preview(showBackground = true)
//@Composable
//fun CellPreview3() {
//    Cell(" ")
//}
//
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GridPreview() {
    FifteenGrid(
        byteArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
        true, FifteenEngine::formatCell
    )
}

@Preview(
    showSystemUi = true, showBackground = true,
    backgroundColor = 0xFF673AB7
)
@Composable
fun GridPreview2() {
    FifteenGridStateHolder(
        engine = object : FifteenEngine by FifteenEngine.Companion {
            override fun getInitialState() = byteArrayOf(
                1, 2, 3, 4,
                5, 6, 7, 8,
                9, 10, 11, 12,
                13, 14, 16, 15
            )
        }
    )
}