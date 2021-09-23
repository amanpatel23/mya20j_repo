/* 
    Author: Aman Patel
    Date: 23-09-2021
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
private const val N = (1e6).toInt()
private val divisors = Array(N + 5) { 0 }
private val primeFactors = mutableListOf<MutableList<Int>>()

private class Divisors {

    fun init() {

        for (i in 1..N) {
            for (j in i..N step i) {
                divisors[j]++
            }
        }

        for (i in 1..(N + 5))
            primeFactors.add(mutableListOf())

        for (i in 2..N) {
            if (primeFactors[i].size == 0) {
                for (j in i..N step i)
                    primeFactors[j].add(i)
            }
        }
    }

    fun solveTestCase() {
        //TODO: Solve the question

        init()

        var count = 0
        for (i in 2..N) {
            val x = divisors[i]
            if (primeFactors[x].size == 2 && primeFactors[x][0] * primeFactors[x][1] == x) {
                count++
                if (count == 9) {
                    out.println(i)
                    count = 0
                }
            }
        }
    }
}

fun main(args: Array<String>) {

    var t = 1
    //t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        Divisors()
            .solveTestCase()
    }

    out.flush()
}