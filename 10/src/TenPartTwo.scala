import scala.collection.mutable
import scala.io.Source
import scala.util.Using

object TenPartTwo {
  private val resultsMap = mutable.Map[Int, Long]()

  def main(args: Array[String]): Unit = {
    val linesFromFile: List[Long] = Using(Source.fromFile("input")) { s => s.getLines().map(l => l.toLong).toList.sorted }.get
    val lines: List[Long] = List(0L) ++ linesFromFile

    println(getCount(lines, 0))
  }

  def getCount(allLines: List[Long], currentIndex: Int): Long = {
    if (currentIndex == allLines.length - 1) {
      1L
    } else if (resultsMap.contains(currentIndex)) {
      resultsMap(currentIndex)
    } else {
      val head = allLines(currentIndex)

      val ans = (currentIndex + 1 until List(currentIndex + 4, allLines.length).min)
        .filter(j => allLines(j) - head <= 3)
        .map(j => getCount(allLines, j))
        .sum

      resultsMap(currentIndex) = ans

      ans
    }
  }
}


