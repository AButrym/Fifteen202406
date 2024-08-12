package com.example.fifteen

import com.example.fifteen.FifteenEngine.Companion.areAdjacent
import com.example.fifteen.FifteenEngine.Companion.col
import com.example.fifteen.FifteenEngine.Companion.ix
import com.example.fifteen.FifteenEngine.Companion.row
import com.example.fifteen.FifteenEngine.Companion.transitionState
import com.example.fifteen.FifteenEngine.Companion.withSwapped
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MainKtTest {

    @Test
    fun swapTest() {
        val arr = byteArrayOf(1, 2)
        val expected = byteArrayOf(2, 1)

        val res = withSwapped(arr, 0, 1)

        assertArrayEquals(expected, res)
    }

    @Test
    fun adjacentTest() {
        val ix1 = 0
        val ix2 = 1

        assertTrue(areAdjacent(ix1, ix2))
    }

    @Test
    fun transitionTest() {
        val start = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            16, 14, 15, 13
        )
        val actionCell: Byte = 14
        val expected = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 11, 12,
            14, 16, 15, 13
        )

        val res = transitionState(start, actionCell)

        assertArrayEquals(expected, res)
    }

    @Test
    fun transitionTest2() {
        val start = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 16, 12,
            11, 14, 15, 13
        )
        val actionCell: Byte = 7
        val expected = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 16, 8,
            9, 10, 7, 12,
            11, 14, 15, 13
        )

        val res = transitionState(start, actionCell)

        assertArrayEquals(expected, res)
    }
    @Test
    fun transitionTest3() {
        val start = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 12, 16,
            11, 14, 15, 13
        )
        val actionCell: Byte = 11
        val expected = byteArrayOf(
            1, 2, 3, 4,
            5, 6, 7, 8,
            9, 10, 12, 16,
            11, 14, 15, 13
        )

        val res = transitionState(start, actionCell)

        assertArrayEquals(expected, res)
    }

    @Test
    fun ixTest() {
        repeat(16) {
            val ixOrig = it
            val iRow = row(ixOrig)
            val iCol = col(ixOrig)

            val ix = ix(iRow, iCol)

            assertEquals("iRow = $iRow, iCol = $iCol", ixOrig, ix)
        }
    }
}