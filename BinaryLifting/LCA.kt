/* 
    Author: Aman Patel
    Date: 16-08-2021
*/

import java.io.PrintWriter
import java.util.StringTokenizer
import java.io.File
import kotlin.math.*

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

// global vals
private const val N = 1000
private const val maxPow = 10

private class LCA {

    // initiallizing variables and containers
    var n: Int? = null
    val adjList = mutableListOf<MutableList<Int>>()
    val levels = Array(N + 5) { 0 }
    val sparseTable = Array(N + 5) { Array(maxPow + 1) { -1 } }

    fun dfs(i: Int, parent: Int, level: Int) {

        sparseTable[i][0] = parent
        levels[i] = level

        for (x in adjList[i]) {
            if (x != parent)
                dfs(x, i, level + 1)
        }

        return
    }

    fun init() {

        dfs(1, -1, 0)

        for (j in 1..maxPow) {
            for (i in 1..N) {
                if (sparseTable[i][j - 1] != -1) {
                    val par = sparseTable[i][j - 1]
                    sparseTable[i][j] = sparseTable[par][j - 1]
                }
            }
        }
    }

    fun lca(_a: Int, _b: Int): Int {

        var (a, b) = listOf(_a, _b)
        if (levels[a] > levels[b]) {
            a = b.also { b = a }
        }

        var d = levels[b] - levels[a]
        while (d > 0) {
            val i = log2(d.toDouble()).toInt()
            b = sparseTable[b][i]

            d -= (1 shl i)
        }

        if (a == b) return a

        for (i in maxPow downTo 0) {
            if (sparseTable[a][i] != -1 && sparseTable[a][i] != sparseTable[b][i]) {
                a = sparseTable[a][i]; b = sparseTable[b][i]
            }
        }

        return sparseTable[a][0]
    }

    fun solveTestCase() {
        //TODO: Solve the question

        // creating adjList
        for (i in 0..N) {
            adjList.add(mutableListOf())
        }

        // getting no of nodes
        n = readInt()

        // storing the nodes
        for (a in 1..n!!) {
            val edges = readInt()
            for (j in 0 until edges) {
                val b = readInt()
                adjList[a].add(b)
                adjList[b].add(a)
            }
        }

        // calling init function to create the sparse table
        init()

        repeat(readInt()) {
            val (a, b) = readIntList()
            // getting the lca
            val result = lca(a, b)

            out.println(result)
        }
    }
}

fun main(args: Array<String>) {

    var t = 1
    t = readInt()
    var case = 1
    repeat(t) {
        //TODO: Read in each Test Case

        out.println("Case $case:")
        LCA()
            .solveTestCase()
        case++
    }

    out.flush()
}