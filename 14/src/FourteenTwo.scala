import scala.collection.mutable

object FourteenTwo {
  def main(args: Array[String]) = {
    val memoryMap = mutable.Map[Long, Long]()

    val entries = Entry.loadEntries("input")

    var currentMaskStr: MaskEntry = null

    entries.foreach {
      case maskEntry: MaskEntry =>
        currentMaskStr = maskEntry

      case valueEntry: ValueEntry =>
        val maskedAddress = applyMask(currentMaskStr, valueEntry)
        val numberOfFloatingBits = maskedAddress.count(_ == 'X')

        val addresses = if (numberOfFloatingBits > 0) {
          val floatingBitsCombinations = (0L until scala.math.pow(2L, numberOfFloatingBits).longValue())
            .map(toBinaryWithPadding(_, numberOfFloatingBits))

          floatingBitsCombinations
            .map { xAsBinary => replaceFloatingBits(maskedAddress, xAsBinary)}
            .toList
        } else {
          List(maskedAddress)
        }

        addresses
          .map(addr => java.lang.Long.parseLong(addr, 2))
          .foreach { addr => memoryMap(addr.longValue()) = valueEntry.value }
    }

    println(s"Sum: ${memoryMap.values.sum}")
  }

  private def replaceFloatingBits(maskedAddress: String, xAsBinary: String) = {
    var index = 0
    val resultCharArray = maskedAddress.toCharArray.map { c =>
      if (c == 'X') {
        val result = xAsBinary(index)
        index += 1
        result
      } else {
        c
      }
    }
    new String(resultCharArray)
  }

  private def applyMask(maskEntry: MaskEntry, valueEntry: ValueEntry) = {
    val valueAsBinary = toBinaryWithPadding(valueEntry.index, maskEntry.value.length)
    maskEntry.value.zipWithIndex
      .map {
        case ('X', _) => 'X'
        case ('0', index) => valueAsBinary(index)
        case ('1', _) => '1'
      }
      .mkString("")
  }

  private def toBinaryWithPadding(value: Long, targetLength: Int) = {
    String.format(s"%${targetLength}s", java.lang.Long.toBinaryString(value)).replace(' ', '0')
  }
}
