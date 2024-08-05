package com.example.fifteen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fifteen.FifteenEngine.Companion.DIM
import com.example.fifteen.ui.theme.FifteenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FifteenGridStateHolder()
        }
    }
}

@Composable
fun FifteenGridStateHolder(engine: FifteenEngine = FifteenEngine) {
    var state by rememberSaveable { mutableStateOf(engine.getInitialState()) }
    FifteenGrid(
        state,
        engine.isWin(state),
        FifteenEngine::formatCell) {
        state = engine.transitionState(state, it)
    }
}


@Composable
fun FifteenGrid(
    state: ByteArray,
    isVictory: Boolean = false,
    formatText: (Byte) -> String = { it.toString() },
    onClick: (Byte) -> Unit = {}
) {
    fun ix(iRow: Int, iCol: Int) = iRow * DIM + iCol
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isVictory) {
            Text("YOU WON!", style = TextStyle(
                color = Color.Blue,
                fontSize = 40.sp,
                fontWeight = FontWeight.W900
            ))
        }
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

}

@Composable
fun Cell(number: String, modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    FilledTonalButton(
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .size(90.dp)
            .padding(2.dp)
            .then(modifier),
        shape = MaterialTheme.shapes.small
//        shape = RectangleShape
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
        byteArrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16),
        true, FifteenEngine::formatCell
        )
}

@Preview()
@Composable
fun GridPreview2() {
    FifteenGridStateHolder()
}