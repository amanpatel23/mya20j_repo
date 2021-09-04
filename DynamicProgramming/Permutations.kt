/* 
    Author: Aman Patel
    Date: 04-09-2021
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

private class Permutations {

    var count = 0
    fun solveTestCase() {
        //TODO: Solve the question

        val (n, k) = readIntList()

        val maskSize = (1 shl (n + 2))
        val dp = Array(maskSize + 1) { Array(k + 1) { -1L } }
        val result = totalPermutations(0, n, k, 0, dp)
        out.println(result)
        //out.println(count)

    }

    fun totalPermutations(i: Int, n: Int, k: Int, mask: Int, dp: Array<Array<Long>>): Long {
        count++
        if (k < 0)
            return 0

        if (i >= n) {
            return if (k == 0) 1 else 0
        }

        if (dp[mask][k] != -1L)
            return dp[mask][k]

        var ans = 0L
        for (num in 1..n) {
            if ((mask and (1 shl num)) == 0) {
                ans += totalPermutations(i + 1, n, k - greaterNums(num, mask, n), (mask or (1 shl num)), dp)
            }
        }

        dp[mask][k] = ans
        return ans
    }

    fun greaterNums(num: Int, mask: Int, n: Int): Int {
        var count = 0
        for (i in (num + 1)..n) {
            if (mask and (1 shl i) == 0)
                continue

            count++
        }

        return count
    }

}

fun main(args: Array<String>) {

    var t = 1
    t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        Permutations()
            .solveTestCase()
    }

    out.flush()
}