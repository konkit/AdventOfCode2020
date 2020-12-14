import java.io.File
import java.util.*

val colorToBagMap = mutableMapOf<String, List<BagData>>()

fun main(args: Array<String>) {
part2()
}

fun part1() {
  loadData()

  val toSearch = LinkedList<String>(listOf("shiny gold"))
  val resultSet = mutableSetOf<String>()

  while(toSearch.isNotEmpty()) {
    val searched = toSearch.pop()
    val directs = colorToBagMap.filter { e -> e.value.filter { e.value.filter { it.color == searched }.isNotEmpty() }.isNotEmpty() }.keys

    directs.forEach {
      toSearch.push(it)
      resultSet.add(it)
    }
  }

  println(resultSet.size)
}

fun part2() {
  loadData()

  val toSearch = LinkedList<String>(listOf("shiny gold"))
  val resultSet = mutableSetOf<String>()
  var resultCount = 0

  while(toSearch.isNotEmpty()) {
    val searched = toSearch.pop()
    val children = colorToBagMap.get(searched)!!.forEach { e ->
      for (i in 1..e.count) {
        toSearch.push(e.color)
        resultCount += 1
      }
    }
  }

  println(resultCount)
}

fun loadData() {
  File("input").readLines().forEach({line ->
    val (color, contents) = Regex("(.+) bags contain (.+)").find(line)!!.destructured

    if (contents.trim() == "no other bags.") {
      colorToBagMap.put(color, emptyList())
    } else {
      val bags = contents.replace(".", "").split(",").map({contentLine ->
        val (count, bagColor) = Regex("(\\d+) (.+) bag|bags").find(contentLine)!!.destructured
        BagData(count.toInt(), bagColor)
      })
      colorToBagMap.put(color, bags)
    }
  })
}

data class BagData(val count: Int, val color: String)
