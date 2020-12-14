import scala.io.Source
import scala.util.Using

object Twelve {


  def main(args: Array[String]) = {
    val instructions = loadInstructions.map { line =>
      val command = line.head
      val amount = line.tail.toLong

      (command, amount)
    }

    var coords: Coords = Coords(0L, 0L)
    var direction: Direction = East

    instructions.foreach { case (command, amount) =>
      command match {
        case 'N' => coords = North.moveForward(coords, amount)
        case 'S' => coords = South.moveForward(coords, amount)
        case 'E' => coords = East.moveForward(coords, amount)
        case 'W' => coords = West.moveForward(coords, amount)
        case 'F' => coords = direction.moveForward(coords, amount)
        case 'L' => direction = changeDirection(direction, -amount)
        case 'R' => direction = changeDirection(direction, amount)
      }

      println(s"Coords: ${coords.x}, ${coords.y}, manhattan distance: ${coords.x + coords.y}")
    }
  }

  private def loadInstructions: List[String] = {
    Using(Source.fromFile("input")) { source => source.getLines().toList }.get
  }

  def changeDirection(direction: Direction, amount: Long): Direction = {
    val directionToAngle: Map[Int, Direction] = Map(
      0 -> North,
      90 -> East,
      180 -> South,
      270 -> West
    )

    val angle = directionToAngle.map(_.swap)(direction) + amount.intValue()
    val newAngle = if (angle < 0) {
      angle + 360
    } else if (angle >= 360) {
      angle - 360
    } else {
      angle
    }
    directionToAngle(newAngle)
  }
}
