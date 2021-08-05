/* 
    Author: Aman Patel
    Date: 05-08-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File

private val checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null
//private val INPUT = if (checkOnlineJudge) File("src/input.txt").inputStream() else System.`in`
//private val OUTPUT = if (checkOnlineJudge) File("src/output.txt").outputStream() else System.out
private val INPUT = System.`in`
private val OUTPUT = System.out

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

private class TriTiling {

    val n = 30
    val dp = Array(n + 1) { Array(8) { 0 } }
    fun solveTestCase() {
        //TODO: Solve the question

        preProcess()
        while (true) {
            val n = readInt()
            if (n == -1)
                break

            out.println(dp[n][7])
        }
    }

    fun preProcess() {

        dp[0][7] = 1

        for (i in 1..n) {
            dp[i][0] += dp[i - 1][7]

            dp[i][1] += dp[i - 1][6]

            dp[i][3] += dp[i - 1][7]
            dp[i][3] += dp[i - 1][4]

            dp[i][4] += dp[i - 1][3]

            dp[i][6] += dp[i - 1][7]
            dp[i][6] += dp[i - 1][1]

            dp[i][7] += dp[i - 1][0]
            dp[i][7] += dp[i - 1][3]
            dp[i][7] += dp[i - 1][6]
        }
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        TriTiling()
            .solveTestCase()
    }

    out.flush()
}