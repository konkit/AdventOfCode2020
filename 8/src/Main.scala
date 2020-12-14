import scala.collection.mutable
import scala.io.Source

object Main {
  def main(args: Array[String]): Unit = {
    val program = Source.fromFile("input").getLines().toArray.map({ line =>
      val programLine = "(.+) (.+)".r

      line match {
        case programLine(command1, command2) => (command1, command2.toInt)
      }
    })

    var i = 0;
    while (i < program.length) {
      if (program(i)._1 == "jmp") {
        val newProgram = program.clone()
        newProgram(i) = ("nop", newProgram(i)._2)
        runProgram(newProgram)
        i += 1
      } else if (program(i)._1 == "nop") {
        val newProgram = program.clone()
        newProgram(i) = ("jmp", newProgram(i)._2)
        runProgram(newProgram)
        i += 1
      } else {
        i += 1
      }
    }
  }

  def runProgram(program: Array[(String, Int)]): Unit = {
    var acc = 0
    var i = 0
    val executedInstructions = mutable.Set[Int]()

    while (true) {
      if (executedInstructions.contains(i)) {
        //        println(s"Loop! Acc: ${acc}")
        return
      } else if (i == program.length) {
        println(s"End of program! Acc: ${acc}")
        return
      } else {
        executedInstructions.add(i)
      }

      program(i) match {
        case ("acc", value) =>
          acc += value
          i += 1
        case ("nop", _) =>
          i += 1
        case ("jmp", rel) =>
          i += rel
      }
    }
  }
}
