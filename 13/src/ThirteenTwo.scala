import scala.io.Source
import scala.util.Using

object ThirteenTwo {

  def main(args: Array[String]): Unit = {
    val linesFromFile = Using(Source.fromFile("input")) { source => source.getLines().toList }.get
    val input = linesFromFile(1).split(",")

    val arrays = input.zipWithIndex.filterNot(_._1 == "x")
      .map { case (value, index) =>
        val valueAsLong = value.toLong
        if (index == 0) {
          (0L, valueAsLong)
        } else {
          (valueAsLong - index, valueAsLong)
        }
      }

    val listOfRemainders: Array[Long] = arrays.map(_._1)
    val listOfDivisors: Array[Long] = arrays.map(_._2)

    println(chineseRemainder(listOfRemainders, listOfDivisors))
  }

  def chineseRemainder(a: Array[Long], n: Array[Long]) = {
    val product = n.toList.product
    var p = 0L
    var sm = 0L

    for (i <- n.indices) {
      p = product / n(i)
      sm = sm + a(i) * mulInv(p, n(i)) * p
    }
    sm % product
  }

  private def mulInv(inputA: Long, inputB: Long): Long = {
    var a = inputA
    var b = inputB
    val b0 = b
    var x0 = 0L
    var x1 = 1L

    if (b == 1)
      return 1L

    while (a > 1) {
      val q = a / b
      val amb = a % b
      a = b
      b = amb
      val xqx = x1 - q * x0
      x1 = x0
      x0 = xqx
    }
    if (x1 < 0) x1 += b0
    x1
  }

}
