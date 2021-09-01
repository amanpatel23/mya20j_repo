/* 
    Author: Aman Patel
    Date: 01-09-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File

private val checkOnlineJudge = false
//private val checkOnlineJudge = System.getProperty("ONLINE_JUDGE") == null
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

private class PartySchedule {
    fun solveTestCase() {
        //TODO: Solve the question

        while (true) {
            val totalBudget = readInt();
            val n = readInt()
            if (n == 0 && totalBudget == 0)
                break

            val entranceFee = mutableListOf<Int>()
            val enjoy = mutableListOf<Int>()
            for (i in 0 until n) {
                entranceFee.add(readInt())
                enjoy.add(readInt())
            }

            val dpTable = Array(n + 1) { Array(totalBudget + 1) { 0 } }
            for (i in 1..n) {
                for (j in 1..totalBudget) {
                    dpTable[i][j] = if (entranceFee[i - 1] > j) (dpTable[i - 1][j]) else maxOf(
                        dpTable[i - 1][j],
                        (enjoy[i - 1] + dpTable[i - 1][j - entranceFee[i - 1]])
                    )
                }
            }

            val maxFun = dpTable[n][totalBudget]
            var minSpent = 0
            for (i in 0..totalBudget) {
                if (dpTable[n][i] == maxFun) {
                    minSpent = i
                    break
                }
            }

            out.println("$minSpent $maxFun")
        }
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        PartySchedule()
            .solveTestCase()
    }

    out.flush()
}