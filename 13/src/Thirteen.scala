import scala.io.Source
import scala.util.Using

object Thirteen {

  def main(args: Array[String]) = {
    val lines = Using(Source.fromFile("input")) { source => source.getLines().toList }.get
    val targetTimestamp = lines.head.toLong
    val busLines = lines(1).split(",").filter(x => x != "x").map(_.toLong)

    println(s"targetTimestamp: ${targetTimestamp}")
    println(s"busLines: ${busLines.mkString(",")}")

    val lineEntry = busLines
      .map { busLine => busLine -> ((targetTimestamp / busLine + 1) * busLine - targetTimestamp) }
      .minBy(_._2)

    println(s"Line: ${lineEntry._1} at ${lineEntry._2}. Answer: ${lineEntry._1 * lineEntry._2}")
  }

}
