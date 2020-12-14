import scala.io.Source
import scala.util.Using

trait Entry

case class MaskEntry(value: String) extends Entry

case class ValueEntry(index: Int, value: Long) extends Entry

object Entry {
  def loadEntries(filename: String): List[Entry] = {
    Using(Source.fromFile(filename)) { s => s.getLines().toList }.get map { l =>
      if (l.startsWith("mask")) {
        val regex = "mask = (.+)".r
        l match {
          case regex(value) => MaskEntry(value)
        }
      } else {
        val regex = "mem\\[(\\d+)] = (\\d+)".r

        l match {
          case regex(index, number) => ValueEntry(index.toInt, number.toLong)
        }
      }
    }
  }
}
