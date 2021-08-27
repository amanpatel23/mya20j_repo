/* 
    Author: Aman Patel
    Date: 27-08-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File
import kotlin.math.abs

private val checkOnlineJudge = /* System.getProperty("ONLINE_JUDGE") == null */ false
private val INPUT = if (checkOnlineJudge) File("src/input.txt").inputStream() else System.`in`
private val OUTPUT = if (checkOnlineJudge) File("src/output.txt").outputStream() else System.out

private val bufferedReader = INPUT.bufferedReader()
private val out = PrintWriter(OUTPUT, false)
private fun readLn() = bufferedReader.readLine()!!

private fun readList() = readLn().split(' ')
private var tokenizer = StringTokenizer("")
private fun read(): String {
    while (tokenizer.hasMoreTokens().not()) tokenizer = StringTokenizer(readLn(), " ")
    return tokenizer.nextToken()
}

private fun readInt() = read().toInt()
private fun readLong() = read().toLong()
private fun readDouble() = read().toDouble()

private fun readIntList() = readList().map { it.toInt() }
private fun readLongList() = readList().map { it.toLong() }
private fun readDoubleList() = readList().map { it.toDouble() }

private fun readIntArray(n: Int = 0) =
    if (n == 0) readList().run { IntArray(size) { get(it).toInt() } } else IntArray(n) { readInt() }

private fun readLongArray(n: Int = 0) =
    if (n == 0) readList().run { LongArray(size) { get(it).toLong() } } else LongArray(n) { readLong() }

private fun readDoubleArray(n: Int = 0) =
    if (n == 0) readList().run { DoubleArray(size) { get(it).toDouble() } } else DoubleArray(n) { readDouble() }

private class RectanglesPerimeter2 {
    fun solveTestCase() {
        //TODO: Solve the question

        val n = readInt()

        val dpTable = Array(n) { Array(2) { 0 } }

        val (a, b) = readIntList()
        dpTable[0][0] = a; dpTable[0][1] = b
        var (prevA, prevB) = listOf(a, b)
        for (i in 1 until n) {
            val (a, b) = readIntList()
            dpTable[i][0] = a + maxOf((abs(prevB - b) + dpTable[i - 1][0]), (abs(prevA - b) + dpTable[i - 1][1]))
            dpTable[i][1] = b + maxOf((abs(prevB - a) + dpTable[i - 1][0]), (abs(prevA - a) + dpTable[i - 1][1]))

            prevA = a; prevB = b;
        }

        out.println(maxOf(dpTable[n - 1][0], dpTable[n - 1][1]))
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        RectanglesPerimeter2()
            .solveTestCase()
    }

    out.flush()
}