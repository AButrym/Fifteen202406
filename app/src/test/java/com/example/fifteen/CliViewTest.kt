package com.example.fifteen

import com.example.fifteen.FifteenEngine.Companion.FINAL_STATE
import org.junit.Assert.*

import org.junit.Test

class CliViewTest {


    @Test
    fun printBoardTest() {
        val printed = StringBuilder()
        printBoard(FINAL_STATE, printer = { text -> printed.append(text) })
        val expected = """
            ------------------
            |  1   2   3   4 |
            |  5   6   7   8 |
            |  9  10  11  12 |
            | 13  14  15     |
            ------------------
            
            """.trimIndent()

        assertEquals(expected, printed.toString())
    }

    @Test
    fun printBoardTest2() {
        val printed = StringBuilder()
        val state = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 16, 10, 11,
            12, 13, 14, 15
        )
        printBoard(state, printer = { text -> printed.append(text) })
        val expected = """
            ------------------
            |  1   2   3   4 |
            |  5   6   7   8 |
            |  9      10  11 |
            | 12  13  14  15 |
            ------------------
            
            """.trimIndent()

        assertEquals(expected, printed.toString())
    }
}