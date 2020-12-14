import scala.io.Source
import scala.util.Using

object TwelveTwo {


  def main(args: Array[String]) = {
    val instructions = loadInstructions.map { line =>
      val command = line.head
      val amount = line.tail.toLong

      (command, amount)
    }

    var waypoint: Coords = Coords(10L, 1L)
    var coords: Coords = Coords(0L, 0L)

    instructions.foreach { case (command, amount) =>
      command match {
        case 'N' => waypoint = North.moveForward(waypoint, amount)
        case 'S' => waypoint = South.moveForward(waypoint, amount)
        case 'E' => waypoint = East.moveForward(waypoint, amount)
        case 'W' => waypoint = West.moveForward(waypoint, amount)
        case 'F' => coords = Coords(coords.x + amount * waypoint.x, coords.y + amount * waypoint.y)
        case 'L' => waypoint = rotate(waypoint, -amount)
        case 'R' => waypoint = rotate(waypoint, amount)
      }

      println(s"Coords: ${coords.x}, ${coords.y}, waypoint: ${waypoint.x}, ${waypoint.y}, manhattan distance: ${abs(coords.x) + abs(coords.y)}")
    }
  }

  private def loadInstructions: List[String] = {
    Using(Source.fromFile("input")) { source => source.getLines().toList }.get
  }

  def rotate(waypoint: Coords, angle: Long): Coords = {
    val actualAngle = if (angle < 0) {
      360 + angle
    } else {
      angle
    }

    actualAngle match {
      case 90 => Coords(waypoint.y, -waypoint.x)
      case 180 => Coords(-waypoint.x, -waypoint.y)
      case 270 => Coords(-waypoint.y, waypoint.x)
    }
  }

  def abs(value: Long): Long = {
    if (value < 0) {
      -value
    } else {
      value
    }
  }
}

