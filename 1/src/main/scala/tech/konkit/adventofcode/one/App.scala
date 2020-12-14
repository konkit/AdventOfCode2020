package tech.konkit.adventofcode.one

import scala.io.Source

object App {
  def main(args: Array[String]): Unit = {
    println(calculate())
  }

  def calculate(): Long = {
    val lines = Source.fromFile("input").getLines().toArray.map(_.toLong).sorted

    println(lines.mkString(", "))

    var h=0;
    while (h < lines.length) {
      var i = 0;
      while (i < lines.length) {
        val lookingFor = 2020 - lines(i) - lines(h)

        var j = 0;
        while (j < lines.length) {
          if (lines(j) == lookingFor) {
            println(s"${lines(h)} + ${lines(i)} + ${lines(j)} == ${lines(h) + lines(i) + lines(j)}")
            println(s"${lines(h)} * ${lines(i)} * ${lines(j)} == ${lines(h) * lines(i) * lines(j)}")

            return lines(h) * lines(i) * lines(j)
          } else if (lines(j) > lookingFor) {
            j = lines.length
          } else {
            j += 1;
          }
        }

        i += 1;
      }

      h += 1;
    }

    return 0
  }

}
