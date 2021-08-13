/* 
    Author: Aman Patel
    Date: 13-08-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File
import kotlin.math.abs

private val checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null
private val INPUT = /* if (checkOnlineJudge) File("src/input.txt").inputStream() else */ System.`in`
private val OUTPUT = /* if (checkOnlineJudge) File("src/output.txt").outputStream() else */ System.out

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

private class BLAST {
    fun solveTestCase() {
        //TODO: Solve the question

        val a = readLn()
        val b = readLn()
        val k = readInt()

        val (len1, len2) = listOf(a.length, b.length)

        val dp = Array(len1) { Array(len2) { -1 } }
        var count = 0

        fun smallestDistance(i: Int, j: Int): Int {
            count++
            if (i >= len1 && j >= len2)
                return 0

            if (i >= len1)
                return (k + smallestDistance(i, j + 1))

            if (j >= len2)
                return (k + smallestDistance(i + 1, j))

            if (dp[i][j] != -1)
                return dp[i][j]

            dp[i][j] = minOf(
                minOf(2 * k, abs((a[i] - 'a') - (b[j] - 'a'))) + smallestDistance(i + 1, j + 1),
                k + smallestDistance(i + 1, j),
                k + smallestDistance(i, j + 1)
            )

            return dp[i][j]
        }

        val result = smallestDistance(0, 0)
        //out.println(count)
        out.println(result)
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        BLAST()
            .solveTestCase()
    }

    out.flush()
}