import scala.io.Source
import scala.util.Using

object Ten {
  def main(args: Array[String]): Unit = {
    val lines = Using(Source.fromFile("testinput")) { source => source.getLines().map(l => l.toInt).toList.sorted }.get

    var incrementsByOne = 0
    var incrementsByTwo = 0
    var incrementsByThree = 0

    var i = 0
    while(i < lines.max) {
      if (lines.contains(i + 1)) {
        i = i+1
        incrementsByOne += 1
      } else if (lines.contains(i + 2)) {
        i = i+2
        incrementsByTwo += 1
      }
      else if (lines.contains(i + 3)) {
        i = i+3
        incrementsByThree += 1
      } else {
        println(s"O kurde, i = ${i}, linesLeft = ${lines.filter(_ >= i).mkString(",")}")
        return
      }
    }

    println(s"incrementsByOne: ${incrementsByOne}")
    println(s"incrementsByTwo: ${incrementsByTwo}")
    println(s"incrementsByThree: ${incrementsByThree + 1}")
    println(s"Result: ${incrementsByOne * (incrementsByThree + 1)}")
  }
}
