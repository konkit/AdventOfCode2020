import scala.annotation.tailrec
import scala.io.Source
import scala.util.Using

object Eleven {

  def main(args: Array[String]): Unit = {
    val lines: Array[String] = Using(Source.fromFile("input")) { s => s.getLines().toArray }.get
    val map: Array[Array[Char]] = lines.map(str => str.toCharArray)

    printMap(map)

    evolveMap(map)
  }


  def printMap(newMap: Array[Array[Char]]) = {
    println("Printing map")
    println("")
    newMap.foreach { line =>
      println(line.mkString(""))
    }
    println("")
    println("")
  }

  @tailrec
  def evolveMap(map: Array[Array[Char]], iteration: Int = 0): Array[Array[Char]] = {
    val newMap = (map.indices).map { i =>
      (map(i).indices).map { j =>
        val occupied = getSurroundingOccupiedCount(map, i, j, map.length - 1, map(0).length - 1)

        val field = map(i)(j)
        if (field == '.') {
          '.'
        } else if (occupied == 0 && field == 'L') {
          '#'
        } else if (field == '#' && occupied >= 4) {
          'L'
        } else {
          field
        }
      }.toArray
    }.toArray

    printMap(newMap)

    if (!sameMaps(map, newMap)) {
      return evolveMap(newMap, iteration + 1)
    } else {
      println(s"Iteration: ${iteration}")
      println(s"Occupied : ${getAllOccupied(newMap)}")
      return newMap
    }
  }

  def getSurroundingOccupiedCount(map: Array[Array[Char]], i: Int, j: Int, iMax: Int, jMax: Int): Int = {
    var occupied = 0

    for (a <- i - 1 to i + 1) {
      for (b <- j - 1 to j + 1) {
        if (a >= 0 && b >= 0 && a <= iMax && b <= jMax) {
          if (!(a == i && b == j)) {
            if (map(a)(b) == '#') {
              occupied += 1
            }
          }
        }
      }
    }

    return occupied
  }

  def sameMaps(map1: Array[Array[Char]], map2: Array[Array[Char]]): Boolean = {
    for (i <- map1.indices) {
      for (j <- map1(0).indices) {
        if (map1(i)(j) != map2(i)(j)) {
          return false
        }
      }
    }

    return true
  }

  def getAllOccupied(map: Array[Array[Char]]): Int = {
    var occupied = 0
    for (line <- map) {
      for (char <- line) {
        if (char == '#') {
          occupied += 1
        }
      }
    }

    return occupied
  }
}
