package tech.konkit.adventofcode

import java.io.File

fun main(args: Array<String>) {
//    Right 1, down 1.
//    Right 3, down 1. (This is the slope you already checked.)
//    Right 5, down 1.
//    Right 7, down 1.
//    Right 1, down 2.

    val result1 = getForSlope(1, 1)
    val result2 = getForSlope(3, 1)
    val result3 = getForSlope(5, 1)
    val result4 = getForSlope(7, 1)
    val result5 = getForSlope(1, 2)

    println(result1)
    println(result2)
    println(result3)
    println(result4)
    println(result5)
    println(result1.toLong() * result2 * result3 * result4 * result5)
}

fun getForSlope(changeColumn: Int, changeRow: Int): Int {
    val array = File("input").readLines()
        .map { line -> line.toCharArray() }
        .toTypedArray()

    val rowSize = array[0].size

    var cntRow = 0
    var cntColumn = 0
    var trees = 0

    while(cntRow + changeRow < array.size) {
        cntColumn += changeColumn
        cntRow += changeRow

        if (array[cntRow][cntColumn % rowSize] == '#') {
            trees += 1
        }
    }

    println("cntRow ${cntRow}")

    return trees
}