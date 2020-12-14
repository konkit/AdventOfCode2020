import scala.collection.mutable

object Fourteen {
  def main(args: Array[String]) = {
    val memoryMap = mutable.Map[Int, String]()

    val entries = Entry.loadEntries("input")

    var currentMaskStr: MaskEntry = null

    entries.foreach {
      case maskEntry: MaskEntry => currentMaskStr = maskEntry
      case valueEntry: ValueEntry =>
        val maskedValue = applyMask(currentMaskStr, valueEntry)
        memoryMap(valueEntry.index) = maskedValue
    }

    println(s"Sum: ${memoryMap.values.map(s => java.lang.Long.parseLong(s, 2)).sum}")
  }

  def applyMask(maskEntry: MaskEntry, valueEntry: ValueEntry) = {
    val valueAsBinary = toBinaryWithPadding(valueEntry.value, maskEntry.value.length)
    maskEntry.value.zipWithIndex.map {
      case ('X', index) => valueAsBinary(index)
      case ('0', index) => '0'
      case ('1', index) => '1'
    }.mkString("")
  }

  private def toBinaryWithPadding(value: Long, targetLength: Int) = {
    String.format(s"%${targetLength}s", java.lang.Long.toBinaryString(value)).replace(' ', '0')
  }
}
