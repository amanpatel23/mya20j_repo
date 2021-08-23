/* 
    Author: Aman Patel
    Date: 23-08-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File
import java.lang.StringBuilder

//private val checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null
private const val checkOnlineJudge = false
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


private class CoinsGame {

    fun solveTestCase() {
        //TODO: Solve the question

        val (k, l, m) = readIntList()
        val coins = Array(m) { 0 }
        var maxCoins = 1
        for (i in 0 until m) {
            coins[i] = readInt()
            maxCoins = maxOf(maxCoins, coins[i])
        }

        val dpTable = Array(maxCoins + 5) { false }
        dpTable[1] = true
        for (i in 2..maxCoins) {
            if (!dpTable[i - 1])
                dpTable[i] = true
            if ((i - k) >= 0 && !dpTable[i - k])
                dpTable[i] = true
            if ((i - l) >= 0 && !dpTable[i - l])
                dpTable[i] = true
        }

        val result = StringBuilder()
        for (i in 0 until m) {
            if (dpTable[coins[i]])
                result.append('A')
            else
                result.append('B')
        }

        out.println(result.toString())
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        CoinsGame()
            .solveTestCase()
    }

    out.flush()
}