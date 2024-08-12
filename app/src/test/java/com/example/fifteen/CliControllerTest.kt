package com.example.fifteen

import org.junit.Assert.*

import org.junit.Test

class CliControllerTest {

    @Test
    fun readCellTest() {
        val expected: Byte = 12
        val provided = expected.toString()
        val expectedPrompt = "Enter cell to move (1..15):\n"
        val output = StringBuilder()

        val res = readCell(
            println = { output.appendLine(it) },
            readln = { provided })

        assertEquals(expected, res)
        assertEquals(expectedPrompt, output.toString())
    }

    @Test
    fun readCellWrongInputTest() {
        val expected: Byte = 12
        val provided = listOf("adfdas", "42", expected.toString())
            .iterator()
        val expectedPrompt = """
            Enter cell to move (1..15):
            Enter cell to move (1..15):
            Enter cell to move (1..15):
            
        """.trimIndent()
        val output = StringBuilder()

        val res = readCell(
            println = output::appendLine,
            readln = provided::next)

        assertEquals(expected, res)
        assertEquals(expectedPrompt, output.toString())
    }
}