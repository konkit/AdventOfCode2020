import java.io.File

fun main(args: Array<String>) {
  val seatIds = File("input").readLines()
    .map { line -> getRowAndColumn(line) }
    .map { result -> result.startRow * 8 + result.startColumn }

  val max = seatIds.maxOrNull()!!
  val min = seatIds.minOrNull()!!

  var counter = max
  val seatsDescending = seatIds
    .sortedDescending()
    .forEach { seatId ->
      if (seatId == counter) {
        counter -= 1
      } else {
        println(counter)
      }
    }

}

fun getRowAndColumn(pass: String): Result {
  var startRow = 0;
  var endRow = 127;
  var startColumn = 0;
  var endColumn = 7;

  pass.forEach { c ->
    val halfRow = (endRow + 1 - startRow) / 2
    val halfColumn = (endColumn +1 - startColumn) / 2

    if (c == 'F') {
      endRow = startRow + halfRow -1;
    } else if (c == 'B') {
      startRow = endRow - halfRow + 1;
    } else if (c == 'L') {
      endColumn = startColumn + halfColumn - 1;
    } else if (c == 'R') {
      startColumn = endColumn - halfColumn + 1;
    }
  }

  return Result(startRow, endRow, startColumn, endColumn)
}

data class Result(val startRow: Int, val endRow: Int, val startColumn: Int, val endColumn: Int)
