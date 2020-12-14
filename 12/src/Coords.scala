case class Coords(x: Long, y: Long)

sealed trait Direction {
  def moveForward(coords: Coords, amount: Long): Coords
}

case object North extends Direction {
  override def moveForward(coords: Coords, amount: Long) = {
    Coords(coords.x, coords.y+amount)
  }
}

case object South extends Direction {
  override def moveForward(coords: Coords, amount: Long) = {
    Coords(coords.x, coords.y-amount)
  }
}

case object East extends Direction {
  override def moveForward(coords: Coords, amount: Long) = {
    Coords(coords.x+amount, coords.y)
  }
}

case object West extends Direction {
  override def moveForward(coords: Coords, amount: Long) = {
    Coords(coords.x-amount, coords.y)
  }
}
