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

private class RectanglesPerimeter {
    fun solveTestCase() {
        //TODO: Solve the question

        val n = readInt()
        val rectangles = mutableListOf<Pair<Int, Int>>()

        for (i in 0 until n) {
            val (a, b) = readIntList()
            rectangles.add(Pair(a, b))
        }

        val dp = Array(n) { Array(1000) { -1 } }

        fun maxLenght(i: Int, prevY: Int): Int {

            if (i >= n)
                return 0

            if (dp[i][prevY] != -1)
                return dp[i][prevY]

            val (a, b) = listOf(rectangles[i].first, rectangles[i].second)

            // when side 'a' is on the x-axis
            val ans1 = (if (i == 0) 0 else abs(prevY - b)) + a + maxLenght(i + 1, b)

            // when side 'b' is on the x-axis
            val ans2 = (if (i == 0) 0 else abs(prevY - a)) + b + maxLenght(i + 1, a)

            dp[i][prevY] = maxOf(ans1, ans2)
            return dp[i][prevY]
        }

        val result = maxLenght(0, 0)
        out.println(result)
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        RectanglesPerimeter()
            .solveTestCase()
    }

    out.flush()
}