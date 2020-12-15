

object Fifteen {

  def main(args: Array[String]) = {
//    val input = List(13,16,0,12,15,1)

    require(runTheGame(List(1,3,2)) == 1)
    require(runTheGame(List(2,1,3)) == 10)
    require(runTheGame(List(1,2,3)) == 27)
    require(runTheGame(List(2,3,1)) == 78)
    require(runTheGame(List(3,2,1)) == 438)
    require(runTheGame(List(3,1,2)) == 1836)

    val input = List(13,16,0,12,15,1)
    println(runTheGame(input))
  }

  def runTheGame(queue: List[Int]): Int = {
    runIteration(queue.zipWithIndex.map(v => ValueWithIndex(v._1, v._2)))
  }

  def runIteration(queue: List[ValueWithIndex]): Int = {
    val currentElement = queue.last
    if (queue.length == 2020) {
      currentElement.value
    } else if (queue.init.exists(_.value == currentElement.value)) {
      val newValue = getDifferenceOfPreviousTwoValues(queue, currentElement)
      runIteration(queue :+ ValueWithIndex(newValue, queue.length))
    } else {
      runIteration(queue :+ ValueWithIndex(0, queue.length))
    }
  }

  private def getDifferenceOfPreviousTwoValues(currentQueue: List[ValueWithIndex], currentElement: ValueWithIndex) = {
    val twoPreviousValuesWithIndices = currentQueue.to(LazyList).filter(_.value == currentElement.value).reverse.take(2).toVector

    if (twoPreviousValuesWithIndices.length == 2) {
      twoPreviousValuesWithIndices.head.index - twoPreviousValuesWithIndices.last.index
    } else {
      twoPreviousValuesWithIndices.head.index
    }
  }
}

case class ValueWithIndex(value: Int, index: Int)
