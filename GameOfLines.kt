/* 
    Author: Aman Patel
    Date: 05-10-2021
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

private fun gcd(a: Int, b: Int): Int {
    if (b == 0)
        return a
    return gcd(b, a % b)
}

private class GamesOfLines {

    fun solveTestCase() {
        //TODO: Solve the question

        while (true) {
            val n = readInt()
            if (n == 0)
                break

            val coord = mutableListOf<Pair<Int, Int>>()
            for (i in 0 until n) {
                coord.add(Pair(readInt(), readInt()))
            }

            val slopes = mutableSetOf<Triple<Int, Int, Int>>()
            for (i in 0 until n) {
                for (j in (i + 1) until n) {
                    var p = (coord[j].second - coord[i].second)
                    var q = (coord[j].first - coord[i].first)

                    val divBy = gcd(p, q)
                    p /= divBy; q /= divBy
                    val sign = if ((p < 0 && q < 0) || (p >= 0 && q >= 0)) 1 else 0

                    slopes.add(Triple(p, q, sign))

                }
            }

            val result = slopes.size
            out.println(result)
            //out.println(slopes)
        }
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        GamesOfLines()
            .solveTestCase()
    }

    out.flush()
}