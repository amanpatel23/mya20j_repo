/* 
    Author: Aman Patel
    Date: 26-09-2021
*/

import java.io.PrintWriter
import java.io.File
import java.util.*
import kotlin.Comparator

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

private class Roads {

    val adjList = mutableListOf<MutableList<Triple<Int, Int, Int>>>()
    var k = 0;
    var n = 0;
    var r = 0

    fun solveTestCase() {
        //TODO: Solve the question

        k = readInt(); n = readInt(); r = readInt()
        for (i in 0 until n)
            adjList.add(mutableListOf())
        for (i in 0 until r) {
            var (u, v, d, c) = readIntList()
            u--; v--
            addEdge(u, v, d, c)
        }

        val result = dijkstras()
        out.println(result)
    }

    fun addEdge(u: Int, v: Int, d: Int, c: Int) {
        adjList[u].add(Triple(v, d, c))
    }

    fun dijkstras(): Int {

        val dist = Array(n) { Array(n) { Pair(iMax, iMax) } }
        dist[0][0] = Pair(0, 0)

        val myComparator = Comparator { p1: Triple<Int, Int, Int>, p2: Triple<Int, Int, Int> -> p1.second - p2.second }
        val pq: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue(myComparator)
        pq.add(Triple(0, 0, 0))

        while (pq.isNotEmpty()) {
            val (u, d, c) = pq.peek()
            pq.remove()

            if (dist[u][n - 1].first < d)
                continue

            for (e in adjList[u]) {
                val (v, d2, c2) = e

                if (dist[v][n - 1].first >= (d + d2) && (c + c2) <= k) {
                    dist[v][n - 1] = Pair(d + d2, c + c2)
                    pq.add(Triple(v, dist[v][n - 1].first, dist[v][n - 1].second))
                    dist[v].sortBy { it.first }
                }
            }
        }

        val result = dist[n - 1].find { it.second <= k }?.let { it.first } ?: -1
        return result
    }
}

fun main(args: Array<String>) {

    var t = 1
    t = readInt()
    repeat(t) {
        //TODO: Read in each Test Case

        Roads()
            .solveTestCase()
    }

    out.flush()
}