import java.io.File
import java.io.InputStream
import java.util.*

fun main(args: Array<String>) {
  partTwo()
}

fun partOne() {
  var counter = 0

  File("input").readLines().forEach { line ->
    val reader = Scanner(line.byteInputStream())

  val (minStr, maxStr, letterStr, pass) = Regex("(\\d+)-(\\d+) (\\w): (\\w+)").find(line)!!.destructured
    val min = minStr.toLong()
    val max = maxStr.toLong()
    val letter = letterStr[0]

    val count = pass.count { c -> c == letter }

    if (count >= min && count <= max) {
      counter += 1
    }
  }

  println(counter)
}

fun partTwo() {
  var counter = 0

  File("input").readLines().forEach { line ->
    val reader = Scanner(line.byteInputStream())

    val (firstIndexStr, secondIndexStr, letterStr, pass) = Regex("(\\d+)-(\\d+) (\\w): (\\w+)").find(line)!!.destructured
    val firstIndex = firstIndexStr.toInt()
    val secondIndex = secondIndexStr.toInt()
    val letter = letterStr[0]

    val letterOneFits = pass[firstIndex-1] == letter
    val letterTwoFits = pass[secondIndex-1] == letter

    if (letterOneFits xor letterTwoFits) {
      counter += 1
    }
  }

  println(counter)
}
