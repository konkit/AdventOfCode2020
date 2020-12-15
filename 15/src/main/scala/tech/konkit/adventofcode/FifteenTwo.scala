package tech.konkit.adventofcode

import scala.annotation.tailrec

object FifteenTwo {

  def main(args: Array[String]): Unit = {
    require(runTheGame(List(0,3,6), 2020) == 436)
    require(runTheGame(List(1,3,2), 2020) == 1)
    require(runTheGame(List(2,1,3), 2020) == 10)
    require(runTheGame(List(1,2,3), 2020) == 27)
    require(runTheGame(List(2,3,1), 2020) == 78)
    require(runTheGame(List(3,2,1), 2020) == 438)
    require(runTheGame(List(3,1,2), 2020) == 1836)
    require(runTheGame(List(13,16,0,12,15,1), 2020) == 319)

    val result = runTheGame(List(13, 16, 0, 12, 15, 1), 30000000)
    println(result)
  }

  def runTheGame(queue: List[Int], target: Int): Int = {
    runIteration(
      isFirstRun = true,
      currentElement = ValueWithIndex(queue.last, queue.length - 1),
      map = queue.zipWithIndex.map { case (value, index) => value -> List(index) }.toMap,
      targetCount = target,
      count = queue.length
    )
  }

  @tailrec
  def runIteration(isFirstRun: Boolean,
                   currentElement: ValueWithIndex,
                   map: Map[Int, List[Int]],
                   targetCount: Int,
                   count: Int): Int = {
    if (count % 1000 == 0) {
      println(s"Queue length: ${count}")
    }

    if (count == targetCount) {
      currentElement.value
    } else {
      if (!isFirstRun && elementExists(map, currentElement)) {
        val newMap = updatedMap(count, map, currentElement)
        val newValue = getDifferenceOfPreviousTwoValues(newMap, currentElement)
        val newElement = ValueWithIndex(newValue, count)

        runIteration(isFirstRun = false, newElement, newMap, targetCount, count+1)
      } else {
        val newMap = updatedMap(count, map, currentElement)
        val newElement = ValueWithIndex(0, count)

        runIteration(isFirstRun = false, newElement, newMap, targetCount, count+1)
      }
    }
  }

  private def updatedMap(queueLength: Int, map: Map[Int, List[Int]], currentElement: ValueWithIndex) = {
    val newList = (queueLength - 1) :: map.getOrElse(currentElement.value, List.empty)
    map + (currentElement.value -> newList.take(2))
  }

  private def elementExists(map: Map[Int, List[Int]], currentElement: ValueWithIndex) =
    map.contains(currentElement.value)

  private def getDifferenceOfPreviousTwoValues(map: Map[Int, List[Int]], currentElement: ValueWithIndex) = {
    val twoPreviousIndices = map(currentElement.value).take(2)
    if (twoPreviousIndices.length == 2) {
      twoPreviousIndices.head - twoPreviousIndices.last
    } else {
      twoPreviousIndices.head
    }
  }
}
