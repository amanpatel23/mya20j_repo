/* 
    Author: Aman Patel
    Date: 09-09-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File

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

private const val iMax = (1e9).toInt()

private class PiggyBank {
    fun solveTestCase() {
        //TODO: Solve the question

        var (e, f) = readIntList()
        f -= e
        val n = readInt()
        val coins = Array(n) { Pair(readInt(), readInt()) }

        val dp = Array(f + 5) { -1 }
        fun minAmount(currWt: Int): Int {

            if (currWt > f)
                return iMax

            if (currWt == f)
                return 0

            if (dp[currWt] != -1)
                return dp[currWt]

            var ans = iMax
            for (coin in coins) {
                ans = minOf(ans, coin.first + minAmount(currWt + coin.second))
            }

            dp[currWt] = ans
            return ans
        }

        val result = minAmount(0)
        out.println(if (result == iMax) "This is impossible." else "The minimum amount of money in the piggy-bank is $result.")
    }
}

fun main(args: Array<String>) {

    var t = 1
    t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        PiggyBank()
            .solveTestCase()
    }

    out.flush()
}